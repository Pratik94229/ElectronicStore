package com.pratik.electronic.store.ElectronicStore.repositories;

import com.pratik.electronic.store.ElectronicStore.entities.Product;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

  // search

  Page<Product> findByTitleContaining(String subTitle, Pageable pageable);

  Page<Product> findByLiveTrue(PageRequest pageable);

}

package com.pratik.electronic.store.ElectronicStore.repositories;

import com.pratik.electronic.store.ElectronicStore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
}

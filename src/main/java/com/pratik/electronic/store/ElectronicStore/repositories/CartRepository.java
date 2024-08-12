package com.pratik.electronic.store.ElectronicStore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratik.electronic.store.ElectronicStore.entities.Cart;
import com.pratik.electronic.store.ElectronicStore.entities.User;

public interface CartRepository extends JpaRepository<Cart, String> {

  Optional<Cart> findByUser(User user);

}

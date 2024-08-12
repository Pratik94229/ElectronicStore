package com.pratik.electronic.store.ElectronicStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratik.electronic.store.ElectronicStore.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}

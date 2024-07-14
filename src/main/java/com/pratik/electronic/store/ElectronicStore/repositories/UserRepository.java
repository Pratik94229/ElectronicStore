package com.pratik.electronic.store.ElectronicStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratik.electronic.store.ElectronicStore.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}

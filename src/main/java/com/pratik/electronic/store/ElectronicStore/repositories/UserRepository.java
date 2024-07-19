package com.pratik.electronic.store.ElectronicStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratik.electronic.store.ElectronicStore.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email,String password);

    List<User> findByNameContaining(String keywords);

}

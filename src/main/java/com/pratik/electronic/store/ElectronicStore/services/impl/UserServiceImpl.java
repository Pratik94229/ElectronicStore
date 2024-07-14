package com.pratik.electronic.store.ElectronicStore.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import com.pratik.electronic.store.ElectronicStore.dtos.UserDto;
import com.pratik.electronic.store.ElectronicStore.entities.User;
import com.pratik.electronic.store.ElectronicStore.repositories.UserRepository;
import com.pratik.electronic.store.ElectronicStore.services.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDto createUser(UserDto userDto) {

    // generate unique id in string format
    String userId = UUID.randomUUID().toString();
    userDto.setUserId(userId);

    // DTO -> Entity
    User user = dtotoEntity(userDto);
    User savedUser = userRepository.save(user);

    // entity->DTO
    UserDto newDto = entityToDto(savedUser);
    return newDto;
  }

  private UserDto entityToDto(User savedUser) {
    UserDto userDto = UserDto.builder()
        .userId(savedUser.getUserId())
        .name(savedUser.getName())
        .email(savedUser.getEmail())
        .password(savedUser.getPassword())
        .about(savedUser.getAbout())
        .gender(savedUser.getGender())
        .imageName(savedUser.getImageName())
        .build();
    return userDto;
  }

  private User dtotoEntity(UserDto userDto) {
    User user = User.builder()
        .userId(userDto.getUserId())
        .name(userDto.getName())
        .email(userDto.getEmail())
        .password(userDto.getPassword())
        .about(userDto.getAbout())
        .gender(userDto.getGender())
        .imageName(userDto.getImageName())
        .build();

    return user;
  }

  @Override
  public UserDto updateUser(UserDto userDto, String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with given id !!"));
    user.setName(userDto.getName());

    // email update
    user.setAbout(userDto.getAbout());
    user.setGender(userDto.getGender());

    user.setPassword(userDto.getPassword());

    user.setImageName(userDto.getImageName());

    // save data
    User updatedUser = userRepository.save(user);

    UserDto updatedDto = entityToDto(updatedUser);

    return updatedDto;
  }

  @Override
  public void deleteUser(String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with given id !!"));

    // delete user
    userRepository.delete(user);
  }

  @Override
  public List<UserDto> getAllUser() {
    List<User> users = userRepository.findAll();
    List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
    return dtoList;
  }

  @Override
  public UserDto getUserById(String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("user not found with given id !!"));
    return entityToDto(user);
  }

  @Override
  public UserDto getUserByEmail(String email) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getUserByEmail'");
  }

  @Override
  public List<UserDto> searchUser(String keyword) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchUser'");
  }

}

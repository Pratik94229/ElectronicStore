package com.pratik.electronic.store.ElectronicStore.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratik.electronic.store.ElectronicStore.dtos.UserDto;
import com.pratik.electronic.store.ElectronicStore.entities.User;
import com.pratik.electronic.store.ElectronicStore.repositories.UserRepository;
import com.pratik.electronic.store.ElectronicStore.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

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
                .orElseThrow(() -> new RuntimeException("user not found with given id !!"));
        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with given email id !!"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }


//    @Override
//    public Optional<User> findUserByEmailOptional(String email) {
//        return userRepository.findByEmail(email);
//    }

    private UserDto entityToDto(User savedUser) {
//    UserDto userDto = UserDto.builder()
//        .userId(savedUser.getUserId())
//        .name(savedUser.getName())
//        .email(savedUser.getEmail())
//        .password(savedUser.getPassword())
//        .about(savedUser.getAbout())
//        .gender(savedUser.getGender())
//        .imageName(savedUser.getImageName())
//        .build();
        return mapper.map(savedUser, UserDto.class);
    }

    private User dtotoEntity(UserDto userDto) {
//    User user = User.builder()
//        .userId(userDto.getUserId())
//        .name(userDto.getName())
//        .email(userDto.getEmail())
//        .password(userDto.getPassword())
//        .about(userDto.getAbout())
//        .gender(userDto.getGender())
//        .imageName(userDto.getImageName())
//        .build();

        return mapper.map(userDto, User.class);
    }

}

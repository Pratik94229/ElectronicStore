package com.pratik.electronic.store.ElectronicStore.controllers;

import com.pratik.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import com.pratik.electronic.store.ElectronicStore.dtos.UserDto;
import com.pratik.electronic.store.ElectronicStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        UserDto user = userService.createUser(userDto);

        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    // Update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId, @RequestBody UserDto userDto) {

        UserDto updatedUserDto = userService.updateUser(userDto, userId);

        return new ResponseEntity<>(updatedUserDto, HttpStatus.CREATED);

    }

    //Delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        ApiResponseMessage userDeletedSuccessfully = ApiResponseMessage
                .builder()
                .message("User Deleted Successfully")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(userDeletedSuccessfully, HttpStatus.OK);

    }

    //Get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);

    }

    //Get Single user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);

    }

    //Get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);

    }


    //Search User
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword) {
        return new ResponseEntity<>(userService.searchUser(keyword), HttpStatus.OK);

    }
}

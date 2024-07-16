package com.pratik.electronic.store.ElectronicStore.dtos;

import com.pratik.electronic.store.ElectronicStore.validators.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @Size(min = 3, max = 15,message = "Invalid Name Size")
    private String name;

    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$", message = "Invalid User Email !!")
    @Email(message = "Invalid Email !")
    private String email;

    @NotBlank(message = "Password is needed")
    private String password;

    @Size(min=4,max=6,message = "Please choose between male and female")
    private String gender;

    @NotBlank(message = "Please fill necessary details")
    private String about;

    @ImageNameValid
    private String imageName;

}

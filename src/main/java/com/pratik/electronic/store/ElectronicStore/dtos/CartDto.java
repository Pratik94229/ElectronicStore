package com.pratik.electronic.store.ElectronicStore.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartDto {

  private String cartId;
  private Date createdAt;

  private UserDto user;

  private List<CartItemDto> items = new ArrayList<>();

}

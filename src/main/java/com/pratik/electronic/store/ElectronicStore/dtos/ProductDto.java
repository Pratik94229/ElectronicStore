package com.pratik.electronic.store.ElectronicStore.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {

  private String productId;
  private String title;
  private String description;
  private int price;
  private int discountedPrice;
  private int quantity;
  private Date addedDate;
  private boolean live;
  private boolean stock;
  private String productImageName;
  private CategoryDto category;

}

package com.pratik.electronic.store.ElectronicStore.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddItemToCartRequest {

  private String productId;
  private int quantity;

}

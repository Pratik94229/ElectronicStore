package com.pratik.electronic.store.ElectronicStore.services;

import com.pratik.electronic.store.ElectronicStore.dtos.AddItemToCartRequest;
import com.pratik.electronic.store.ElectronicStore.dtos.CartDto;

public interface CartService {

  // add items to cart:
  // case1: cart for user is not available: we will create the cart and then add
  // the item
  // case2: cart available add the items to cart
  CartDto addItemToCart(String userId, AddItemToCartRequest request);

  // remove item from cart:
  void removeItemFromCart(String userId, int cartItem);

  // remove all items from cart
  void clearCart(String userId);

  CartDto getCartByUser(String userId);

}

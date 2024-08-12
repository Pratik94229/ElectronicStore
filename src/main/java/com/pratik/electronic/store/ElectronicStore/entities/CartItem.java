package com.pratik.electronic.store.ElectronicStore.entities;

import jakarta.persistence.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int cartItemId;

  @JoinColumn(name = "product_id")
  @OneToOne
  private Product product;
  private int quantity;
  private int totalPrice;

  // mapping cart

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

}
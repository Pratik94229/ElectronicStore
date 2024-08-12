package com.pratik.electronic.store.ElectronicStore.services.impl;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pratik.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.pratik.electronic.store.ElectronicStore.dtos.ProductDto;
import com.pratik.electronic.store.ElectronicStore.entities.Category;
import com.pratik.electronic.store.ElectronicStore.entities.Product;
import com.pratik.electronic.store.ElectronicStore.expections.ResourceNotFoundException;
import com.pratik.electronic.store.ElectronicStore.helper.Helper;
import com.pratik.electronic.store.ElectronicStore.repositories.CategoryRepository;
import com.pratik.electronic.store.ElectronicStore.repositories.ProductRepository;
import com.pratik.electronic.store.ElectronicStore.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public ProductDto create(ProductDto productDto) {

    Product product = mapper.map(productDto, Product.class);

    // product id
    String productId = UUID.randomUUID().toString();
    product.setProductId(productId);
    // added

    // Convert java.util.Date to java.sql.Date
    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
    product.setAddedDate(sqlDate);

    Product saveProduct = productRepository.save(product);
    return mapper.map(saveProduct, ProductDto.class);
  }

  @Override
  public ProductDto update(ProductDto productDto, String productId) {
    // fetch the product of given id
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found of given Id !!"));

    product.setTitle(productDto.getTitle());
    product.setDescription(productDto.getDescription());
    product.setPrice(productDto.getPrice());
    product.setDiscountedPrice(productDto.getDiscountedPrice());
    product.setQuantity(productDto.getQuantity());
    product.setLive(productDto.isLive());
    product.setStock(productDto.isStock());
    product.setProductImageName(productDto.getProductImageName());

    // save the entity
    Product updatedProduct = productRepository.save(product);
    return mapper.map(updatedProduct, ProductDto.class);
  }

  @Override
  public void delete(String productId) {

    // fetch the product of given id
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found of given Id !!"));
    productRepository.delete(product);

  }

  @Override
  public ProductDto getProductById(String productId) {
    // fetch the product of given id
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found of given Id !!"));
    return mapper.map(product, ProductDto.class);
  }

  @Override
  public PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
    Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
    PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
    Page<Product> page = productRepository.findAll(pageable);
    return Helper.getPageableResponse(page, ProductDto.class);
  }

  @Override
  public PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize, String sortBy, String sortDir) {
    Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
    PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
    Page<Product> page = productRepository.findByLiveTrue(pageable);
    return Helper.getPageableResponse(page, ProductDto.class);

  }

  @Override
  public PageableResponse<ProductDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy,
      String sortDir) {
    Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
    PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
    Page<Product> page = productRepository.findByTitleContaining(subTitle, pageable);
    return Helper.getPageableResponse(page, ProductDto.class);
  }

  @Override
  public ProductDto createWithCategory(ProductDto productDto, String categoryId) {

    // fetch the category from db:
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found !!"));

    Product product = mapper.map(productDto, Product.class);

    // product id
    String productId = UUID.randomUUID().toString();
    product.setProductId(productId);

    // added
    product.setAddedDate(new Date());

    product.setCategory(category);
    Product saveProduct = productRepository.save(product);
    return mapper.map(saveProduct, ProductDto.class);
  }

  @Override
  public ProductDto updateCategory(String productId, String categoryId) {
    // product fetch
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product of given id not found !!"));

    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category of given id not found !!"));

    product.setCategory(category);
    Product savedProduct = productRepository.save(product);
    return mapper.map(savedProduct, ProductDto.class);
  }

  @Override
  public PageableResponse<ProductDto> getAllOfCategory(String categoryId, int pageNumber, int pageSize, String sortBy,
      String sortDir) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category of given id not found !!"));
    Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

    PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);

    Page<Product> page = productRepository.findByCategory(category, pageable);
    return Helper.getPageableResponse(page, ProductDto.class);
  }

}

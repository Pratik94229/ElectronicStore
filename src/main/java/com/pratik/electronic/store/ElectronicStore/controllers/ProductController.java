package com.pratik.electronic.store.ElectronicStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratik.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import com.pratik.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.pratik.electronic.store.ElectronicStore.dtos.ProductDto;
import com.pratik.electronic.store.ElectronicStore.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  ProductService productService;

  // create
  @PostMapping
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
    ProductDto createProduct = productService.create(productDto);
    return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
  }

  // update
  public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto) {
    ProductDto updatedProduct = productService.update(productDto, productId);
    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
  }

  // delete
  public ResponseEntity<ApiResponseMessage> delete(@PathVariable String productId) {
    productService.delete(productId);

    ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Product is deleted successfully !!")
        .status(HttpStatus.OK).success(true).build();
    return new ResponseEntity<>(responseMessage, HttpStatus.OK);

  }

  // get single
  @GetMapping("/{productId}")
  public ResponseEntity<ProductDto> getProduct(@PathVariable String productId) {
    ProductDto productDto = productService.get(productId);
    return new ResponseEntity<>(productDto, HttpStatus.OK);

  }

  // get
  @GetMapping
  public ResponseEntity<PageableResponse<ProductDto>> getAll(
      @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
      @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

  ) {
    PageableResponse<ProductDto> pageableResponse = productService.getAll(pageNumber, pageSize, sortBy, sortDir);
    return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
  }

  // get all live

  @GetMapping("/live")
  public ResponseEntity<PageableResponse<ProductDto>> getAllLive(
      @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
      @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

  ) {
    PageableResponse<ProductDto> pageableResponse = productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
    return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
  }

  // search all

  @GetMapping("/search/{query}")
  public ResponseEntity<PageableResponse<ProductDto>> searchProduct(
      @PathVariable String query,
      @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
      @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

  ) {
    PageableResponse<ProductDto> pageableResponse = productService.searchByTitle(query, pageNumber, pageSize, sortBy,
        sortDir);
    return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
  }

}

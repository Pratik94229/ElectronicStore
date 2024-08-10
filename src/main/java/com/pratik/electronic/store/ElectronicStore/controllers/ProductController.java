package com.pratik.electronic.store.ElectronicStore.controllers;

import com.pratik.electronic.store.ElectronicStore.dtos.UserDto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pratik.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import com.pratik.electronic.store.ElectronicStore.dtos.ImageResponse;
import com.pratik.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.pratik.electronic.store.ElectronicStore.dtos.ProductDto;
import com.pratik.electronic.store.ElectronicStore.services.FileService;
import com.pratik.electronic.store.ElectronicStore.services.ProductService;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  ProductService productService;

  @Autowired
  private FileService fileService;

  @Value("${product.image.path}")
  private String imageUploadPath;

  private Logger logger = LoggerFactory.getLogger(ProductController.class);

  // create
  @PostMapping
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
    ProductDto createProduct = productService.create(productDto);
    return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
  }

  // update
  @PutMapping("/{productId}")
  public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto) {
    ProductDto updatedProduct = productService.update(productDto, productId);
    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
  }

  // delete
  @DeleteMapping("/{productId}")
  public ResponseEntity<ApiResponseMessage> delete(@PathVariable String productId) {
    productService.delete(productId);

    ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Product is deleted successfully !!")
        .status(HttpStatus.OK).success(true).build();
    return new ResponseEntity<>(responseMessage, HttpStatus.OK);

  }

  // get single
  @GetMapping("/{productId}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable String productId) {
    ProductDto productDto = productService.getProductById(productId);
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

  // upload user image
  @PostMapping("/image/{productId}")
  public ResponseEntity<ImageResponse> uploadProductImage(@RequestParam("productImage") MultipartFile image,
      @PathVariable String productId) throws IOException {
    String imageName = fileService.uploadFile(image, imageUploadPath);
    logger.info(imageName);
    ProductDto productDto = productService.getProductById(productId);
    productDto.setProductImageName(imageName);
    logger.info(productDto.getProductImageName());
    ProductDto updatedDto = productService.update(productDto, productId);
    logger.info(productDto.getProductImageName());
    ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true)
        .message("image is uploaded successfully ").status(HttpStatus.CREATED).build();
    return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

  }

  // serve user image
  @GetMapping(value = "/image/{productId}")
  public void serveUserImage(@PathVariable String productId, HttpServletResponse response) throws IOException {
    ProductDto productDto = productService.getProductById(productId);
    logger.info("User image name : {} ", productDto.getProductImageName());
    InputStream resource = fileService.getResource(imageUploadPath, productDto.getProductImageName());
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(resource, response.getOutputStream());

  }

}
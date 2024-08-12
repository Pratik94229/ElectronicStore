package com.pratik.electronic.store.ElectronicStore.services;

import java.util.List;

import com.pratik.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.pratik.electronic.store.ElectronicStore.dtos.ProductDto;

public interface ProductService {

    // create
    ProductDto create(ProductDto productDto);

    // update
    ProductDto update(ProductDto productDto, String productId);

    // delete
    void delete(String productId);

    // get single
    ProductDto getProductById(String productId);

    // get all
    PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    // get live
    PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize, String sortBy, String sortDir);

    // search product

    PageableResponse<ProductDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy,
            String sortDir);

    // create product with category
    ProductDto createWithCategory(ProductDto productDto, String categoryId);

    // update category of product
    ProductDto updateCategory(String productId, String categoryId);

    // Get all product of given category

    PageableResponse<ProductDto> getAllOfCategory(String categoryId, int pageNumber, int pageSize, String sortBy,
            String sortDir);

}

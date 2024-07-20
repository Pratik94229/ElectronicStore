package com.pratik.electronic.store.ElectronicStore.services;

import com.pratik.electronic.store.ElectronicStore.dtos.CategoryDto;
import com.pratik.electronic.store.ElectronicStore.dtos.PageableResponse;

public interface CategoryService {

    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //delete

    void delete(String categoryId);

    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single category detail
    CategoryDto get(String categoryId);

    //search:
}

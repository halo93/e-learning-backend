package com.elearningbackend.controller;

import com.elearningbackend.customexception.ElearningException;
import com.elearningbackend.dto.CategoryDto;
import com.elearningbackend.dto.Pager;
import com.elearningbackend.dto.Result;
import com.elearningbackend.service.IAbstractService;
import com.elearningbackend.utility.Constants;
import com.elearningbackend.utility.ResultCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    @Qualifier("categoryService")
    private IAbstractService<CategoryDto, String> abstractService;

    @GetMapping("/categories")
    public Pager<CategoryDto> loadAll(
        @RequestParam(value = "page", defaultValue = Constants.CURRENT_PAGE_DEFAULT_STRING_VALUE) int page,
        @RequestParam(value = "limit", defaultValue = Constants.NO_OF_ROWS_DEFAULT_STRING_VALUE) int noOfRowInPage){
        return abstractService.loadAll(page, noOfRowInPage);
    }

    @GetMapping("/categories/{key}")
    public Result<CategoryDto> getByKey(@PathVariable("key") String key){
        try{
            CategoryDto categoryDto = abstractService.getOneByKey(key);
            return new Result<>(ResultCodes.OK.getCode(),
                ResultCodes.OK.getMessage(), categoryDto);
        } catch (ElearningException e) {
            e.printStackTrace();
            return new Result<>(e.getErrorCode(), e.getMessage(), null);
        }
    }
}

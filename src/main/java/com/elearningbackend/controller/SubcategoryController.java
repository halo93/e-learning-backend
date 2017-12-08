package com.elearningbackend.controller;

import com.elearningbackend.customexception.ElearningException;
import com.elearningbackend.dto.Pager;
import com.elearningbackend.dto.Result;
import com.elearningbackend.dto.SubcategoryDto;
import com.elearningbackend.service.IAbstractService;
import com.elearningbackend.utility.Constants;
import com.elearningbackend.utility.ResultCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SubcategoryController {
    @Autowired
    @Qualifier("subcategoryService")
    private IAbstractService<SubcategoryDto, String> abstractService;

    @GetMapping("/subcategories")
    public Pager<SubcategoryDto> loadAll(
        @RequestParam(value = "page", defaultValue = Constants.CURRENT_PAGE_DEFAULT_STRING_VALUE) int page,
        @RequestParam(value = "limit", defaultValue = Constants.NO_OF_ROWS_DEFAULT_STRING_VALUE) int noOfRowInPage) {
            return abstractService.loadAll(page, noOfRowInPage);
    }

    @GetMapping("/subcategories/{key}")
    public Result<SubcategoryDto> getOneByKey(@PathVariable("key") String key){
        try {
            SubcategoryDto subcategoryDto = abstractService.getOneByKey(key.toUpperCase());
            return new Result<>(ResultCodes.OK.getCode(), ResultCodes.OK.getMessage(), subcategoryDto);
        } catch (ElearningException e) {
            e.printStackTrace();
            return new Result<>(e.getErrorCode(), e.getMessage(), null);
        }
    }
}

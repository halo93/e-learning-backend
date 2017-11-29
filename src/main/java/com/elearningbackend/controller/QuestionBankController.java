package com.elearningbackend.controller;

import com.elearningbackend.dto.Pager;
import com.elearningbackend.dto.QuestionBankDto;
import com.elearningbackend.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api")
public class QuestionBankController {
    @Autowired
    @Qualifier("questionBankService")
    private IAbstractService<QuestionBankDto, String> abstractService;

    @GetMapping("/question/getAll/")
    public Pager<QuestionBankDto> loadAll(
            @Param("currentPage") int currentPage,
            @Param("noOfRowInPage") int noOfRowInPage){
        return null;
    }

    @GetMapping("/question/getOne/{key}")
    public QuestionBankDto getOne(@PathVariable("key") String key){
        return null;
    }

    @GetMapping("/question/getBy/{creatorUsername}/{currentPage}/{noOfRowInPage}")
    public Pager<QuestionBankDto> getByCreator(
            @PathVariable("creatorUsername") String creatorUsername,
            @PathVariable("currentPage") int currentPage,
            @PathVariable("noOfRowInPage") int noOfRowInPage
    ){
        return null;
    }

    @PostMapping("/question/add")
    public String add(@RequestBody QuestionBankDto questionBankDto){
        return null;
    }

    @PutMapping("/question/edit")
    public String edit(@RequestBody QuestionBankDto questionBankDto){
        return null;
    }

    @DeleteMapping("/question/del/{key}")
    public String delete(@PathVariable String key){
        return null;
    }
}

package com.elearningbackend.controller;

import com.elearningbackend.dto.AnswerBankDto;
import com.elearningbackend.dto.Pager;
import com.elearningbackend.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api")
public class AnswerBankController {
    @Autowired
    @Qualifier("answerBankService")
    private IAbstractService<AnswerBankDto, String> abstractService;

    @GetMapping("/answer/getAll/")
    public Pager<AnswerBankDto> loadAll(
            @Param("currentPage") int currentPage,
            @Param("noOfRowInPage") int noOfRowInPage
    ){
        return null;
    }

    @GetMapping("/answer/getKey/{key}")
    public AnswerBankDto getByKey(@PathVariable("key") String key){
        return null;
    }

    @GetMapping("/answer/getCreator/{creatorUsername}/")
    public Pager<AnswerBankDto> getByCreator(
            @PathVariable("creatorUsername") String creatorUserName,
            @Param("currentPage") int currentPage,
            @Param("noOfRowInPage") int noOfRowInPage
    ){
        return null;
    }

    @PostMapping("/answer/add")
    public AnswerBankDto add(@RequestBody AnswerBankDto answerBankDto){
        return null;
    }

    @PutMapping("/answer/edit")
    public AnswerBankDto edit(@RequestBody AnswerBankDto answerBankDto){
        return null;
    }

    @DeleteMapping("/answer/del/{key}")
    public AnswerBankDto delete(@PathVariable("key") String key){
        return null;
    }
}

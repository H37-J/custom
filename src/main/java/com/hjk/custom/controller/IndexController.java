package com.hjk.custom.controller;

import com.hjk.custom.dto.ResponseDto;
import com.hjk.custom.utils.RestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IndexController {

    @GetMapping(value="index")
    public ResponseDto index() {
        return ResponseDto.builder().data("test").build();
    }
}

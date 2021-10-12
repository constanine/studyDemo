package com.bokesoft.example.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class DemoController {
    @RequestMapping(value = {"/say/{id}"},method = RequestMethod.GET)
    public String say(@PathVariable("id") Integer id){
        return "id:"+id;
    }
}
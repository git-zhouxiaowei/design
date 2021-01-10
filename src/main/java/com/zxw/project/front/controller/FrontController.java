package com.zxw.project.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zhouxw
 * @description: PC前端相关接口d
 * @date 2021/1/8 15:23
 **/
@Controller
@RequestMapping("front")
public class FrontController {
    private String prefix = "front";

    @GetMapping
    public String index() {
        return prefix + "/index";
    }
}

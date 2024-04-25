package com.ohgiraffers.sessionsecurtiy.user.controller;

import com.ohgiraffers.sessionsecurtiy.user.model.dto.SignupDTO;
import com.ohgiraffers.sessionsecurtiy.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public void signup(){}

    @PostMapping("/signup")
    public ModelAndView signup(ModelAndView mv, @ModelAttribute SignupDTO signupDTO){

        int result = userService.regist(signupDTO);

        String message = "";
        if(result>0){
            message = "회원 가입 성공";
        }
        else{
            message="회원 가입 실패";
        }


        mv.addObject("message",message);


        return mv;
    }

}

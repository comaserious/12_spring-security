package com.ohgiraffers.sessionsecurtiy.user.model.service;

import com.ohgiraffers.sessionsecurtiy.user.model.dao.UserMapper;
import com.ohgiraffers.sessionsecurtiy.user.model.dto.LoginUserDTO;
import com.ohgiraffers.sessionsecurtiy.user.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /*패스워드의 암호처리가 필요하다*/
    @Autowired
    private PasswordEncoder passwordEncoder;

    public int regist(SignupDTO signupDTO) {

        // 암호화된 비밀번호를 다시 집어 넣어준다
        signupDTO.setUserPass(passwordEncoder.encode(signupDTO.getUserPass()));

        int result = 0;

        try {
            result = userMapper.regist(signupDTO);
        }catch (Exception e ){
            e.printStackTrace(); // 예외가 발생했을때 , 발생한 위치와 상태를 반환하는 메소드
        }

        return  result;
    }

    public LoginUserDTO findByUsername(String username) {

        LoginUserDTO login = userMapper.findByUsername(username);

        if(!Objects.isNull(login)){
            return login;
        }
        else return null;

    }
}

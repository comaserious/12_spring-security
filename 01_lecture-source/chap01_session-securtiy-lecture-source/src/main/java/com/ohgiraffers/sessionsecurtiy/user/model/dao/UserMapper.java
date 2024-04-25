package com.ohgiraffers.sessionsecurtiy.user.model.dao;

import com.ohgiraffers.sessionsecurtiy.user.model.dto.LoginUserDTO;
import com.ohgiraffers.sessionsecurtiy.user.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int regist(SignupDTO signupDTO);

    LoginUserDTO findByUsername(String username);
}

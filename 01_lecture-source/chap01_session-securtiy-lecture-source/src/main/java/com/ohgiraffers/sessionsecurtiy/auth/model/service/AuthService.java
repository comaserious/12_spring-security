package com.ohgiraffers.sessionsecurtiy.auth.model.service;

import com.ohgiraffers.sessionsecurtiy.auth.model.AuthDetails;
import com.ohgiraffers.sessionsecurtiy.user.model.dto.LoginUserDTO;
import com.ohgiraffers.sessionsecurtiy.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserService userService;
    /*필기.
    *  security 에서 사용자의 아이디를 인증하기 위한 interface 이다.
    *  loadUserByUsername 을 필수로 구현해야 하며, 로그인 인증 시 해당
    *  메소드에 login 요청시 전달 된 사용자의 id 를 매개변수로
    *  DB 에서 조회한다*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserDTO loginUserDTO = userService.findByUsername(username);

        if(Objects.isNull(loginUserDTO)){
            throw new UsernameNotFoundException("해당하는 유저는 존재하지 않습니다");
        }
        return new AuthDetails(loginUserDTO);
    }
}

package com.ohgiraffers.sessionsecurtiy.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

/*사용자의 로그인 실패시 실패요청을 커스텀 하기 위한 핸들러이다*/

@Configuration
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    /*
    * @Param request 사용자 요쳥 개체
    * @Param response 서버 응답값
    * @Param exception 발생한 오류를 담는 개체
    * */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "";
        /*사용자의 아이디 DB 존재하지 않는 경우 비밀번호가 맞지 않는 경우 발행하는 오류*/
        if(exception instanceof BadCredentialsException){
            errorMessage="아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다";
        }else if(exception instanceof InternalAuthenticationServiceException){
            /*서버에서 사용자 정보를 검증하는 과정헤서 발생하는 에러*/
            errorMessage="서버에서 오류가 발생하였습니다. 관리자에게 문의해주세요";
        }else if(exception instanceof UsernameNotFoundException){
            /*DB 에 사용자의 정보가 없는 경우 발생하는 오류*/
            errorMessage="존재하지 않는 ID 입니다";
        }else if(exception instanceof AuthenticationCredentialsNotFoundException){
            /*보안 컨텍스트(security context) 에 인증 객체가 존재하지 않거나 인증정보가 없는 상태에서
            * 보안 처리된 리소스에 접근하는 경우 발생*/
            errorMessage="인증 요청이 거부 되었습니다";
        }else{
            errorMessage="알수 없는 오류로 로그인 요청을 처리할 수 없습니다";
        }

        /*URL을 안전하게 인코딩하는데 사용되는 유틸로 문자열을 URL 에 사용가능한 형식으로 인코딩*/
        errorMessage = URLEncoder.encode(errorMessage,"UTF-8");

        /*오류를 처리할 페이지로 이동시킨다 . URL 요청은 servlet이 존재해야함*/
        setDefaultFailureUrl("/auth/fail?message="+errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}

package com.ohgiraffers.sessionsecurtiy.user.model.dto;

import com.ohgiraffers.sessionsecurtiy.common.UserRole;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor@AllArgsConstructor@Getter@Setter@ToString
public class LoginUserDTO {

    private int userCode;
    private String userId;
    private String userName;
    private String password;
    private UserRole userRole;


    public List<String> getRole() {
        if(this.userRole.getRole().length()>0){
            return Arrays.asList(this.userRole.getRole().split(","));
        }

        return new ArrayList<>();
    }
}

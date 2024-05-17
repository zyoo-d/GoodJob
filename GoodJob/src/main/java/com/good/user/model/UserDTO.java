package com.good.user.model;

import lombok.Data;

/**
 * UserDTO 클래스는 사용자 데이터를 담는 DTO(Data Transfer Object)입니다.
 */
@Data
public class UserDTO {
    
    /**
     * 사용자 아이디를 나타내는 변수입니다.
     */
    private String id;
    
    /**
     * 사용자 비밀번호를 나타내는 변수입니다.
     */
    private String pw;
    
    /**
     * 사용자 이름을 나타내는 변수입니다.
     */
    private String name;
    
    /**
     * 사용자 전화번호를 나타내는 변수입니다.
     */
    private String tel;
    
    /**
     * 사용자 주소를 나타내는 변수입니다.
     */
    private String address;
    
    /**
     * 사용자 이메일을 나타내는 변수입니다.
     */
    private String email;
    
    /**
     * 사용자 이메일 도메인을 나타내는 변수입니다.
     */
    private String domain;
    
    /**
     * 사용자 레벨을 나타내는 변수입니다.
     */
    private String lv;
    
    /**
     * 사용자 닉네임을 나타내는 변수입니다.
     */
    private String nickname;
    
    /**
     * 사용자의 희망 사항을 나타내는 변수입니다.
     */
    private String wish;
    
    /**
     * 사용자의 선호도를 나타내는 변수입니다.
     */
    private String prefer;
}
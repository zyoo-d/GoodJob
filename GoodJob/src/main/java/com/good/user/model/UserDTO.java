package com.good.user.model;

import lombok.Data;

@Data
public class UserDTO {
	private String id;
	private String pw;
	private String name;
	private String tel;
	private String address;
	private String email;
	private String lv;
	private String nickname;
}

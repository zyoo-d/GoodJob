package com.good.admin.block.model;

import lombok.Data;

/**
* UserManagementDTO 클래스는 사용자 관리 정보를 담고 있는 데이터 전송 객체입니다.
*/
@Data
public class UserManagementDTO {
	
	private String id;
	private String name;
	private String board;
	private String comment;
	private String review;
	private String regdate;
	private String report;
	private String status;

}

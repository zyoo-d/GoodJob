package com.good.admin.block.model;

import lombok.Data;

/**
* BlockUserDTO 클래스는 차단된 사용자 정보를 담고 있는 데이터 전송 객체입니다.
*/
@Data
public class BlockUserDTO {
	
	private String ban_seq;
	private String id;
	private String ban_startdate;
	private String ban_enddate;
	private String ban_reason;
	private String remaining_time;
	
	
}

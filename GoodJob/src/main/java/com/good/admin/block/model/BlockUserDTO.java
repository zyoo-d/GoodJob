package com.good.admin.block.model;

import lombok.Data;

@Data
public class BlockUserDTO {
	
	private String ban_seq;
	private String id;
	private String ban_startdate;
	private String ban_enddate;
	private String ban_reason;
	private String remaining_time;
	
}

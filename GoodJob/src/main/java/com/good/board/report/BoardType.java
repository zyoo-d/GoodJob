package com.good.board.report;

import lombok.Getter;

@Getter
public enum BoardType {
	QNA("qna", "tblQnAReport"),
	STD("std", "tblStdReport"),
	QNA_CM("qna_cm", "tblQnACmReport"),
	STD_CM("std_cm", "tblStdCmReport"),
	LIVE("live", "tblLiveComment");
	
	private final String code;
	private final String tableName;
	
	BoardType(String code, String tableName){
		this.code = code;
		this.tableName = tableName;
	}
	
	
	
	

}

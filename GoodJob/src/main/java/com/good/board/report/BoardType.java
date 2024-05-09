package com.good.board.report;

import lombok.Getter;

@Getter
public enum BoardType {
	QNA("qna", "/qna/listqna.do"),
	STD("std", "/study/liststudy.do"),
	QNA_CM("qna_cm", "/qna/listqna.do"),
	STD_CM("std_cm", "/study/liststudy.do"),
	LIVE("live", "tblLiveComment");
	
	private final String code;
	private final String tableName;
	
	BoardType(String code, String tableName){
		this.code = code;
		this.tableName = tableName;
	}
	
	
	
	

}

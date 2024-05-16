package com.good.board.report;

import lombok.Getter;

/**
 * 게시판 타입을 나타내는 열거형입니다.
 * 각 게시판 타입은 코드와 테이블 이름을 가지고 있습니다.
 */
@Getter
public enum BoardType {
	 /**
     * QnA 게시판을 나타냅니다.
     * 코드: "qna", 테이블 이름: "/qna/listqna.do"
     */
	QNA("qna", "/qna/listqna.do"),
	 /**
     * 스터디 게시판을 나타냅니다.
     * 코드: "std", 테이블 이름: "/study/liststudy.do"
     */
	STD("std", "/study/liststudy.do"),
	/**
     * QnA 댓글을 나타냅니다.
     * 코드: "qna_cm", 테이블 이름: "/qna/listqna.do"
     */
	QNA_CM("qna_cm", "/qna/listqna.do"),
	 /**
     * 스터디 댓글을 나타냅니다.
     * 코드: "std_cm", 테이블 이름: "/study/liststudy.do"
     */
	STD_CM("std_cm", "/study/liststudy.do"),
	 /**
     * 실시간 기업 댓글을 나타냅니다.
     * 코드: "live", 테이블 이름: "tblLiveComment"
     */
	LIVE("live", "tblLiveComment");
	
	private final String code;
	private final String tableName;
	
	 /**
     * 게시판 타입의 생성자입니다.
     *
     * @param code      게시판 타입의 코드
     * @param tableName 게시판 타입에 해당하는 테이블 이름
     */
	BoardType(String code, String tableName){
		this.code = code;
		this.tableName = tableName;
	}
	
	
	
	

}

package com.good.board.model;

import lombok.Data;

@Data
public class InterviewDTO {
	
	private String ITV_SEQ;  	//seq
	private String ITV_CPNAME;		//회사이름
	private String ITV_MEETDATE;	//면접날짜
	private String ITV_EVALUATION;	//면접평가 > 긍정 부정 보통
	private String ITV_REGDATE;		//작성일자
	private String CP_SEQ;		//회사 번호
	private String ID;			//작성자 id
	private String ITV_DIFFICULTY;	//난이도
	private String ITV_CATEGORY;	//카테고리 0 선택 X 1 선택 O
	private String ITV_CAREER;		//커리어 1 경력 0 신입
	private String ITV_PERSONNEL;	//면접 인원
	private String ITV_QUESTION;	//면접 질문
	private String ITV_TIP;			//면접 팁
	private String ITV_WHETHER;		//면접 합격 여부
	private String IMAGE; 	 //회사 이미지
	private String ITV_CONFIRM;		//면접 후기 등록 여부
	private String rnum;		//마이페이지 번호 매길 때 씀
	
	
	
	
}

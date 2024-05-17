package com.good.board.model;

import lombok.Data;

/**
 * StudyDTO 클래스는 공부 세션에 관련된 데이터를 저장하고 전달하는 데 사용됩니다.
 * Lombok 라이브러리의 @Data 어노테이션을 사용하여 기본적인 getter/setter, toString, equals, hashCode 메소드가 자동으로 생성됩니다.
 */
@Data
public class StudyDTO {
	private String std_seq;
	private String std_title;
	private String std_content;
	private String std_ing;
	private String std_duedate;
	private String std_regdate;
	private String std_views;
	private String cp_seq;
	private String cp_name;
	private String id;
	private String nickname;
	private String rnum;
	private String cm_count;
}

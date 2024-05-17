package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.util.DBUtil;

/**
* 스크랩 관련 데이터베이스 작업을 처리하는 DAO 클래스
*/
public class ScrapDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	/**
     * 기본 생성자입니다. 데이터베이스 연결을 수행합니다.
     */
	public ScrapDAO() {
		this.conn = DBUtil.open();
	}
	
	 /**
     * 데이터베이스 연결을 닫습니다.
     */
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("ScrapDAO.close 오류");
			e.printStackTrace();
		}
	}
	
	
}

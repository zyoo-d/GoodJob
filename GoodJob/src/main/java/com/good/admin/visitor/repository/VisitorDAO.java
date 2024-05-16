package com.good.admin.visitor.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.test.util.DBUtil;

/**
 * VisitorDAO 클래스는 방문자 데이터를 데이터베이스에서 조회하고 관리하는 데이터 액세스 객체입니다.
 */
public class VisitorDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	/**
	 * VisitorDAO 생성자입니다.
	 * 데이터베이스 연결을 초기화합니다.
	 */
	public VisitorDAO() {
		this.conn = DBUtil.open();
	}

	/**
	 * 데이터베이스 연결을 종료합니다.
	 */
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("VisitorDAO.close 오류");
			e.printStackTrace();
		}
	}

	/**
	 * 지정된 날짜까지의 방문자 수 정보를 반환합니다.
	 *
	 * @param date 날짜
	 * @return 날짜별 방문자 수 맵
	 */
	public Map<LocalDate, Integer> getVisitorsUntilDate(LocalDate date) {

		try {

			String sql = "select * from tblVisitor where visit_date <= ?";

			pstat = conn.prepareStatement(sql);
			pstat.setObject(1, date);

			rs = pstat.executeQuery();


			Map<LocalDate, Integer> map = new ConcurrentHashMap<>();

			while(rs.next()) {

				Date sqlDate = rs.getObject("visit_date", Date.class);

				map.put(sqlDate.toLocalDate(),rs.getInt("visit_count"));


			}

			return map;

		} catch (Exception e) {
			System.out.println("현재 날짜 부터 이전 날짜 방문자수 로드 실패");
			e.printStackTrace();
		}   

		return null;

	}
	/**
	 * 지정된 날짜부터 100일간의 방문자 수 데이터를 테이블에 삽입합니다.
	 *
	 * @param date 시작 날짜
	 */
	public void insertTblVisitor(LocalDate date) {

		try {

			String sql = "insert into tblVisitor values(?, 0)";

			pstat = conn.prepareStatement(sql);
			LocalDate day = date;
			for(int i=0; i<100; i++) {
				day = day.plusDays(1);
				pstat.setObject(1, day);
				pstat.executeUpdate();
				pstat.close();
			}

		} catch (Exception e) {
			System.out.println("방문자수 테이블 삽입 오류");
			e.printStackTrace();
		}


	}
	/**
	 * 지정된 날짜의 방문자 수를 데이터베이스에 저장합니다.
	 *
	 * @param today 날짜
	 * @param count 방문자 수
	 */
	public void saveVisitorsForDate(LocalDate today, int count) {
		try {

			String sql = "UPDATE tblVisitor SET visit_count = ? WHERE TRUNC(visit_date) = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, count);
			pstat.setDate(2, Date.valueOf(today));
			pstat.executeUpdate();
			pstat.close();
		} catch (Exception e) {
			System.out.println("서버 종료시 방문자수 DB저장 오류");
			e.printStackTrace();
		}
	}

	/**
	 * 지정된 날짜에 대한 방문자 수 데이터를 테이블에 삽입합니다.
	 *
	 * @param currentDate 날짜
	 */
	public void insertToday(LocalDate currentDate) {

		try {

			System.out.println("현재날짜 테이블에 insert");
			String sql = "insert into tblVisitor values(?,0)";

			pstat = conn.prepareStatement(sql);
			pstat.setObject(1, currentDate);

			pstat.executeUpdate();
			pstat.close();

		} catch (Exception e) {
			System.out.println("VisitorDAO.insertToday");
			e.printStackTrace();
		}


	}
	/**
	 * 지정된 날짜에 대한 방문자 수 데이터가 존재하는지 확인합니다.
	 *
	 * @param date 날짜
	 * @return 데이터가 존재하면 true, 그렇지 않으면 false
	 */
	public boolean existsForDate(LocalDate date) {
		try {
			String sql = "select count(*) from tblVisitor where visit_date = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setObject(1, date);
			rs = pstat.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			return count > 0;
		} catch (Exception e) {
			System.out.println("날짜 존재 확인 오류");
			e.printStackTrace();
		}
		return false;
	}






}

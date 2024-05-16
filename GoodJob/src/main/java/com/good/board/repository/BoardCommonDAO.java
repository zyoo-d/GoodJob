package com.good.board.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.good.board.model.BoardCommonDTO;
import com.good.board.report.model.ReportCommonDTO;
import com.test.util.DBUtil;

/**
 * 게시판 공통 정보를 다루는 DAO 클래스입니다.
 */
public class BoardCommonDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	/**
     * BoardCommonDAO 생성자입니다.
     * 데이터베이스 연결을 설정합니다.
     */
	public BoardCommonDAO() {
		this.conn = DBUtil.open();
	}
	/**
     * 데이터베이스 연결을 닫습니다.
     */
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("BoardCommonDAO.close 오류");
			e.printStackTrace();
		}
	}
	/**
     * 주간 인기 게시글 목록을 가져옵니다.
     *
     * @return 주간 인기 게시글 목록
     */
	public ArrayList<BoardCommonDTO> getWeekTopPosts() {
		
		ArrayList<BoardCommonDTO> list = new ArrayList<>();
		
		try {
			
			String sql = "select post_id,title,content,to_char(regdate,'yyyy-mm-dd') as regdate ,id ,views,board_type from VWWEEKLYPOPULARPOSTS";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				BoardCommonDTO dto = new BoardCommonDTO();
				
				dto.setSeq(rs.getString("post_id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRegDate(rs.getString("regdate"));
				dto.setId(rs.getString("id"));
				dto.setViews(rs.getString("views"));
				dto.setBoard_type(rs.getString("board_type"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println("일주일 게시글 조회수 top5 로드 실패");
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	/**
     * 게시판별 게시글 비율을 가져옵니다.
     *
     * @return 게시판별 게시글 비율이 담긴 HashMap (게시판 타입 -> 비율)
     */
	public HashMap<String, Double> getPostRatioByBoard() {
	    HashMap<String, Double> map = new HashMap<>();

	    try {
	        String sql = "SELECT board_type, COUNT(*) AS cnt FROM viewAllBoards GROUP BY board_type";
	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql);

	        int totalCount = 0;
	        HashMap<String, Integer> countMap = new HashMap<>();

	        while (rs.next()) {
	            String boardType = rs.getString("board_type");
	            int count = rs.getInt("cnt");
	            countMap.put(boardType, count);
	            totalCount += count;
	        }

	        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
	            String boardType = entry.getKey();
	            int count = entry.getValue();
	            double ratio = (double) count / totalCount * 100;
	            map.put(boardType, (double)Math.round(ratio));
	        }
	    } catch (Exception e) {
	        System.out.println("(관리자) 게시판별 게시글 비율 로드 실패");
	        e.printStackTrace();
	    }

	    return map;
	}
	/**
     * 당일 신고된 게시글 수를 가져옵니다.
     *
     * @return 당일 신고된 게시글 수
     */
	public String getTodayReportCount() {
		
		try {
			
			String sql = "select nvl(sum(report_count),0) as cnt from vwallreportlist WHERE TRUNC(REPORT_REGDATE) = TRUNC(SYSDATE)";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getString("cnt");
				
			}
			
		} catch (Exception e) {
			System.out.println("당일 신고 게시글수 로드 실패");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
     * 처리 완료된 신고 수를 가져옵니다.
     *
     * @return 처리 완료된 신고 수
     */
	public int getTotalCompleteReport() {
		
		try {
			
			String sql = "select count(*) as cnt from tblReportCheck";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getInt("cnt");
				
			}
			
		} catch (Exception e) {
			System.out.println("처리 완료된 신고 수");
			e.printStackTrace();
		}
		
		return 0;

	}
	
	/**
     * 최근 신고 수를 날짜별로 가져옵니다.
     *
     * @param recentDays 최근 날짜 목록
     * @return 최근 신고 수가 담긴 HashMap (날짜 -> (게시판 타입 -> 신고 수))
     */
	public HashMap<String,HashMap<String,Integer>> getRecentReportCount(List<LocalDate> recentDays){
		
		HashMap<String,HashMap<String,Integer>> map = new HashMap<>();
		
		try {
			
			LocalDate startDate = recentDays.get(0); 
			LocalDate endDate = recentDays.get(recentDays.size() - 1).plusDays(1);
			
	        System.out.println(startDate);
	        System.out.println(endDate);
			
	        String sql = "SELECT type, TO_CHAR(regdate, 'YYYY-MM-DD') AS regdate, cnt " +
	                "FROM vwDailyReportCount " +
	                "WHERE regdate >= ? AND regdate < ? " +
	                "ORDER BY regdate";
			
			pstat = conn.prepareStatement(sql);
			pstat.setDate(1, Date.valueOf(startDate));
			pstat.setDate(2, Date.valueOf(endDate));
			rs = pstat.executeQuery();
			
			while (rs.next()) {
			    String type = rs.getString("type");
			    String regdate = rs.getString("regdate");
			    int cnt = rs.getInt("cnt");
			    System.out.println("type: " + type + ", regdate: " + regdate + ", cnt: " + cnt);

			    HashMap<String, Integer> typeMap = map.getOrDefault(regdate, new HashMap<>());
			    typeMap.put(type, typeMap.getOrDefault(type, 0) + cnt);

			    map.put(regdate, typeMap);
			    System.out.println("Current map: " + map);
			}
			
		} catch (Exception e) {
			System.out.println("BoardCommonDAO.getRecentReportCount");
			e.printStackTrace();
		}
		
		return map;
		
	}
	
	
	

}

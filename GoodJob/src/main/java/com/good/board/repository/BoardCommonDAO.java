package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.good.board.model.BoardCommonDTO;
import com.test.util.DBUtil;

public class BoardCommonDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public BoardCommonDAO() {
		this.conn = DBUtil.open();
	}
	
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("BoardCommonDAO.close 오류");
			e.printStackTrace();
		}
	}
	
	public ArrayList<BoardCommonDTO> getDailyTopPosts() {
		
		ArrayList<BoardCommonDTO> list = new ArrayList<>();
		
		try {
			
			String sql = "select * from vwDailyPopularPosts";
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
			System.out.println("당일 게시글 조회수 top5 로드 실패");
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
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

	public String getTodayReportCount() {
		
		try {
			
			String sql = "select sum(report_count) as cnt from vwallreportlist";
			
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

}

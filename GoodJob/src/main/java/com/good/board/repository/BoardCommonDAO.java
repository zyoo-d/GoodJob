package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
				dto.setView(rs.getString("view"));
				dto.setBoard_type(rs.getString("board_type"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BoardCommonDAO.enclosing_method");
			e.printStackTrace();
		}
		
		return null;
		
	}

}

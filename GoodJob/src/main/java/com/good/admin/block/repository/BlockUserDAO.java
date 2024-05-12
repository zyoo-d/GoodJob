package com.good.admin.block.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.good.admin.block.model.BlockUserDTO;
import com.test.util.DBUtil;

public class BlockUserDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public BlockUserDAO() {
		this.conn = DBUtil.open();
	}
	
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("BlockUserDAO.close");
			e.printStackTrace();
		}
	}

	public ArrayList<BlockUserDTO> getBlockUserList() {
		
		ArrayList<BlockUserDTO> list = new ArrayList<>();
		
		try {
			
			String sql = "select ban_seq,ban_reason,id,to_char(ban_startdate,'yyyy-mm-dd') as ban_startdate,to_char(ban_enddate,'yyyy-mm-dd') as ban_enddate, TRUNC(ban_enddate-ban_startdate) as remaining_time from tblBanLog b";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				BlockUserDTO dto = new BlockUserDTO();
				dto.setBan_seq(rs.getString("ban_seq"));
				dto.setBan_reason(rs.getString("ban_reason"));
				dto.setBan_startdate(rs.getString("ban_startdate"));
				dto.setBan_enddate(rs.getString("ban_enddate"));
				dto.setId(rs.getString("id"));
				
				long remaining_time = rs.getLong("remaining_time");
				
				if(remaining_time > 1000) {
					dto.setRemaining_time("영구정지");
				} else {
					dto.setRemaining_time(remaining_time + "일 남음");
				}
				
				list.add(dto);
				
			}
			
		} catch (Exception e) {
			System.out.println("차단 유저 로드 실패");
			e.printStackTrace();
		}
		
		return list;
	}
	
	

}

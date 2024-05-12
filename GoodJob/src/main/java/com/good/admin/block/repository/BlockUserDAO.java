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
	
	public int getTotalBlockUserCount() {

		try {

			StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS cnt FROM tblBanLog");
			

			pstat = conn.prepareStatement(sql.toString());
			

			rs = pstat.executeQuery();

			if(rs.next()) {

				return rs.getInt("cnt");

			}

		} catch (Exception e) {
			System.out.println("총 차단 유저 수 로드 실패");
			e.printStackTrace();
		}

		return 0;

	}

	public ArrayList<BlockUserDTO> getBlockUserList(int startIndex, int endIndex) {
		
		ArrayList<BlockUserDTO> list = new ArrayList<>();
		
		try {
			
			StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT ROWNUM AS rnum, t.* FROM (SELECT * "
                    + "FROM vwListBanUser "
                    + "ORDER BY regdate asc) t) WHERE rnum BETWEEN ? AND ?");
			
			pstat = conn.prepareStatement(sql.toString());
			int parameterIndex = 1;
			
			pstat.setInt(parameterIndex++, startIndex + 1);
			pstat.setInt(parameterIndex, endIndex);
			
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

package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.good.board.model.BoardDTO;
import com.good.board.model.NoticeDTO;
import com.test.util.DBUtil;

public class NoticeDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public NoticeDAO() {
		this.conn = DBUtil.open();
	}

	public int addNotice(BoardDTO dto) {
		
		try {
			String sql = "Insert into tblNotice (nt_seq, nt_title, nt_content, id, nt_regdate) values (nt_seq.nextVal, ?, ?, ?, sysdate)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getId());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("NoticeDAO.addNotice");
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<NoticeDTO> list() {
		try {
			String sql = "select * from tblNotice";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<NoticeDTO> list = new ArrayList<>();
			while (rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setNt_seq(rs.getInt("nt_seq"));
				dto.setNt_title(rs.getString("nt_title"));
				dto.setNt_content(rs.getString("nt_content"));
				dto.setNt_regdate(rs.getString("nt_regdate"));
				dto.setId(rs.getString("id"));
				dto.setNt_views(rs.getInt("nt_views"));

				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("NoticeDAO.list");
			e.printStackTrace();
		}
		return null;
	}
}

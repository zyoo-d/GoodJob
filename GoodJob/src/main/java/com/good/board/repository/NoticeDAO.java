package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.board.model.BoardDTO;
import com.good.board.model.NoticeDTO;
import com.good.board.model.QnaBoardDTO;
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

	public int getTotalCount(HashMap<String, String> map) {
		try {

			String where = "";

			if (map.get("search").equals("y")) {

				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format("select count(*) as cnt from tblNotice %s", where);

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public ArrayList<NoticeDTO> list(HashMap<String, String> map) {

		try {

			String where = "";
			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format(
					"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM tblNotice WHERE rnum BETWEEN %s AND %s",
					where, map.get("sort"), map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<NoticeDTO> list = new ArrayList<>();

			while (rs.next()) {

				NoticeDTO dto = new NoticeDTO();
				dto.set

				list.add(dto);

			}
			return list;

		} catch (Exception e) {
			System.out.println("질문게시판 게시글 목록 로드 실패");
			e.printStackTrace();
		}

		return null;
	}
}

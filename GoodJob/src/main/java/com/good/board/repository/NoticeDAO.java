package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.board.model.BoardCommonDTO;
import com.good.board.model.NoticeDTO;
import com.test.util.DBUtil;

/**
 * NoticeDAO는 공지사항(Notice)과 관련된 데이터베이스 작업을 수행하는 클래스입니다.
 */
public class NoticeDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

/**
     * NoticeDAO 객체를 생성합니다. 데이터베이스 연결을 수행합니다.
     */
	public NoticeDAO() {
		this.conn = DBUtil.open();
	}
/**
     * 데이터베이스 연결을 닫습니다.
     */
	public void close()  {
		
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("noticeDAO.close 오류");
			e.printStackTrace();
		}
	}

	public int addNotice(BoardCommonDTO dto) {

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
			
			String sql = "";
			
			sql = String.format(
					"select * from (select a.*, rownum as rnum from vwNotice a %s order by %s) where rnum between %s and %s",
					where, map.get("sort"), map.get("begin"), map.get("end"));
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();
			
			while (rs.next()) {

				NoticeDTO dto = new NoticeDTO();

				dto.setId(rs.getString("id"));
				dto.setNt_content(rs.getString("nt_content"));
				dto.setNt_regdate(rs.getString("nt_regdate"));
				dto.setNt_seq(rs.getString("nt_seq"));
				dto.setNt_views(rs.getInt("nt_views"));
				dto.setNt_title(rs.getString("nt_title"));
				dto.setNickname(rs.getString("nickname"));


				list.add(dto);
			}
			
			
			return list;

		} catch (Exception e) {
			System.out.println("질문게시판 게시글 목록 로드 실패");
			e.printStackTrace();
		}

		return null;
	}

	public NoticeDTO viewNotice(String nt_seq) {
		try {
			String sql = "select * from vwNotice where nt_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, nt_seq);

			rs = pstat.executeQuery();

			if (rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setNt_content(rs.getString("nt_content"));
				dto.setNt_title(rs.getString("nt_title"));
				dto.setNt_regdate(rs.getString("nt_regdate"));
				dto.setNt_seq(rs.getString("nt_seq"));

				return dto;
			}

		} catch (Exception e) {
			System.out.println("NoticeDAO.viewNotice");
			e.printStackTrace();
		}
		
		return null;
	}

	public int delNotice(String seq) {
		try {
			String sql = "delete from tblNotice where nt_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("NoticeDAO.delNotice");
			e.printStackTrace();
		}
		return 0;
	}

	public void updateReadcount(String nt_seq) {
		try {
			String sql = "update tblNotice set nt_views = nt_views + 1 where nt_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, nt_seq);
			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("NoticeDAO.updateReadcount");
			e.printStackTrace();
		}
		
	}

	public int edit(NoticeDTO dto) {
		try {
			String sql = "update tblNotice set nt_title = ?, nt_content = ? where nt_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getNt_title());
			pstat.setString(2, dto.getNt_content());
			pstat.setString(3, dto.getNt_seq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("NoticeDAO.edit");
			e.printStackTrace();
		}
		return 0;
	}
}

package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.board.model.StudyDTO;
import com.test.util.DBUtil;

public class StudyDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public StudyDAO() {
		this.conn = DBUtil.open();
	}

	public int addStudy(StudyDTO dto) {
		try {
			String sql = "INSERT INTO tblstudy (STD_SEQ, STD_TITLE, STD_CONTENT, STD_ING, STD_REGDATE, CP_SEQ, ID, STD_DUEDATE) VALUES (seqStudy.NEXTVAL, ?, ?, 'N', sysdate, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getStd_title());
			pstat.setString(2, dto.getStd_content());
			pstat.setString(3, dto.getCp_seq());
			pstat.setString(4, dto.getId());
			pstat.setString(5, dto.getStd_duedate());

			int result = pstat.executeUpdate();
			pstat.close();
			return result;

		} catch (Exception e) {
			System.out.println("StudyDAO.addStudy");
			e.printStackTrace();
		}
		return 0;
	}

	public StudyDTO getStudy(String seq) {
		try {
			String sql = "select * from vwstudy where std_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			if (rs.next()) {
				StudyDTO dto = new StudyDTO();

				dto.setStd_seq(rs.getString("std_seq"));
				dto.setStd_title(rs.getString("std_title"));
				dto.setStd_content(rs.getString("std_content"));
				dto.setStd_ing(rs.getString("std_ing"));
				dto.setStd_regdate(rs.getString("std_regdate"));
				dto.setStd_duedate(rs.getString("std_duedate"));
				dto.setStd_views(rs.getString("std_views"));
				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setCp_name(rs.getString("cp_name"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));

				return dto;
			}

		} catch (Exception e) {
			System.out.println("StudyDAO.getStudy");
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<StudyDTO> listStudy(HashMap<String, String> map) {
		try {
			String where = "";
			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format(
					"select * from (select a.*, rownum as rnum from vwstudy a %s) where rnum between %s and %s order by std_regdate desc",
					where, map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<StudyDTO> list = new ArrayList<>();
			while (rs.next()) {
				StudyDTO dto = new StudyDTO();
				dto.setStd_seq(rs.getString("std_seq"));
				dto.setStd_title(rs.getString("std_title"));
				dto.setStd_content(rs.getString("std_content"));
				dto.setStd_ing(rs.getString("std_ing"));
				dto.setStd_regdate(rs.getString("std_regdate"));
				dto.setStd_duedate(rs.getString("std_duedate"));
				dto.setStd_views(rs.getString("std_views"));
				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setCp_name(rs.getString("cp_name"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));

				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("StudyDAO.listStudy");
			e.printStackTrace();
		}
		return null;
	}

	public int getTotalCount(HashMap<String, String> map) {
		try {
			String where = "";
			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format("select count(*) as cnt from vwStudy %s", where);

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt("cnt");
			}
		} catch (Exception e) {
			System.out.println("StudyDAO.getTotalCount");
			e.printStackTrace();
		}
		return 0;
	}

	public void updateReadcount(String seq) {
		try {
			String sql = "update tblStudy set std_views = std_views +1 where std_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.updateReadcount");
			e.printStackTrace();
		}
	}

	public int editStudy(StudyDTO dto) {
		try {
			String sql = "update tblStudy set std_title = ?, std_content = ?, std_ing = ?, cp_seq = ?, std_duedate = ? where std_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getStd_title());
			pstat.setString(2, dto.getStd_content());
			pstat.setString(3, dto.getStd_ing());
			pstat.setString(4, dto.getCp_seq());
			pstat.setString(5, dto.getStd_duedate());
			pstat.setString(6, dto.getStd_seq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("StudyDAO.editStudy");
			e.printStackTrace();
		}
		return 0;
	}

	public int delStudy(String seq) {
		try {
			/*
			 * String sql = "delete from tblComment where bseq = ?"; pstat =
			 * conn.prepareStatement(sql); pstat.setString(1, seq); pstat.executeUpdate();
			 * 
			 * sql = "delete from tblTagging where bseq = ?"; pstat =
			 * conn.prepareStatement(sql); pstat.setString(1, seq); pstat.executeUpdate();
			 */

			String sql = "delete from tblStudy where std_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.del");
			e.printStackTrace();
		}
		return 0;
	}

	public int getCount(String id) {
		try {
			String sql = "select count(*) as cnt from tblstudy where id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("StudyDAO.getCount");
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<StudyDTO> myStudy(HashMap<String, String> map) {
		try {
			String sql = String.format(
					"select * from (select a.*, rownum as rnum from (select * from vwstudy where id = '%s' order by std_regdate desc) a) where rnum between %s and %s", map.get("id"), map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<StudyDTO> list = new ArrayList<>();
			while (rs.next()) {
				StudyDTO dto = new StudyDTO();
				dto.setStd_seq(rs.getString("std_seq"));
				dto.setStd_title(rs.getString("std_title"));
				dto.setStd_content(rs.getString("std_content"));
				dto.setStd_ing(rs.getString("std_ing"));
				dto.setStd_regdate(rs.getString("std_regdate"));
				dto.setStd_duedate(rs.getString("std_duedate"));
				dto.setStd_views(rs.getString("std_views"));
				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setCp_name(rs.getString("cp_name"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setRnum(rs.getString("rnum"));

				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("StudyDAO.myStudy");
			e.printStackTrace();
		}
		return null;
	}

}

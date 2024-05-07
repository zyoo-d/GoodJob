package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.board.model.QnaBoardDTO;
import com.good.board.model.StudyDTO;
import com.test.util.DBUtil;

public class QnaBoardDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public QnaBoardDAO() {
		this.conn = DBUtil.open();
	}

	public void close() {

		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("QnaBoardDAO.close 오류");
			e.printStackTrace();
		}
	}

	public int listCount() {

		try {
			String sql = "select count(*) as cnt from tblQna";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			stat.close();

			int result = 0;

			if (rs.next()) {
				result = rs.getInt("cnt");
			}

			return result;

		} catch (Exception e) {
			System.out.println("질문게시판 글 개수 로드 실패");
			e.printStackTrace();
		}

		return 0;

	}

	public int getTotalCount(HashMap<String, String> map) {
		try {

			String where = "";
			int result = 0;
			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format("select count(*) as cnt from vwQna %s", where);

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				result = rs.getInt("cnt");
			}

			return result;

		} catch (Exception e) {
			System.out.println("게시글 갯수 로드 실패");
			e.printStackTrace();
		}
		return 0;
	}

	public int create(QnaBoardDTO dto) {

		int qna_seq = 0;

		try {

			String sql = "insert into tblQna (qna_seq, qna_title, qna_content, qna_regdate, qna_views, cp_seq, id) "
					+ "values(seqQna.nextVal, ?, ? , sysdate, 0 , (select cp_seq from tblCompany where cp_name= ? ) ,?)";

			pstat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstat.setString(1, dto.getQna_title());
			pstat.setString(2, dto.getQna_content());
			pstat.setString(3, dto.getCp_name());
			pstat.setString(4, dto.getId());

			int result = pstat.executeUpdate();

			if (result == 1) {
				String selectSql = "SELECT qna_seq FROM tblQna WHERE rowid = (SELECT max(rowid) FROM tblQna)";
				ResultSet rs = conn.createStatement().executeQuery(selectSql);
				if (rs.next()) {
					qna_seq = rs.getInt(1);
				}
				rs.close();
			}

		} catch (Exception e) {
			System.out.println("게시글 작성 실패");
			e.printStackTrace();
		}

		return qna_seq;

	}

	public ArrayList<QnaBoardDTO> listQna(HashMap<String, String> map) {

		try {

			String where = "";
			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format(
					"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM vwQna %s ORDER BY %s) a) WHERE rnum BETWEEN %s AND %s",
					where, map.get("sort"), map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<QnaBoardDTO> list = new ArrayList<>();

			while (rs.next()) {

				QnaBoardDTO dto = new QnaBoardDTO();
				dto.setQna_seq(rs.getInt("qna_seq"));
				dto.setQna_title(rs.getString("qna_title"));
				dto.setId(rs.getString("id"));
				dto.setQna_regdate(rs.getTimestamp("qna_regdate"));
				dto.setQna_views(rs.getInt("qna_views"));
				dto.setCp_name(rs.getString("cp_name"));
				dto.setCp_seq(rs.getInt("cp_seq"));
				dto.setQna_comments(rs.getInt("comment_count"));

				list.add(dto);

			}
			return list;

		} catch (Exception e) {
			System.out.println("질문게시판 게시글 목록 로드 실패");
			e.printStackTrace();
		}

		return null;

	}

	public QnaBoardDTO viewQna(int qna_seq) {

		try {

			String sql = "select * from vwQnaDetail where qna_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, qna_seq);

			rs = pstat.executeQuery();
			QnaBoardDTO dto = new QnaBoardDTO();

			if (rs.next()) {

				dto.setQna_seq(rs.getInt("qna_seq"));
				dto.setQna_title(rs.getString("qna_title"));
				dto.setQna_content(rs.getString("qna_content"));
				dto.setId(rs.getString("id"));
				dto.setQna_regdate(rs.getTimestamp("qna_regdate"));
				dto.setQna_views(rs.getInt("qna_views"));
				dto.setCp_seq(rs.getInt("cp_seq"));
				dto.setCp_name(rs.getString("cp_name"));

			}
			;
			return dto;

		} catch (Exception e) {
			System.out.println("게시글 상세보기 실패");
			e.printStackTrace();
		}

		return null;
	}

	public QnaBoardDTO get(String qna_seq) {

		try {

			String sql = "select * from tblQna q inner join tblCompany c on q.cp_seq = c.cp_seq where q.qna_seq = ? ";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, qna_seq);

			rs = pstat.executeQuery();

			QnaBoardDTO dto = new QnaBoardDTO();
			if (rs.next()) {

				dto.setQna_seq(rs.getInt("qna_seq"));
				dto.setQna_title(rs.getString("qna_title"));
				dto.setQna_content(rs.getString("qna_content"));
				dto.setId(rs.getString("id"));
				dto.setCp_name(rs.getString("cp_name"));

			}
			return dto;

		} catch (Exception e) {
			System.out.println("(수정)글 내용 불러오기 실패");
			e.printStackTrace();
		}

		return null;
	}

	public int edit(QnaBoardDTO dto) {

		try {

			String sql = "update tblQna set qna_title= ? , qna_content = ? , cp_seq = (select cp_seq from tblCompany where cp_name=?), qna_regdate = sysdate where qna_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getQna_title());
			pstat.setString(2, dto.getQna_content());
			pstat.setString(3, dto.getCp_name());
			pstat.setInt(4, dto.getQna_seq());

			int result = pstat.executeUpdate();
			return result;

		} catch (Exception e) {
			System.out.println("게시글 수정 실패");
			e.printStackTrace();
		}

		return 0;

	}

	public void updateReadcount(int qna_seq) {

		try {

			String sql = "update tblQna set qna_views =  qna_views +1 where qna_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, qna_seq);
			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("조회수 업로드 실패");
			e.printStackTrace();
		}

	}

	public ArrayList<QnaBoardDTO> myQna(HashMap<String, String> map) {
		try {
			String sql = String.format(
					"select * from (select a.*, rownum as rnum from (select * from vwQnaDetail where id = '%s' order by qna_regdate desc) a) where rnum between %s and %s",
					map.get("id"), map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<QnaBoardDTO> list = new ArrayList<>();
			while (rs.next()) {
				QnaBoardDTO dto = new QnaBoardDTO();
				dto.setQna_seq(rs.getInt("qna_seq"));
				dto.setQna_title(rs.getString("qna_title"));
				dto.setQna_content(rs.getString("qna_content"));
				dto.setId(rs.getString("id"));
				dto.setQna_regdate(rs.getTimestamp("qna_regdate"));
				dto.setQna_views(rs.getInt("qna_views"));
				dto.setCp_seq(rs.getInt("cp_seq"));
				dto.setCp_name(rs.getString("cp_name"));
				dto.setRnum(rs.getString("rnum"));

				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("QnaBoardDAO.myQna");
			e.printStackTrace();
		}
		return null;
	}

	public int getMyCount(String id) {
		try {
			String sql = "select count(*) as cnt from tblQna where id = ?";

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

}

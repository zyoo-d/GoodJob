package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.good.board.model.QnaBoardDTO;
import com.test.util.DBUtil;

public class QnaBoardDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public QnaBoardDAO() {
		this.conn = DBUtil.open();
	}
	
	public int listCount() {
		
		try {
			
			String sql = "select count(*) as cnt from tblQna";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getInt("cnt");
				
			}
			
			
			
		} catch (Exception e) {
			System.out.println("질문게시판 글 개수 로드 실패");
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
	
	public ArrayList<QnaBoardDTO> listQna(){
		
		try {
			
			String sql = "SELECT q.qna_seq, q.qna_title, q.id, q.qna_regdate, q.qna_views, c.cp_seq, c.cp_name, COUNT(qc.qna_cm_seq) AS comment_count "
			           + "FROM tblQna q "
			           + "INNER JOIN tblCompany c ON q.cp_seq = c.cp_seq "
			           + "LEFT JOIN tblQnAComment qc ON q.qna_seq = qc.qna_seq "
			           + "GROUP BY q.qna_seq, q.qna_title, q.id, q.qna_regdate, q.qna_views, c.cp_seq, c.cp_name "
			           + "ORDER BY q.qna_seq DESC";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<QnaBoardDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
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
			
			String sql = "SELECT q.qna_seq, q.qna_title, q.qna_content, q.id, q.qna_regdate, q.qna_views, c.cp_seq, c.cp_name "
					+ "FROM tblQna q "
					+ "INNER JOIN tblCompany c ON q.cp_seq = c.cp_seq "
					+ "LEFT JOIN tblQnAComment qc ON q.qna_seq = qc.qna_seq "
					+ "WHERE q.qna_seq = ? GROUP BY q.qna_seq, q.qna_title, q.qna_content, q.id, q.qna_regdate, q.qna_views, c.cp_seq, c.cp_name";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, qna_seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				QnaBoardDTO dto = new QnaBoardDTO();
				dto.setQna_seq(rs.getInt("qna_seq"));
				dto.setQna_title(rs.getString("qna_title"));
				dto.setQna_content(rs.getString("qna_content"));
				dto.setId(rs.getString("id"));
				dto.setQna_regdate(rs.getTimestamp("qna_regdate"));
				dto.setQna_views(rs.getInt("qna_views"));
				dto.setCp_seq(rs.getInt("cp_seq"));
				dto.setCp_name(rs.getString("cp_name"));
				
				return dto;
				
			}
			
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
			
			if(rs.next()) {
				QnaBoardDTO dto = new QnaBoardDTO();
				
				dto.setQna_seq(rs.getInt("qna_seq"));
				dto.setQna_title(rs.getString("qna_title"));
				dto.setQna_content(rs.getString("qna_content"));
				dto.setId(rs.getString("id"));
				dto.setCp_name(rs.getString("cp_name"));
				
				return dto;
			}
			
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
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("QnaBoardDAO.edit");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	
}

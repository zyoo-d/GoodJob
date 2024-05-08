package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.board.model.CommentDTO;
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
	
	public void close()  {
		
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
			
			if(rs.next()) {
				
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
			if(map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format("select count(*) as cnt from vwQna %s", where); 

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			
			
			if(rs.next()) {
				result =  rs.getInt("cnt");
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
	
	public ArrayList<QnaBoardDTO> listQna(HashMap<String, String> map){
		
		try {
			
			String where = "";
			if(map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format("SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM vwQna %s ORDER BY %s) a) WHERE rnum BETWEEN %s AND %s", where, map.get("sort"), map.get("begin"), map.get("end"));
			
			
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
			
			String sql = "select * from vwQnaDetail where qna_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, qna_seq);
			
			rs = pstat.executeQuery();
			QnaBoardDTO dto = new QnaBoardDTO();
			
			if(rs.next()) {
				
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

	public QnaBoardDTO getQna(String qna_seq) {
		
		try {
			
			String sql = "select * from tblQna q inner join tblCompany c on q.cp_seq = c.cp_seq where q.qna_seq = ? ";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, qna_seq);
			
			rs = pstat.executeQuery();
			
			QnaBoardDTO dto = new QnaBoardDTO();
			if(rs.next()) {
				
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

	public int delQna(String qna_seq) {
		
		try {

			String sql = "delete from tblQna where qna_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, qna_seq);
			
			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("게시글 삭제 실패");
			e.printStackTrace();
		}
		
		return 0;
		
	}

	
	
	public int addComment(CommentDTO dto) {
	    try {
	        // SQL 문장 작성
	        String sql = "INSERT INTO tblQnaComment (QNA_CM_SEQ, QNA_CM_CONTENT, QNA_CM_REGDATE, QNA_SEQ, QNA_CM_BSEQ, ID) VALUES (SEQQNACOMMENT.nextval, ?, SYSDATE, ?, NULL, ?)";
	        
	        // PreparedStatement 생성 및 값 설정
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, dto.getContent());
	        pstat.setString(2, dto.getBoard_seq());
	        pstat.setString(3, dto.getId());
	        
	    
	        return pstat.executeUpdate();
	   
	        
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
	    return 0;
	}

	public CommentDTO getComment(String QNA_SEQ) {

		try {
			
			String sql = "SELECT * FROM vwqnaComment WHERE QNA_SEQ = ? AND QNA_CM_SEQ = (SELECT MAX(QNA_CM_SEQ) FROM vwqnaComment WHERE QNA_SEQ = ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, QNA_SEQ);
			pstat.setString(2, QNA_SEQ);
					
			rs = pstat.executeQuery();
			
			
			if (rs.next()) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setCm_seq(rs.getString("QNA_CM_SEQ"));
				dto.setContent(rs.getString("QNA_CM_CONTENT"));
				dto.setRegdate(rs.getString("QNA_CM_REGDATE"));
				dto.setCm_bseq(rs.getString("QNA_CM_BSEQ"));
				dto.setId(rs.getString("ID"));
		        dto.setNickname(rs.getString("NICKNAME"));
				
				return dto;				
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<CommentDTO> listComment(int QNA_seq) {
	    try {
	        
	    	String sql = "SELECT * FROM (SELECT t.*, ROW_NUMBER() OVER (ORDER BY t.QNA_CM_SEQ DESC) AS rn FROM vwQNAComment t WHERE t.QNA_SEQ = ?) WHERE rn BETWEEN 1 AND 10";
	    	pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, QNA_seq);
	        rs = pstat.executeQuery();
	        ArrayList<CommentDTO> clist = new ArrayList<>();
	        	        
	        while (rs.next()) {
	            CommentDTO dto = new CommentDTO();
	            dto.setCm_seq(rs.getString("QNA_CM_SEQ"));
	            dto.setContent(rs.getString("QNA_CM_CONTENT"));
	            dto.setRegdate(rs.getString("QNA_CM_REGDATE"));
	            dto.setBoard_seq(rs.getString("QNA_SEQ"));
	            dto.setCm_bseq(rs.getString("QNA_CM_BSEQ"));
	            dto.setId(rs.getString("ID"));
	            dto.setNickname(rs.getString("NICKNAME"));
	            
	            clist.add(dto);	        
	        }
	        return clist;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public ArrayList<CommentDTO> listMoreComment(String bseq, int commentBegin) {
		//queryParamListReturn
		try {
			
			int startRow = commentBegin;
			int endRow = startRow + 9;

			String sql = "SELECT * FROM (SELECT t.*, ROW_NUMBER() OVER (ORDER BY t.QNA_CM_SEQ DESC) AS rn FROM vwQNAComment t WHERE t.QNA_SEQ = ?) WHERE rn BETWEEN ? AND ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, bseq);
			pstat.setInt(2, startRow);
			pstat.setInt(3, endRow);			
			
			
			rs = pstat.executeQuery();
			
			ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
			
			while (rs.next()) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setCm_seq(rs.getString("qna_cm_seq"));
				dto.setContent(rs.getString("qna_cm_content"));
				dto.setRegdate(rs.getString("qna_cm_regdate"));
				dto.setCm_bseq(rs.getString("qna_cm_bseq"));
				dto.setId(rs.getString("id"));
		        dto.setNickname(rs.getString("nickname"));
				
				list.add(dto);				
			}	
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	
	}

	public int delComment(String seq) {
		//queryParamNoReturn
		try {
			
			String sql = "delete from tblqnaComment where qna_cm_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.delComment");
			e.printStackTrace();
		}
		
		return 0;
	
	}

	public int editComment(CommentDTO dto) {
		//queryParamNoReturn
		try {

			String sql = "update tblqnaComment set qna_cm_content = ? where qna_cm_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getContent());
			pstat.setString(2, dto.getCm_seq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.editComment");
			e.printStackTrace();
		}
	
		return 0;
	}
	
}

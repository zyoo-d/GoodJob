package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.board.model.CommentDTO;
import com.good.board.model.StudyDTO;
import com.test.util.DBUtil;
/**
 * StudyDAO 클래스는 공부 관련 데이터 액세스 오브젝트로, 데이터베이스의 공부 관련 테이블에 접근하여
 * 데이터를 조회, 추가, 수정, 삭제하는 기능을 제공합니다.
 */
public class StudyDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	/**
     * 데이터베이스 연결을 초기화합니다.
     */
	public StudyDAO() {
		this.conn = DBUtil.open();
	}
	/**
     * 데이터베이스 연결을 종료합니다.
     */
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("StudyDAO.close 오류");
			e.printStackTrace();
		}
	}
	/**
     * 새로운 공부 세션을 데이터베이스에 추가합니다.
     *
     * @param dto 추가할 공부 세션의 데이터를 담고 있는 StudyDTO 객체
     * @return 데이터 추가 성공 여부를 나타내는 정수 (1: 성공, 0: 실패)
     */
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
	/**
     * 특정 공부 세션의 상세 정보를 조회합니다.
     *
     * @param seq 공부 세션의 식별자
     * @return 조회된 공부 세션 정보가 담긴 StudyDTO 객체
     */
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
	/**
     * 데이터베이스에서 모든 공부 세션 목록을 조회합니다.
     * 페이징 처리를 위한 시작 인덱스와 종료 인덱스를 매개변수로 받아 해당 범위의 공부 세션 정보를 리스트로 반환합니다.
     *
     * @param map 페이지 처리와 검색정보가 담긴 HashMap
     * @return 조회된 공부 세션 정보 목록을 담고 있는  ArrayList&lt;StudyDTO&gt;
     */
	public ArrayList<StudyDTO> listStudy(HashMap<String, String> map) {
		try {
			String where = "";
			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format(
					"select * from (select a.*, rownum as rnum from (select * from vwstudy %s order by %s) a) where rnum between %s and %s",
					where, map.get("sort"), map.get("begin"), map.get("end"));
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
				dto.setCm_count(rs.getString("cm_count"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("StudyDAO.listStudy");
			e.printStackTrace();
		}
		return null;
	}
	/**
     * 데이터베이스에서 모든 공부 세션의 갯수를 조회합니다.
     *
     * @param map 검색정보가 담긴 HashMap
     * @return 조회된 공부 세션 정보 갯수
     */
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

	/**
     * 특정 공부 세션의 조회수를 증가시킵니다.
     *
     * @param seq 공부 세션의 식별자
     */
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
	/**
     * 특정 공부 세션의 상세 정보를 수정합니다.
     *
     * @param dto 수정할 공부 세션의 데이터를 담고 있는 StudyDTO 객체
     * @return 데이터 수정 성공 여부를 나타내는 정수 (1: 성공, 0: 실패)
     */
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
	/**
     * 특정 공부 세션의 정보를 삭제합니다.
     *
     * @param seq 공부 세션의 식별자
     * @return 데이터 삭제 성공 여부를 나타내는 정수 (1: 성공, 0: 실패)
     */
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

	/**
     * 특정 사용자가 업로드한 공부 세션 횟수를 조회합니다.
     *
     * @param id 사용자 id
     * @return 사용자가 업로드한 게시글 총 횟수
     */
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
	/**
     * 데이터베이스에서 특정 사용자의 모든 공부 세션 목록을 조회합니다.
     * 페이징 처리를 위한 시작 인덱스와 종료 인덱스를 매개변수로 받아 해당 범위의 공부 세션 정보를 리스트로 반환합니다.
     *
     * @param map 페이지 처리와 id가 담긴 HashMap
     * @return 조회된 공부 세션 정보 목록을 담고 있는  ArrayList&lt;StudyDTO&gt;
     */
	public ArrayList<StudyDTO> myStudy(HashMap<String, String> map) {
		try {
			String sql = String.format(
					"select * from (select a.*, rownum as rnum from (select * from vwstudy where id = '%s' order by std_regdate desc) a) where rnum between %s and %s",
					map.get("id"), map.get("begin"), map.get("end"));

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
	/**
     * 새로운 댓글을 데이터베이스에 추가합니다.
     *
     * @param dto 추가할 댓글의 데이터를 담고 있는 CommentDTO 객체
     * @return 데이터 추가 성공 여부를 나타내는 정수 (1: 성공, 0: 실패)
     */
	public int addComment(CommentDTO dto) {
	    try {
	        // SQL 문장 작성
	        String sql = "INSERT INTO tblStdComment (STD_CM_SEQ, STD_CM_CONTENT, STD_CM_REGDATE, STD_SEQ, STD_CM_BSEQ, ID) VALUES (SEQSTDCOMMENT.nextval, ?, SYSDATE, ?, NULL, ?)";
	        
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
	/**
     * 공부 세션과 관련된 댓글 목록을 조회합니다.
     *
     * @param std_seq 공부 세션의 식별자
     * @return 해당 공부 세션의 댓글 목록이 담긴  ArrayList&lt;CommentDTO&gt;
     */
	public ArrayList<CommentDTO> listComment(String std_seq) {
	    try {
	        
	    	String sql = "SELECT * FROM (SELECT t.*, ROW_NUMBER() OVER (ORDER BY t.STD_CM_SEQ DESC) AS rn FROM vwstdComment t WHERE t.STD_SEQ = ?) WHERE rn BETWEEN 1 AND 10";
	    	pstat = conn.prepareStatement(sql);
	        pstat.setString(1, std_seq);
	        rs = pstat.executeQuery();
	        ArrayList<CommentDTO> clist = new ArrayList<>();
	        	        
	        while (rs.next()) {
	            CommentDTO dto = new CommentDTO();
	            dto.setCm_seq(rs.getString("STD_CM_SEQ"));
	            dto.setContent(rs.getString("STD_CM_CONTENT"));
	            dto.setRegdate(rs.getString("STD_CM_REGDATE"));
	            dto.setBoard_seq(rs.getString("STD_SEQ"));
	            dto.setCm_bseq(rs.getString("STD_CM_BSEQ"));
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
	/**
     * 특정 댓글을 데이터베이스에서 조회합니다.
     *
     * @param std_seq 댓글 번호
     * @return 조회된 댓글 정보를 담은 CommentDTO
     */
	public CommentDTO getComment(String std_seq) {

		try {
			
			String sql = "SELECT * FROM vwstdComment WHERE STD_SEQ = ? AND STD_CM_SEQ = (SELECT MAX(STD_CM_SEQ) FROM vwstdComment WHERE STD_SEQ = ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, std_seq);
			pstat.setString(2, std_seq);
					
			rs = pstat.executeQuery();
			
			
			if (rs.next()) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setCm_seq(rs.getString("std_cm_seq"));
				dto.setContent(rs.getString("std_cm_content"));
				dto.setRegdate(rs.getString("std_cm_regdate"));
				dto.setCm_bseq(rs.getString("std_cm_bseq"));
				dto.setId(rs.getString("id"));
		        dto.setNickname(rs.getString("nickname"));
				
				return dto;				
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
     * 공부 세션과 관련된 댓글 목록을 더 조회합니다.
     *
     * @param bseq 공부 세션의 식별자
     * @param commentBegin 추가로 불러올 댓글의 시작 번호
     * @return 해당 공부 세션의 댓글 목록이 담긴 ArrayList&lt;CommentDTO&gt;
     */
	public ArrayList<CommentDTO> listMoreComment(String bseq, int commentBegin) {
		//queryParamListReturn
		try {
			
			int startRow = commentBegin;
			int endRow = startRow + 9;

			String sql = "SELECT * FROM (SELECT t.*, ROW_NUMBER() OVER (ORDER BY t.STD_CM_SEQ DESC) AS rn FROM vwstdComment t WHERE t.STD_SEQ = ?) WHERE rn BETWEEN ? AND ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, bseq);
			pstat.setInt(2, startRow);
			pstat.setInt(3, endRow);			
			
			
			rs = pstat.executeQuery();
			
			ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
			
			while (rs.next()) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setCm_seq(rs.getString("std_cm_seq"));
				dto.setContent(rs.getString("std_cm_content"));
				dto.setRegdate(rs.getString("std_cm_regdate"));
				dto.setCm_bseq(rs.getString("std_cm_bseq"));
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
	/**
     * 댓글을 수정합니다.
     *
     * @param dto 수정할 댓글의 데이터를 담고 있는 CommentDTO 객체
     * @return 데이터 수정 성공 여부를 나타내는 정수 (1: 성공, 0: 실패)
     */
	public int editComment(CommentDTO dto) {
		//queryParamNoReturn
		try {

			String sql = "update tblstdComment set std_cm_content = ? where std_cm_seq = ?";

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
	/**
     * 댓글을 삭제합니다.
     *
     * @param seq 삭제할 댓글의 시퀀스
     * @return 데이터 삭제 성공 여부를 나타내는 정수 (1: 성공, 0: 실패)
     */
	public int delComment(String seq) {
		
		//queryParamNoReturn
		try {
			
			String sql = "delete from tblstdComment where std_cm_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.delComment");
			e.printStackTrace();
		}
		
		return 0;
		
	}



}

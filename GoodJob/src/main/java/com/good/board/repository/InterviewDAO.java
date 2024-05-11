package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.board.model.InterviewDTO;
import com.good.board.model.StudyDTO;
import com.test.util.DBUtil;

public class InterviewDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public InterviewDAO() {
		this.conn = DBUtil.open();
	}
	
	public void close() {

		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("InterviewDAO.close 오류");
			e.printStackTrace();
		}
	}

	public ArrayList<InterviewDTO> list(HashMap<String, String> map) {
		try {

			String where = "";
			if (map.get("search").equals("y")) {
				where = String.format("and ITV_CPNAME like '%%%s%%'", map.get("cp_name"));
			}
			String sql = String.format("select ITV_SEQ, ITV_CPNAME, TO_CHAR(ITV_MEETDATE, 'YYYY-MM-DD') AS ITV_MEETDATE, ITV_EVALUATION, TO_CHAR(ITV_REGDATE, 'YYYY-MM-DD') AS ITV_REGDATE, CP_SEQ, ID, ITV_DIFFICULTY, ITV_CATEGORY, ITV_CAREER, ITV_PERSONNEL, ITV_QUESTION, ITV_TIP, ITV_WHETHER, IMAGE, rnum from (select a.*, rownum as rnum from (select * from vwinterview where ITV_CONFIRM = 1 %s) a) where rnum between %s and %s", where, map.get("begin"), map.get("end"));
			
			// where 컬럼 1
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<InterviewDTO> list = new ArrayList<>();
			while (rs.next()) {

				InterviewDTO dto = new InterviewDTO();
				   
			    dto.setITV_SEQ(rs.getString("ITV_SEQ"));
			    dto.setITV_CPNAME(rs.getString("ITV_CPNAME")); // 회사 이름 세팅
			    dto.setITV_MEETDATE(rs.getString("ITV_MEETDATE")); // 면접 날짜 세팅
			    dto.setITV_EVALUATION(rs.getString("ITV_EVALUATION")); // 면접 평가 세팅
			    dto.setITV_REGDATE(rs.getString("ITV_REGDATE")); // 작성일자 세팅
			    dto.setCP_SEQ(rs.getString("CP_SEQ")); // 회사 번호 세팅
			    dto.setID(rs.getString("ID")); // 작성자 id 세팅
			    dto.setITV_DIFFICULTY(rs.getString("ITV_DIFFICULTY")); // 난이도 세팅
			    dto.setITV_CATEGORY(rs.getString("ITV_CATEGORY")); // 카테고리 세팅
			    dto.setITV_CAREER(rs.getString("ITV_CAREER")); // 커리어 세팅
			    dto.setITV_PERSONNEL(rs.getString("ITV_PERSONNEL")); // 면접 인원 세팅
			    dto.setITV_QUESTION(rs.getString("ITV_QUESTION")); // 면접 질문 세팅
			    dto.setITV_TIP(rs.getString("ITV_TIP")); // 면접 팁 세팅
			    dto.setITV_WHETHER(rs.getString("ITV_WHETHER")); // 면접 합격 여부 세팅
				dto.setIMAGE(rs.getString("IMAGE"));
				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int Write(InterviewDTO dto) {
	    try {
	        String sql = "INSERT INTO tblInterview (ITV_SEQ, ITV_CPNAME, ITV_MEETDATE, ITV_EVALUATION, ITV_REGDATE, CP_SEQ, ID, ITV_DIFFICULTY, ITV_CATEGORY, ITV_CAREER, ITV_PERSONNEL, ITV_QUESTION, ITV_TIP, ITV_WHETHER, ITV_CONFIRM) " +
	                     "VALUES (seqinterview.NEXTVAL, ?,  TO_DATE(?, 'YYYY-MM-DD'), ?, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
	        
	        pstat = conn.prepareStatement(sql);
	        
	        pstat.setString(1, dto.getITV_CPNAME());
	        pstat.setString(2, dto.getITV_MEETDATE());
	        pstat.setString(3, dto.getITV_EVALUATION());
	        pstat.setString(4, dto.getCP_SEQ());
	        pstat.setString(5, dto.getID());
	        pstat.setString(6, dto.getITV_DIFFICULTY());
	        pstat.setString(7, dto.getITV_CATEGORY());
	        pstat.setString(8, dto.getITV_CAREER());
	        pstat.setString(9, dto.getITV_PERSONNEL());
	        pstat.setString(10, dto.getITV_QUESTION());
	        pstat.setString(11, dto.getITV_TIP());
	        pstat.setString(12, dto.getITV_WHETHER());
	        
	        
	        	
	        int result = pstat.executeUpdate();
	        
	        return result;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return 0;
	}
	
public InterviewDTO getItv(String itv_seq) {
		
		try {

			String sql = "select ITV_SEQ, ITV_CPNAME, TO_CHAR(ITV_MEETDATE, 'YYYY-MM-DD') AS ITV_MEETDATE, ITV_EVALUATION, TO_CHAR(ITV_REGDATE, 'YYYY-MM-DD') AS ITV_REGDATE, CP_SEQ, ID, ITV_DIFFICULTY, ITV_CATEGORY, ITV_CAREER, ITV_PERSONNEL, ITV_QUESTION, ITV_TIP, ITV_WHETHER, IMAGE from vwinterview where itv_seq = ? ";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, itv_seq);

			rs = pstat.executeQuery();
			
			InterviewDTO dto = new InterviewDTO();
			
			if (rs.next()) {

			    dto.setITV_SEQ(rs.getString("ITV_SEQ"));
			    dto.setITV_CPNAME(rs.getString("ITV_CPNAME")); // 회사 이름 세팅
			    dto.setITV_MEETDATE(rs.getString("ITV_MEETDATE")); // 면접 날짜 세팅
			    dto.setITV_EVALUATION(rs.getString("ITV_EVALUATION")); // 면접 평가 세팅
			    dto.setITV_REGDATE(rs.getString("ITV_REGDATE")); // 작성일자 세팅
			    dto.setCP_SEQ(rs.getString("CP_SEQ")); // 회사 번호 세팅
			    dto.setID(rs.getString("ID")); // 작성자 id 세팅
			    dto.setITV_DIFFICULTY(rs.getString("ITV_DIFFICULTY")); // 난이도 세팅
			    dto.setITV_CATEGORY(rs.getString("ITV_CATEGORY")); // 카테고리 세팅
			    dto.setITV_CAREER(rs.getString("ITV_CAREER")); // 커리어 세팅
			    dto.setITV_PERSONNEL(rs.getString("ITV_PERSONNEL")); // 면접 인원 세팅
			    dto.setITV_QUESTION(rs.getString("ITV_QUESTION")); // 면접 질문 세팅
			    dto.setITV_TIP(rs.getString("ITV_TIP")); // 면접 팁 세팅
			    dto.setITV_WHETHER(rs.getString("ITV_WHETHER")); // 면접 합격 여부 세팅
				dto.setIMAGE(rs.getString("IMAGE"));

			}
			return dto;

		} catch (Exception e) {
			System.out.println("(수정)글 내용 불러오기 실패");
			e.printStackTrace();
		}

		return null;
	}

public int edit(InterviewDTO dto) {
	   try {
		   String sql = "UPDATE tblInterview " +
		             "SET ITV_CPNAME = ?, " +
		             "    ITV_MEETDATE = TO_DATE(?, 'YYYY-MM-DD'), " +
		             "    ITV_EVALUATION = ?, " +
		             "    CP_SEQ = ?, " +
		             "    ITV_DIFFICULTY = ?, " +
		             "    ITV_CATEGORY = ?, " +
		             "    ITV_CAREER = ?, " +
		             "    ITV_PERSONNEL = ?, " +
		             "    ITV_QUESTION = ?, " +
		             "    ITV_TIP = ?, " +
		             "    ITV_WHETHER = ? " +	
		             "WHERE ITV_SEQ = ?";
	        //sql에서 조건절로  리뷰승인 컬럼추가하고 
	        
	        pstat = conn.prepareStatement(sql);
	        
	        pstat.setString(1, dto.getITV_CPNAME());
	        pstat.setString(2, dto.getITV_MEETDATE());
	        pstat.setString(3, dto.getITV_EVALUATION());
	        pstat.setString(4, dto.getCP_SEQ());
	        pstat.setString(5, dto.getITV_DIFFICULTY());
	        pstat.setString(6, dto.getITV_CATEGORY());
	        pstat.setString(7, dto.getITV_CAREER());
	        pstat.setString(8, dto.getITV_PERSONNEL());
	        pstat.setString(9, dto.getITV_QUESTION());
	        pstat.setString(10, dto.getITV_TIP());
	        pstat.setString(11, dto.getITV_WHETHER());
	        pstat.setString(12, dto.getITV_SEQ()); 
	        
	        
	        
	        int result = pstat.executeUpdate();
	        
	        return result;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return 0;
	
}

public int delQna(String itv_seq) {
	try {

		String sql = "delete from tblInterview where itv_seq = ?";

		pstat = conn.prepareStatement(sql);
		pstat.setString(1, itv_seq);
		
		return pstat.executeUpdate();

	} catch (Exception e) {
		System.out.println("게시글 삭제 실패");
		e.printStackTrace();
	}
	
	return 0;
}

public int getTotalCount(HashMap<String, String> map) {
	try {

		String where = "";
		int result = 0;
		if (map.get("search").equals("y")) {
			where = String.format("where ITV_CPNAME like '%%%s%%'", map.get("cp_name"));
		}

		String sql = String.format("select count(*) as cnt from vwInterview %s", where);

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

public int getCount(String id) {
	try {
		String sql = "select count(*) as cnt from vwinterview where id = ?";

		pstat = conn.prepareStatement(sql);
		pstat.setString(1, id);

		rs = pstat.executeQuery();

		if (rs.next()) {
			return rs.getInt("cnt");
		}

	} catch (Exception e) {
		System.out.println("InterviewDAO.getCount");
		e.printStackTrace();
	}
	return 0;
}

public ArrayList<InterviewDTO> myInterview(HashMap<String, String> map) {
	try {
		String sql = String.format(
				"select ITV_SEQ, ITV_CPNAME, TO_CHAR(ITV_MEETDATE, 'YYYY-MM-DD') AS ITV_MEETDATE, TO_CHAR(ITV_REGDATE, 'YYYY-MM-DD') AS ITV_REGDATE, ITV_WHETHER, ITV_CONFIRM, rnum from (select a.*, rownum as rnum from (select * from vwinterview where id = '%s' order by ITV_REGDATE desc) a) where rnum between %s and %s",
				map.get("id"), map.get("begin"), map.get("end"));

		stat = conn.createStatement();
		rs = stat.executeQuery(sql);

		ArrayList<InterviewDTO> list = new ArrayList<>();
		while (rs.next()) {
			InterviewDTO dto = new InterviewDTO();
			dto.setITV_SEQ(rs.getString("ITV_SEQ"));
			dto.setITV_CPNAME(rs.getString("ITV_CPNAME"));
			dto.setITV_MEETDATE(rs.getString("ITV_MEETDATE"));
			dto.setITV_REGDATE(rs.getString("ITV_REGDATE"));
			dto.setITV_WHETHER(rs.getString("ITV_WHETHER"));
			dto.setITV_CONFIRM(rs.getString("ITV_CONFIRM"));
			dto.setRnum(rs.getString("rnum"));

			list.add(dto);
		}
		return list;

	} catch (Exception e) {
		System.out.println("InterviewDAO.myInterview");
		e.printStackTrace();
	}
	return null;
}
	

}
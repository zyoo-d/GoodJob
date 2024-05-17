package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.company.model.ReviewDTO;
import com.test.util.DBUtil;

/**
 * TagDAO 클래스는 태그 관련 데이터베이스 작업을 수행합니다.
 */
public class TagDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	/**
     * 기본 생성자입니다. 데이터베이스 연결을 수행합니다.
     */
	public TagDAO() {
		this.conn = DBUtil.open();
	}

	/**
     * 데이터베이스 연결을 닫습니다.
     */
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("TagDAO.close 오류");
			e.printStackTrace();
		}
	}
	/**
	 * 해시태그가 이미 존재하는지 확인합니다.
	 *
	 * @param tagKeyword 해시태그 키워드
	 * @return 해시태그 존재 여부
	 */
	public boolean existHashtag(String tagKeyword) {
		try {

			String sql = "select count(*) as cnt from tblTag where tag_keyword = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, tagKeyword);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt") == 1 ? false : true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	/**
	 * 새로운 해시태그를 추가합니다.
	 *
	 * @param tagKeyword 해시태그 키워드
	 */
	public void addHashtag(String tagKeyword) {
		try {

			String sql = "insert into tblTag (tag_seq, tag_keyword) values (seqTag.nextVal, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, tagKeyword);

			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.addHashtag");
			e.printStackTrace();
		}
		
	}
	/**
	 * 해시태그의 시퀀스 번호를 가져옵니다.
	 *
	 * @param tagKeyword 해시태그 키워드
	 * @return 해시태그의 시퀀스 번호
	 */
	public String getHseq(String tagKeyword) {
		try {
			String sql = "select tag_seq from tblTag where tag_keyword = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, tagKeyword);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getString("tag_seq");
			}

		} catch (Exception e) {
			System.out.println("TagDAO.getHseq");
			e.getStackTrace();
		}
		
		return null;

	}
	/**
	 * 리뷰와 태그의 관계를 추가합니다.
	 *
	 * @param map 태그 정보가 담긴 HashMap
	 */
	public void addTagging(HashMap<String, String> map) {
		try {

			String sql = "insert into tblReviewTag (rv_tag_seq, tag_seq, cp_rv_seq) values (SEQREVIEWTAG.nextVal, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("tag_seq"));
			pstat.setString(2, map.get("cp_rv_seq"));

			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("TagDAO.addTagging");
			e.printStackTrace();
		}
		
	}
	/**
	 * 리뷰와 태그의 관계를 추가합니다.
	 *
	 * @param map       태그 정보가 담긴 HashMap
	 * @param cp_rv_seq 리뷰 시퀀스 번호
	 */
	public void addTagging(HashMap<String, String> map, String cp_rv_seq) {
	    try {
	        String sql = "insert into tblReviewTag (rv_tag_seq, tag_seq, cp_rv_seq) values (SEQREVIEWTAG.nextVal, ?, ?)";

	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, map.get("tag_seq"));
	        pstat.setString(2, cp_rv_seq);

	        pstat.executeUpdate();

	    } catch (Exception e) {
	        System.out.println("TagDAO.addTagging");
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 다음 리뷰 시퀀스 번호를 가져옵니다.
	 *
	 * @return 다음 리뷰 시퀀스 번호
	 */
	public String getCp_rv_seq() {
		try {

			String sql = "select max(cp_rv_seq) as cp_rv_seq from tblCompanyReview";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				int seq = Integer.parseInt(rs.getString("cp_rv_seq"));
				String cp_rv_seq = seq +1 +""; 
				
				return cp_rv_seq;
			}

		} catch (Exception e) {
			System.out.println("TagDAO.getCp_rv_seq");
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 리뷰와 태그의 관계를 삭제합니다.
	 *
	 * @param map 태그 정보가 담긴 HashMap
	 */
	public void delTagging(HashMap<String, String> map) {
	    try {
	        String sql = String.format("DELETE FROM tblReviewTag WHERE cp_rv_seq = %s AND tag_seq = (SELECT tag_seq FROM tblTag WHERE tag_keyword = '%s')",
	                map.get("cp_rv_seq"), map.get("tag_keyword"));

	        pstat = conn.prepareStatement(sql);

	        System.out.println(map);

	        pstat.executeUpdate();
	    } catch (Exception e) {
	        System.out.println("TagDAO.delTagging");
	        e.printStackTrace();
	    }
	}
	/**
	 * 리뷰와 태그의 관계가 이미 존재하는지 확인합니다.
	 *
	 * @param map 태그 정보가 담긴 HashMap
	 * @return 태그 존재 여부
	 */
	public boolean existTagging(HashMap<String, String> map) {
		try {
			String sql = "select count(*) as cnt from tblReviewTag where cp_rv_seq=? and tag_seq =?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("cp_rv_seq"));
			pstat.setString(2, map.get("tag_seq"));

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt") == 0? true : false;
			}

		} catch (Exception e) {
			System.out.println("TagDAO.existTagging");
			e.getStackTrace();
		}
		return false;
	}
	/**
	 * 리뷰에 해당하는 태그 목록을 가져옵니다.
	 *
	 * @param cp_rv_seq 리뷰 시퀀스 번호
	 * @return 리뷰에 해당하는 태그 목록
	 */
	public ArrayList<String> getReviewTag(String cp_rv_seq) {
		
		try {
			ArrayList<String> reviewTagList = new ArrayList<>();
			
			String sql = "select * from vwReviwTag where cp_rv_seq =?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_rv_seq);
			rs = pstat.executeQuery();
			
			while (rs.next()) {
	            String tag_keyword = rs.getString("tag_keyword");
	            reviewTagList.add(tag_keyword);
	        }
			System.out.println(reviewTagList);
			 return reviewTagList;
	    } catch (Exception e) {
	        System.out.println("TagDAO.getReviewTag");
	        e.printStackTrace();
	    }
		
		return null;
	}

	

	

}
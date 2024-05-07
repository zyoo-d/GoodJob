package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.company.model.ReviewDTO;
import com.test.util.DBUtil;

public class ReviewDAO {

		private Connection conn;
		private Statement stat;
		private PreparedStatement pstat;
		private ResultSet rs;

		public ReviewDAO() {
			this.conn = DBUtil.open();
		}
		public void close()  {

	        try {
	            this.conn.close();
	        } catch (Exception e) {
	            System.out.println("ReviewDAO.close 오류");
	            e.printStackTrace();
	        }
	    }
		/**
		 * 지유)등록된 리뷰수 불러오는 메서드
		 * @return 리뷰수
		 */
		public int countRiview(String input) {
			
			try {

				String sql = "select count(*) as cnt from tblCompanyReview where cp_seq = ?";

				pstat = conn.prepareStatement(sql);
				String cp_seq = input;
				
				pstat.setString(1, cp_seq);
				rs = pstat.executeQuery();
					
				while (rs.next()) {
				
					int cnt = rs.getInt("cnt");
					System.out.println(cnt);
					return cnt;
				}

			} catch (Exception e) {
				System.out.println("ReviewDAO.countRiview");
				e.printStackTrace();
			}
			
			return 0;
			
		}
		
		/**
		 * 지유)기업리뷰 목록 조회하는 메서드
		 * @param cp_seq
		 * @return
		 */
		public ArrayList<ReviewDTO> listReview(String cp_seq){
			
			try {
			
			String sql = "select * from vwCompanyReview where cp_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,cp_seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<ReviewDTO> listReview = new ArrayList<ReviewDTO>(); 
			while (rs.next()) {
		
				ReviewDTO dto = new ReviewDTO();
					
				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setId(rs.getString("id"));
				dto.setCp_rv_seq(rs.getString("cp_rv_seq"));
				dto.setSalary_score(rs.getDouble("salary_score"));
				dto.setWelfare_score(rs.getDouble("welfare_score"));
				dto.setStability_score(rs.getDouble("stability_score"));
				dto.setCulture_score(rs.getDouble("culture_score"));
				dto.setGrowth_score(rs.getDouble("growth_score"));
				dto.setLinereview(rs.getString("linereview"));
				dto.setGood(rs.getString("good"));
				dto.setBad(rs.getString("bad"));
				dto.setCp_rv_regdate(rs.getString("cp_rv_regdate"));
				dto.setCp_rv_confirm(rs.getInt("cp_rv_confirm"));
				dto.setNickname(rs.getString("nickname"));

				
				listReview.add(dto);			
				}	
				return listReview;
				
			} catch (Exception e) {
				System.out.println("ReviewDAO.listReview");
				e.printStackTrace();
			}
			
			
			return null;
		}
		
		/**
		 * 지유)태그리스트를 호출하는 메서드
		 * @param tmap
		 * @return
		 */
		public ArrayList<ReviewDTO> tagList() {
		    ArrayList<ReviewDTO> tagList = new ArrayList<>();
		    String sql = "select * from vwCompanyReview";

		    try (Statement stat = conn.createStatement();
		         ResultSet rs = stat.executeQuery(sql)) {
		        while (rs.next()) {
		            ReviewDTO dto = new ReviewDTO();
		            dto.setCp_seq(rs.getString("cp_seq"));

		            String tagSql = "SELECT t.tag_keyword FROM tblCompanyReview cr "
		                    + "INNER JOIN tblReviewTag rt ON cr.cp_rv_seq = rt.cp_rv_seq "
		                    + "INNER JOIN tblTag t ON t.tag_seq = rt.tag_seq WHERE cr.cp_seq = ? GROUP BY t.tag_keyword ORDER BY COUNT(*) DESC";

		            try (PreparedStatement pstat = conn.prepareStatement(tagSql)) {
		                pstat.setString(1, rs.getString("cp_seq"));
		                try (ResultSet tagRs = pstat.executeQuery()) {
		                    ArrayList<String> tlist = new ArrayList<>();
		                    while (tagRs.next()) {
		                        tlist.add(tagRs.getString("tag_keyword"));
		                    }
		                    dto.setTag_keyword(tlist);
		                }
		            }
		            tagList.add(dto);
		        }
		    } catch (Exception e) {
		        System.out.println("ReviewDAO.tagList");
		        e.printStackTrace();
		        return null;
		    }
		    return tagList;
		}
		
}

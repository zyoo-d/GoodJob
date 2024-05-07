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
		 * 지유)기업리뷰 목록과 태그를 조회하는 메서드
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
				dto.setSalary_score(rs.getInt("salary_score"));
				dto.setWelfare_score(rs.getInt("welfare_score"));
				dto.setLngvt_score(rs.getInt("lngvt_score"));
				dto.setCulture_score(rs.getInt("culture_score"));
				dto.setGrowth_score(rs.getInt("growth_score"));
				dto.setLinereview(rs.getString("linereview"));
				dto.setGood(rs.getString("good"));
				dto.setBad(rs.getString("bad"));
				dto.setCp_rv_regdate(rs.getString("cp_rv_regdate"));
				dto.setCp_rv_confirm(rs.getInt("cp_rv_confirm"));
				dto.setNickname(rs.getString("nickname"));

				//
				sql = "select t.tag_keyword from tblCompanyReview cr inner join tblReviewTag rt on cr.cp_rv_seq = rt.cp_rv_seq inner join tblTag t on t.tag_seq=rt.tag_seq where cr.cp_seq=?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1,cp_seq);
				rs = pstat.executeQuery();
				
				ArrayList<String> tlist = new ArrayList<String>();
				
				while(rs.next()) {
					tlist.add(rs.getString("tag_keyword"));
				}
				
				dto.setTag_keyword(tlist);
				listReview.add(dto);			
				}	
				return listReview;
				
			} catch (Exception e) {
				System.out.println("ReviewDAO.rlist");
				e.printStackTrace();
			}
			
			
			return null;
		}
		
		/**
		 * 지유)태그리스트를 호출하는 메서드
		 * @param tmap
		 * @return
		 */
	/*	public ArrayList<ReviewDTO> tagList(HashMap<String,String> tmap){
			
			try {
			
			String sql = "";
			sql = String.format("select t.tag_keyword from tblCompanyReview cr inner join tblReviewTag rt on cr.cp_rv_seq = rt.cp_rv_seq inner join tblTag t on t.tag_seq=rt.tag_seq where cr.cp_seq=?")
					,tmap.get("cp_seq");
					
					
			
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			
			ArrayList<ReviewDTO> taglist = new ArrayList<ReviewDTO>();
			
			while (rs.next()) {
		
				ReviewDTO dto = new ReviewDTO();
				dto.setTag_keyword(rs.getString("tag_keyword"));
				taglist.add(dto);
				}	
				return taglist;
				
			} catch (Exception e) {
				System.out.println("ReviewDAO.rlist");
				e.printStackTrace();
			}
			
			
			return null;
		}*/
		
}

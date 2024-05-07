package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
				
				rs = pstat.executeQuery(sql);
					
				while (rs.next()) {
				
					int cnt = rs.getInt("cnt");
					System.out.println(cnt);
					return 0;
				}

			} catch (Exception e) {
				System.out.println("ReviewDAO.countRiview");
				e.printStackTrace();
			}
			
			return 0;
			
		}
		
}

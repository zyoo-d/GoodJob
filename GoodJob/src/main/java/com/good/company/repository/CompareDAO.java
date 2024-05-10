package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.good.company.model.CompanyDTO;
import com.good.company.model.ReviewDTO;
import com.good.company.model.Tag;
import com.test.util.DBUtil;

public class CompareDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public CompareDAO() {
		this.conn = DBUtil.open();
	}
	public void close()  {
        try {
            this.conn.close();
        } catch (Exception e) {
            System.out.println("CompanyDAO.close 오류");
            e.printStackTrace();
        }
    }
	
	public ArrayList<CompanyDTO> getCompareInfo(HashMap<String, String> map) {
	    try {
	        String sql = "SELECT * from vwComInfoScore where cp_seq in (?, ?, ?)";

       pstat = conn.prepareStatement(sql);
       pstat.setString(1, map.get("tag1"));
       pstat.setString(2, map.get("tag2"));
       pstat.setString(3, map.get("tag3"));

	        rs = pstat.executeQuery();

	        ArrayList<CompanyDTO> list = new ArrayList<>();
	        while (rs.next()) {
	            CompanyDTO dto = new CompanyDTO();
	            dto.setCp_name(rs.getString("cp_name"));
	            dto.setCp_address(rs.getString("cp_address"));
	            dto.setCeo(rs.getString("ceo"));
	            dto.setFounded(rs.getString("founded"));
	            dto.setImage(rs.getString("image"));
	            dto.setHire_member(rs.getInt("hire_member"));
	            dto.setHire_avr_year(rs.getInt("hire_avr_year"));
	            dto.setIdst_name(rs.getString("idst_name"));
	            dto.setPotential(rs.getInt("potential"));
	            dto.setSalary(rs.getInt("salary"));
	            dto.setStability(rs.getInt("stability"));
	            dto.setWelfare(rs.getInt("welfare"));
	            dto.setCulture(rs.getInt("culture"));

	            dto.setHire_avr_salary(rs.getInt("hire_avr_salary"));
	            dto.setFnc_ebit(rs.getLong("fnc_ebit"));
	            dto.setFnc_sales(rs.getLong("fnc_sales"));
	            dto.setFnc_income(rs.getLong("fnc_income"));

	            String tagKeyword = rs.getString("tag_keyword");
	            if (tagKeyword != null && !tagKeyword.isEmpty()) {
	                ArrayList<String> tagList = new ArrayList<>(Arrays.asList(tagKeyword.split(",", 3)));
	                dto.setTag_list(tagList);
	            }
	            
	            list.add(dto);
	        }
	        return list;

	    } catch (Exception e) {
	        System.out.println("CompareDAO.getCompareInfo");
	        e.printStackTrace();
	    }

	    return null;
	}
	public ArrayList<Long>[] getCompanyFinance(HashMap<String, String> map) {
		ArrayList<Long> salesList = new ArrayList<>();
        ArrayList<Long> ebitList = new ArrayList<>();
        ArrayList<Long> incomeList = new ArrayList<>();
    String sql = "select * from tblFinance where cp_seq in (?, ?, ?)";
   
 
    try (PreparedStatement pstat = conn.prepareStatement(sql)) {
        pstat.setString(1, map.get("tag1"));
        pstat.setString(2, map.get("tag2"));
        pstat.setString(3, map.get("tag3"));
        try (ResultSet rs = pstat.executeQuery()) {
            while (rs.next()) {
              
               long sales = rs.getLong("fnc_sales");
               long ebit = rs.getLong("fnc_ebit");
               long income = rs.getLong("fnc_income");
               
               salesList.add(sales);
               ebitList.add(ebit);
               incomeList.add(income);	                
            }
        }
    } catch (Exception e) {
        System.out.println("getCompanyFinance");
        e.printStackTrace();
    }
    
    return new ArrayList[]{salesList, ebitList, incomeList};
}
	public ArrayList<ReviewDTO> getReviewscore(HashMap<String, String> map) {
		try {
			String sql = "SELECT NVL(ROUND(avg(salary_score), 1), 0) AS salary_score,\r\n"
					+ "       NVL(ROUND(avg(welfare_score), 1), 0) AS welfare_score,\r\n"
					+ "       NVL(ROUND(avg(stability_score), 1), 0) AS stability_score,\r\n"
					+ "       NVL(ROUND(avg(culture_score), 1), 0) AS culture_score,\r\n"
					+ "       NVL(ROUND(avg(growth_score), 1), 0) AS growth_score\r\n"
					+ "FROM tblCompanyReview\r\n"
					+ "where cp_seq in (?,?,?)\r\n"
					+ "GROUP BY cp_seq";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("tag1"));
			pstat.setString(2, map.get("tag2"));
			pstat.setString(3, map.get("tag3"));

			rs = pstat.executeQuery();

			ArrayList<ReviewDTO> list = new ArrayList<>();
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setCulture_score(rs.getInt("culture_score"));
				dto.setGrowth_score(rs.getInt("growth_score"));
				dto.setStability_score(rs.getInt("stability_score"));
				dto.setWelfare_score(rs.getInt("welfare_score"));
				dto.setSalary_score(rs.getInt("salary_score"));
				
				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("CompareDAO.getReviewscore");
			e.printStackTrace();
		}
		return null;
	}

	
	
	


}


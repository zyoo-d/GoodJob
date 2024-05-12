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
	        String sql = "SELECT * from vwComInfoScore where cp_name in (?, ?, ?)  order by cp_seq desc";

       pstat = conn.prepareStatement(sql);
       pstat.setString(1, map.get("tag1"));
       pstat.setString(2, map.get("tag2"));
       pstat.setString(3, map.get("tag3"));

	        rs = pstat.executeQuery();

	        ArrayList<CompanyDTO> list = new ArrayList<>();
	        while (rs.next()) {
	            CompanyDTO dto = new CompanyDTO();
	            dto.setCp_name(rs.getString("cp_name"));
	            dto.setCp_seq(rs.getString("cp_seq"));
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
    String sql = "SELECT f.*\r\n"
    		+ "FROM tblFinance f\r\n"
    		+ "INNER JOIN tblCompany c ON f.cp_seq = c.cp_seq\r\n"
    		+ "WHERE c.cp_name IN (?, ?, ?)\r\n"
    		+ "ORDER BY f.cp_seq DESC, f.fnc_period ASC";
   
 
    try (PreparedStatement pstat = conn.prepareStatement(sql)) {
        pstat.setString(1, map.get("tag1"));
        pstat.setString(2, map.get("tag2"));
        pstat.setString(3, map.get("tag3"));
        try (ResultSet rs = pstat.executeQuery()) {
            while (rs.next()) {
              
               long sales = rs.getLong("fnc_sales") / 10000;
               long ebit = rs.getLong("fnc_ebit") / 10000;
               long income = rs.getLong("fnc_income") / 10000;
               
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
			String sql = "SELECT \r\n"
					+ "    c.cp_name,\r\n"
					+ "    NVL(ROUND(avg(r.salary_score), 1), 0) AS salary_score,\r\n"
					+ "    NVL(ROUND(avg(r.welfare_score), 1), 0) AS welfare_score,\r\n"
					+ "    NVL(ROUND(avg(r.stability_score), 1), 0) AS stability_score,\r\n"
					+ "    NVL(ROUND(avg(r.culture_score), 1), 0) AS culture_score,\r\n"
					+ "    NVL(ROUND(avg(r.growth_score), 1), 0) AS growth_score,\r\n"
					+ "    NVL(ROUND(avg(r.salary_score + r.welfare_score + r.stability_score + r.culture_score + r.growth_score) / 5, 1), 0) AS total_average_score\r\n"
					+ "FROM tblCompanyReview r\r\n"
					+ "RIGHT OUTER JOIN tblCompany c ON r.cp_seq = c.cp_seq\r\n"
					+ "WHERE c.cp_name IN (?, ?, ?)\r\n"
					+ "GROUP BY c.cp_seq, c.cp_name\r\n"
					+ "ORDER BY c.cp_seq DESC";

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
				dto.setAvg_score(rs.getDouble("total_average_score"));
				dto.setCp_name(rs.getString("cp_name"));
				
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


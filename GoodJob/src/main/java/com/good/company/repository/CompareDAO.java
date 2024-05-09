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
//	public ArrayList<CompanyDTO> getCompareInfo(HashMap<String, String> map) {
//		
//		try {
//			String sql = "SELECT \r\n"
//					+ "    a.cp_seq, \r\n"
//					+ "    a.cp_name, \r\n"
//					+ "    NVL(a.cp_address, '-') AS cp_address,\r\n"
//					+ "    NVL(a.ceo, '-') AS ceo,\r\n"
//					+ " TO_CHAR(NVL(a.founded, DATE '0-01-01'), 'YYYY-MM-DD') AS founded,\r\n"
//					+ "    image,\r\n"
//					+ "    NVL(a.hire_member, 0) AS hire_member,\r\n"
//					+ "    NVL(a.hire_avr_year, 0) AS hire_avr_year,\r\n"
//					+ "    NVL(a.hire_avr_salary, 0) AS hire_avr_salary,\r\n"
//					+ "    NVL(a.idst_name, '-') AS idst_name,\r\n"
//					+ "    NVL(m.potential, 0) AS potential,\r\n"
//					+ "    NVL(m.salary, 0) AS salary,\r\n"
//					+ "    NVL(m.stability, 0) AS stability,\r\n"
//					+ "    NVL(m.welfare, 0) AS welfare,\r\n"
//					+ "    NVL(m.culture, 0) AS culture\r\n"
//					+ "FROM \r\n"
//					+ "    vwNewComListInfo a\r\n"
//					+ "LEFT OUTER JOIN \r\n"
//					+ "    vwCompanyscore m ON a.cp_seq = m.cp_seq \r\n"
//					+ "WHERE \r\n"
//					+ "    a.cp_seq IN (?, ?, ?)";
//
//			pstat = conn.prepareStatement(sql);
//			pstat.setString(1, map.get("tag1"));
//			pstat.setString(2, map.get("tag2"));
//			pstat.setString(3, map.get("tag3"));
//
//			rs = pstat.executeQuery();
//
//			ArrayList<CompanyDTO> list = new ArrayList<>();
//			while (rs.next()) {
//				CompanyDTO dto = new CompanyDTO();
//				dto.setCp_name(rs.getString("cp_name"));
//				dto.setCp_address(rs.getString("cp_address"));
//				dto.setCeo(rs.getString("ceo"));
//				dto.setFounded(rs.getString("founded"));
//				dto.setImage(rs.getString("image"));
//				dto.setHire_member(rs.getInt("hire_member"));
//				dto.setHire_avr_year(rs.getInt("hire_avr_year"));
//				dto.setHire_avr_salary(rs.getInt("hire_avr_salary"));
//				dto.setIdst_name(rs.getString("idst_name"));
//				dto.setPotential(rs.getInt("potential"));
//				dto.setSalary(rs.getInt("salary"));
//				dto.setStability(rs.getInt("stability"));
//				dto.setWelfare(rs.getInt("welfare"));
//				dto.setCulture(rs.getInt("culture"));
//
//				list.add(dto);
//			}
//			return list;
//
//		} catch (Exception e) {
//			System.out.println("CompareDAO.getCompareInfo");
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	
	public ArrayList<CompanyDTO> getCompareInfo(HashMap<String, String> map) {
	    try {
	        String sql = "SELECT\r\n"
	        		+ "    a.cp_seq,\r\n"
	        		+ "    a.cp_name,\r\n"
	        		+ "    NVL(a.cp_address, '-') AS cp_address,\r\n"
	        		+ "    NVL(a.ceo, '-') AS ceo,\r\n"
	        		+ "    TO_CHAR(NVL(a.founded, DATE '0-01-01'), 'YYYY-MM-DD') AS founded,\r\n"
	        		+ "    a.image,\r\n"
	        		+ "    NVL(a.hire_member, 0) AS hire_member,\r\n"
	        		+ "    NVL(a.hire_avr_year, 0) AS hire_avr_year,\r\n"
	        		+ "    NVL(a.hire_avr_salary, 0) AS hire_avr_salary,\r\n"
	        		+ "    NVL(a.idst_name, '-') AS idst_name,\r\n"
	        		+ "    NVL(m.potential, 0) AS potential,\r\n"
	        		+ "    NVL(m.salary, 0) AS salary,\r\n"
	        		+ "    NVL(m.stability, 0) AS stability,\r\n"
	        		+ "    NVL(m.welfare, 0) AS welfare,\r\n"
	        		+ "    NVL(m.culture, 0) AS culture,\r\n"
	        		+ "    NVL(t.tag_keyword, '등록된 키워드가 없습니다') AS tag_keyword\r\n"
	        		+ "FROM\r\n"
	        		+ "    vwNewComListInfo a\r\n"
	        		+ "LEFT OUTER JOIN\r\n"
	        		+ "    vwCompanyscore m ON a.cp_seq = m.cp_seq\r\n"
	        		+ "LEFT OUTER JOIN\r\n"
	        		+ "    (\r\n"
	        		+ "        SELECT\r\n"
	        		+ "            cp_seq,\r\n"
	        		+ "            LISTAGG(tag_keyword, ',') WITHIN GROUP (ORDER BY tag_keyword) AS tag_keyword\r\n"
	        		+ "        FROM\r\n"
	        		+ "            (\r\n"
	        		+ "                SELECT\r\n"
	        		+ "                    cp_seq,\r\n"
	        		+ "                    tag_keyword,\r\n"
	        		+ "                    ROW_NUMBER() OVER (PARTITION BY cp_seq ORDER BY cnt DESC) AS rn\r\n"
	        		+ "                FROM\r\n"
	        		+ "                    vwCompanyTag\r\n"
	        		+ "            )\r\n"
	        		+ "        WHERE\r\n"
	        		+ "            rn <= 3\r\n"
	        		+ "        GROUP BY\r\n"
	        		+ "            cp_seq\r\n"
	        		+ "    ) t ON a.cp_seq = t.cp_seq\r\n"
	        		+ "WHERE\r\n"
	        		+ "    a.cp_seq IN (?, ?, ?)";

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
	            dto.setHire_avr_salary(rs.getInt("hire_avr_salary"));
	            dto.setIdst_name(rs.getString("idst_name"));
	            dto.setPotential(rs.getInt("potential"));
	            dto.setSalary(rs.getInt("salary"));
	            dto.setStability(rs.getInt("stability"));
	            dto.setWelfare(rs.getInt("welfare"));
	            dto.setCulture(rs.getInt("culture"));


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

	
	public ArrayList<String> tagList(HashMap<String, String> map){
		
		try {	
				
				String sql = "SELECT \r\n"
						+ "    c.cp_seq,\r\n"
						+ "    c.cp_name,\r\n"
						+ "    NVL(t.tag_keyword, '등록된 키워드가 없습니다') AS tag_keyword,\r\n"
						+ "    t.cnt\r\n"
						+ "FROM \r\n"
						+ "    tblCompany c\r\n"
						+ "LEFT JOIN (\r\n"
						+ "    SELECT \r\n"
						+ "        cp_seq,\r\n"
						+ "        tag_keyword,\r\n"
						+ "        cnt,\r\n"
						+ "        ROW_NUMBER() OVER (PARTITION BY cp_seq ORDER BY cnt DESC) AS rn\r\n"
						+ "    FROM \r\n"
						+ "        vwCompanyTag\r\n"
						+ ") t ON c.cp_seq = t.cp_seq\r\n"
						+ "WHERE \r\n"
						+ "    c.cp_seq IN (?, ?, ?) AND\r\n"
						+ "    (t.rn <= 3 OR t.tag_keyword IS NULL)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, map.get("tag1"));
				pstat.setString(2, map.get("tag2"));
				pstat.setString(3, map.get("tag3"));
				
				rs = pstat.executeQuery();
				
				ArrayList<String> list = new ArrayList<>();
				
				while(rs.next()) {
					
					list.add(rs.getString("tag_keyword"));
					
				}
				
				return list;
				
			} catch (Exception e) {
				System.out.println("CompanyDAO.getTaglist");
				e.printStackTrace();
			}
		
		return null;
		
	}
	


}


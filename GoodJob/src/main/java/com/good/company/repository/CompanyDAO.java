package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.good.company.model.CompanyDTO;
import com.test.util.DBUtil;

public class CompanyDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public CompanyDAO() {
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
	
	public ArrayList<CompanyDTO> rcrtCompany() {

		try {

			String sql = "select cp_seq, cp_name, image from tblCompany";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<CompanyDTO> listCompany = new ArrayList<CompanyDTO>();
			while (rs.next()) {

				CompanyDTO dto = new CompanyDTO();

				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setCp_name(rs.getString("cp_name"));
				dto.setCp_address(rs.getString("cp_address"));

				listCompany.add(dto);

			}
			return listCompany;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<CompanyDTO> searchCompany(String input) {
		try {
			String sql = "SELECT * FROM (SELECT * FROM tblcompany WHERE cp_name LIKE ? order by cp_name) WHERE ROWNUM <= 5";

			pstat = conn.prepareStatement(sql);
			String search = input + "%";
			pstat.setString(1, search);

			rs = pstat.executeQuery();

			List<CompanyDTO> list = new ArrayList<>();
			while (rs.next()) {
				CompanyDTO dto = new CompanyDTO();
				dto.setCp_name(rs.getString("cp_name"));
				dto.setCp_seq(rs.getString("cp_seq"));

				list.add(dto);
			}
			pstat.close();
			return list;

		} catch (Exception e) {
			System.out.println("CompanyDAO.searchCompany");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 등록된 기업수 불러오는 메서드
	 * @return 기업수
	 */
	public int countCompanys() {
		
		try {

			String sql = "select count(*) as cnt from tblCompany";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("등록된 기업수 로드 실패");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	/**
	 * 지유)기업 목록, 상세 최신 정보 불러오는 메서드
	 * @return 기업정보(일반/재무/고용)
	 */
	public ArrayList<CompanyDTO> comListInfo(HashMap<String,Object> map){
		
	
		try {
			
			String sql ="";
			String where = "";
			
			//검색
			if (map.get("search").equals("y")) {
				// 검색어 조건 추가
				if (map.get("word") != null && !((String)map.get("word")).isEmpty()) {
					where += "cp_name LIKE '%' || ? || '%' AND ";
				}

				// 연봉 조건 추가
				if (map.get("salary_seq") != null && !((String)map.get("salary_seq")).isEmpty()) {
					where += "salary_seq >= ? AND ";
				}

				// 지역 조건 추가
				if (map.get("cp_address") != null && ((String[])map.get("cp_address")).length > 0) {
				    String[] addressArray = (String[])map.get("cp_address");
				    
				    if (addressArray.length > 0) {
				        where += "(";
				        for (int i = 0; i < addressArray.length; i++) {
				            if (i > 0) {
				                where += " OR ";
				            }
				            where += "cp_address LIKE '%' || ? || '%'";
				        }
				        where += ") AND ";
				    }
				}
				//채용중
				if(map.get("hiring").equals("y") && map.get("hiring") != null) {
					
					where += "com_rcrt_cnt > 0 AND ";
				}
				if (where.endsWith(" AND ")) {
					where = where.substring(0, where.length() - 5);
				}
			}
			
	
			 sql = String.format("SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM VWNEWCOMLISTINFO %s ORDER BY %s) a) WHERE rnum BETWEEN %s AND %s", where.isEmpty() ? "" : " WHERE " + where, map.get("sort"), map.get("begin"), map.get("end"));

	        

			
	        int parameterIndex = 1;
	        if (map.get("search").equals("y")) {
	            if (map.get("word") != null && !((String)map.get("word")).isEmpty()) {
	                pstat.setString(parameterIndex++, "%" + map.get("word") + "%");
	            }
	            if (map.get("salary_seq") != null && !((String)map.get("salary_seq")).isEmpty()) {
	                pstat.setString(parameterIndex++, (String)map.get("salary_seq"));
	            }
	            if (map.get("cp_address") != null && ((String[])map.get("cp_address")).length > 0) {
	                String[] addressArray = (String[])map.get("cp_address");
	                for (String address : addressArray) {
	                    address = address.replace("전체", "").trim();
	                    pstat.setString(parameterIndex++, address);
	                }
	            }
	        }

	        System.out.println(sql);
	        pstat = conn.prepareStatement(sql);
	        
			rs = stat.executeQuery(sql);
			
			ArrayList<CompanyDTO> listCompanyInfo = new ArrayList<CompanyDTO>();
			
			while (rs.next()) {
				CompanyDTO dto = new CompanyDTO();
				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setCp_name(rs.getString("cp_name"));
				dto.setCp_address(rs.getString("cp_address"));
				dto.setCeo(rs.getString("ceo"));
				dto.setFounded(rs.getString("founded"));
				dto.setImage(rs.getString("image"));				
				dto.setIdst_code(rs.getString("idst_code"));
				dto.setIdst_name(rs.getString("idst_name"));							
				dto.setHire_member(rs.getInt("hire_member"));
				dto.setHire_new(rs.getInt("hire_new"));
				dto.setHire_retired(rs.getInt("hire_retired"));
				dto.setHire_avr_year(rs.getInt("hire_avr_year"));
				dto.setHire_avr_salary(rs.getInt("hire_avr_salary"));
				dto.setHire_regdate(rs.getString("hire_regdate"));								
				dto.setFnc_sales(rs.getLong("fnc_sales"));
				dto.setFnc_ebit(rs.getLong("fnc_ebit"));
				dto.setFnc_income(rs.getLong("fnc_income"));
				dto.setFnc_period(rs.getString("fnc_period"));
				dto.setFnc_regdate(rs.getString("fnc_regdate"));				
				//dto.setCom_rcrt_cnt(rs.getInt("com_rcrt_cnt"));
				//dto.setCom_scrap_cnt(rs.getInt("com_scrap_cnt"));
				//dto.setCom_scrap_cnt(rs.getInt("com_rv_cnt"));				
				listCompanyInfo.add(dto);

			}
			return listCompanyInfo;

		} catch (Exception e) {
			System.out.println("기업목록출력failed");
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * 검색결과에 따른 기업수를 부러오는 메서드
	 * @param map
	 * @return
	 */
	public int searchCompanyCount(HashMap<String, Object> map) {
		
		try {

			String where ="";
			String sql = "";
			
			
			if(map.get("search").equals("y") && map.get("hiring").equals("y")) {
				
				where = String.format("where cp_name like '%%%s%%' and com_rcrt_cnt > 0",map.get("word"));
				sql = String.format("select count(*) as search_cnt from vwNewComListInfo %s", where);
			
			}else if (map.get("search").equals("y") && map.get("hiring").equals("n")){
			
				where = String.format("where cp_name like '%%%s%%'",map.get("word"));
				sql = String.format("select count(*) as search_cnt from vwNewComListInfo %s", where);
			
			}else if (map.get("search").equals("n") && map.get("hiring").equals("y")){
			
				where = String.format("where com_rcrt_cnt > 0");
				sql = String.format("select count(*) as search_cnt from vwNewComListInfo %s", where);
			
			} else {
				
				sql = String.format("select count(*) as search_cnt from vwNewComListInfo");
			}
			
			
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();

			if (rs.next()) {
				
				return rs.getInt("search_cnt");
			
			}

		} catch (Exception e) {
			System.out.println("CompanyDAO.searchCompanyCount");
			e.getStackTrace();
		}
		
		return 0;
	}
//TODO 수정해야함
	public CompanyDTO get(String cp_seq) {
		
		try {
		String sql = "select * from vwComDetailInfo where cp_seq = ?";
		
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, cp_seq);
		
		rs = pstat.executeQuery();
		
		if (rs.next()) {
			
			CompanyDTO dto = new CompanyDTO();

			dto.setCp_seq(rs.getString("cp_seq"));
			dto.setCp_name(rs.getString("cp_name"));
			dto.setCp_address(rs.getString("cp_address"));
			dto.setCeo(rs.getString("ceo"));
			dto.setFounded(rs.getString("founded"));
			dto.setImage(rs.getString("image"));
			
			dto.setIdst_code(rs.getString("idst_code"));
			dto.setIdst_name(rs.getString("idst_name"));
			
		
			dto.setHire_member(rs.getInt("hire_member"));
			dto.setHire_new(rs.getInt("hire_new"));
			dto.setHire_retired(rs.getInt("hire_retired"));
			dto.setHire_avr_year(rs.getInt("hire_avr_year"));
			dto.setHire_avr_salary(rs.getInt("hire_avr_salary"));
			dto.setHire_regdate(rs.getString("hire_regdate"));
			
			dto.setHire_member(rs.getInt("hire_member"));
			dto.setHire_new(rs.getInt("hire_new"));
			dto.setHire_retired(rs.getInt("hire_retired"));
			dto.setHire_avr_year(rs.getInt("hire_avr_year"));
			dto.setHire_avr_salary(rs.getInt("hire_avr_salary"));
			dto.setHire_regdate(rs.getString("hire_regdate"));
			
			dto.setCom_rcrt_cnt(rs.getInt("com_rcrt_cnt"));
			dto.setCom_scrap_cnt(rs.getInt("com_scrap_cnt"));
		
			//재무정보
/*			sql = "select * from tblFinance where cp_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_seq);
			rs = pstat.executeQuery();
			
			ArrayList<CompanyDTO> flist = new ArrayList<CompanyDTO>();
			
			while(rs.next()) {
				
				dto.setFnc_sales(rs.getLong("fnc_sales"));
				dto.setFnc_ebit(rs.getLong("fnc_ebit"));
				dto.setFnc_income(rs.getLong("fnc_income"));
				dto.setFnc_period(rs.getString("fnc_period"));
				dto.setFnc_regdate(rs.getString("fnc_regdate"));
				
				
				flist.add(dto);

			
			}
			
			dto.setFnc_list(flist);*/
			
			return dto;				
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return null;
	}
	

	/*public ArrayList<CompanyDTO> getCompanyFinance(String cp_seq) {
	    ArrayList<CompanyDTO> financeList = new ArrayList<>();
	    String sql = "select * from tblFinance where cp_seq = ?";
	    try (PreparedStatement pstat = conn.prepareStatement(sql)) {
	        pstat.setString(1, cp_seq);
	        ResultSet rs = pstat.executeQuery();

	        while (rs.next()) {
	        	CompanyDTO fdto = new CompanyDTO();
	        	fdto.setFnc_sales(rs.getLong("fnc_sales"));
	        	fdto.setFnc_ebit(rs.getLong("fnc_ebit"));
	        	fdto.setFnc_income(rs.getLong("fnc_income"));
	        	fdto.setFnc_period(rs.getString("fnc_period"));
	        	fdto.setFnc_regdate(rs.getString("fnc_regdate"));
	            financeList.add(fdto);
	        }
	    } catch (Exception e) {
	    	System.out.println("getCompanyFinance");
	        e.printStackTrace();
	    }
	    return financeList;
	}*/
	

	public ArrayList<Long>[] getCompanyFinance(String cp_seq) {
		 	ArrayList<Long> salesList = new ArrayList<>();
	        ArrayList<Long> ebitList = new ArrayList<>();
	        ArrayList<Long> incomeList = new ArrayList<>();
	    String sql = "select * from tblFinance where cp_seq = ?";
	   
	 
	    try (PreparedStatement pstat = conn.prepareStatement(sql)) {
	        pstat.setString(1, cp_seq);
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
	/**
	 * 업계평균연봉 조회 메서드	
	 * @param idst_code
	 * @return
	 */
	public int getIdstSalary(String idst_code) {
		try {
			String sql = "select * from vwIdstAvgSalary where idst_code = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, idst_code);
			
			rs = pstat.executeQuery();
			 
				if (rs.next()) {
					
					int idst_avg_salary = rs.getInt("idst_avg_salary");
					
					return idst_avg_salary;
				
				}
			
			}catch (Exception e) {
				e.printStackTrace();
			}
				return 0;
			}
	
	//TODO job dao 만들기
	

}




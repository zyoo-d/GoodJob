package com.goodjob.test.heeyeon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HeeSearchInfo {
	public static void main(String[] args) {
		// searchCompanyInfo();
		searchHireInfo();
		// searchFinanceInfo();
	}

	private static void searchFinanceInfo() {
		HeeTestDAO dao = new HeeTestDAO();

		ArrayList<HeeCompany> list = dao.comList();
		// e11f4c31b1b2d90885677aedb28703686d37aecc
		for (HeeCompany com : list) {

			String path = "https://opendart.fss.or.kr/api/fnlttMultiAcnt.json?crtfc_key=e11f4c31b1b2d90885677aedb28703686d37aecc&corp_code="
					+ com.getCode() + "&bsns_year=2023&reprt_code=11011";
			try {
				URL url = new URL(path);

				// JSON 결과
				BufferedReader bf;
				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				String result = bf.readLine();
				bf.close();

				JSONParser parser = new JSONParser();
				JSONObject root = (JSONObject) parser.parse(result);
				HeeFinance finance = new HeeFinance();
				finance.setFnc_period("2023");
				finance.setP_period("2022");
				finance.setOl_period("2021");
				finance.setSeq(com.getSeq());
				System.out.println(com.getName());
				if (((String) ((JSONObject) root).get("status")).equals("000")) {

					JSONArray arr = (JSONArray) root.get("list");
					for (Object info : arr) {
						if (((String) ((JSONObject) info).get("account_nm")).equals("매출액")) {
							finance.setFnc_sales(((String) ((JSONObject) info).get("thstrm_amount")).replace(",", ""));
							finance.setP_sales(((String) ((JSONObject) info).get("frmtrm_amount")).replace(",", ""));
							finance.setOl_sales(
									((String) ((JSONObject) info).get("bfefrmtrm_amount")).replace(",", ""));

						} else if (((String) ((JSONObject) info).get("account_nm")).equals("영업이익")) {
							finance.setFnc_ebit(((String) ((JSONObject) info).get("thstrm_amount")).replace(",", ""));
							finance.setP_ebit(((String) ((JSONObject) info).get("frmtrm_amount")).replace(",", ""));
							finance.setOl_ebit(((String) ((JSONObject) info).get("bfefrmtrm_amount")).replace(",", ""));

						} else if (((String) ((JSONObject) info).get("account_nm")).equals("당기순이익")) {
							finance.setFnc_income(((String) ((JSONObject) info).get("thstrm_amount")).replace(",", ""));
							finance.setP_income(((String) ((JSONObject) info).get("frmtrm_amount")).replace(",", ""));
							finance.setOl_income(
									((String) ((JSONObject) info).get("bfefrmtrm_amount")).replace(",", ""));
						}
					}

					int end = dao.addFinance(finance);
					System.out.println(end);
					System.out.println(finance);
				}

			} catch (Exception e) {
				System.out.println("emain");
				e.printStackTrace();
			}
		}

	}

	private static void searchHireInfo() {
		HeeTestDAO dao = new HeeTestDAO();

		ArrayList<HeeCompany> list = dao.comList();
		// e11f4c31b1b2d90885677aedb28703686d37aecc
		for (HeeCompany com : list) {

			String path = "https://opendart.fss.or.kr/api/empSttus.json?crtfc_key=e11f4c31b1b2d90885677aedb28703686d37aecc&corp_code="
					+ com.getCode() + "&bsns_year=2023&reprt_code=11011";
			try {
				URL url = new URL(path);

				// JSON 결과
				BufferedReader bf;
				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				String result = bf.readLine();
				bf.close();

				JSONParser parser = new JSONParser();
				JSONObject root = (JSONObject) parser.parse(result);
				String maleNum = "";
				String maleYear = "";
				int totalNum = 0;
				int totalYear = 0;

				String femaleNum = "";
				String femaleYear = "";
				
				if (((String) ((JSONObject) root).get("status")).equals("000")) {

					JSONArray arr = (JSONArray) root.get("list");
					for (Object info : arr) {
						if (((String) ((JSONObject) info).get("sexdstn")).equals("남")) {

							maleNum = ((String) ((JSONObject) info).get("sm"));
							maleNum = maleNum.replace(",", "");
							maleYear = ((String) ((JSONObject) info).get("avrg_cnwk_sdytrn"));
							int months = convertToMonths(maleYear);
							
							totalNum += Integer.parseInt(maleNum);
							totalYear+=Integer.parseInt(maleNum)*months;
							System.out.printf("%s(남): 인원 %s명, 근속 %d\r\n", com.getName(), maleNum, months);

						} else if (((String) ((JSONObject) info).get("sexdstn")).equals("여")) {
							femaleNum = ((String) ((JSONObject) info).get("sm"));
							femaleNum = femaleNum.replace(",", "");
							femaleYear = ((String) ((JSONObject) info).get("avrg_cnwk_sdytrn"));
							int months = convertToMonths(femaleYear);
							
							totalNum += Integer.parseInt(femaleNum);
							totalYear+=Integer.parseInt(femaleNum)*months;
							System.out.printf("%s(여): 인원 %s명, 근속 %d\r\n", com.getName(), femaleNum, months);
						}
					}
					/*
					 * System.out.println(totalNum); System.out.println(totalYear); double temp =
					 * (double)(totalYear/totalNum); int month = (int)Math.floor(temp);
					 * System.out.println(month);
					 * 
					 * HashMap<String, Integer> map = new HashMap<>(); map.put("seq",
					 * Integer.parseInt(com.getSeq())); map.put("month", month);
					 * 
					 * int end = dao.updateHire(map); System.out.println(end);
					 */
				}


				
				 
			} catch (Exception e) {
				System.out.println("emain");
				e.printStackTrace();
			}
		}

	}

	private static void searchCompanyInfo() {
		HeeTestDAO dao = new HeeTestDAO();

		ArrayList<HeeCompany> list = dao.comList();
		// e11f4c31b1b2d90885677aedb28703686d37aecc
		for (HeeCompany com : list) {
			// https://opendart.fss.or.kr/api/company.json?crtfc_key=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx&corp_code=00126380
			String path = "https://opendart.fss.or.kr/api/company.json?crtfc_key=e11f4c31b1b2d90885677aedb28703686d37aecc&corp_code="
					+ com.getCode();
			try {
				URL url = new URL(path);

				// JSON 결과
				BufferedReader bf;
				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				String result = bf.readLine();
				bf.close();

				JSONParser parser = new JSONParser();
				JSONObject info = (JSONObject) parser.parse(result);

				com.setEst_dt((String) ((JSONObject) info).get("est_dt"));
				com.setCeo_nm((String) ((JSONObject) info).get("ceo_nm"));

				System.out.println(com.getName());
				System.out.println(com.getEst_dt());
				System.out.println(com.getCeo_nm());
				// break;

				int end = dao.updateInfo(com);
				System.out.println(end);
			} catch (Exception e) {
				System.out.println("emain");
				e.printStackTrace();
			}
		}
	}

	public static int convertToMonths(String date) {
		date = date.replace(" ", ""); // 공백 제거
		double years = 0;
		int months = 0;

		if (date.contains("년") && date.contains(".")) {
			date = date.replace("년", "");

			years = Double.parseDouble(date);
			months = (int) Math.round((years - Math.floor(years)) * 12);
			years = Math.floor(years);
			return (int) years * 12 + months;

		} else if (date.contains("년")) {
			String[] parts = date.split("년");
			years = Integer.parseInt(parts[0]);
			if (parts.length > 1 && parts[1].contains("개월")) {
				months = Integer.parseInt(parts[1].replace("개월", ""));
			}
		} else if (date.contains("개월")&& date.contains(".")) {
			date = date.replace("개월", "");
			double temp = Double.parseDouble(date);
			months = (int)Math.floor(temp);
			
		} else if (date.contains("개월")) {
			months = Integer.parseInt(date.replace("개월", ""));
			
		} else {
			years = (int) Double.parseDouble(date);
		}

		return (int) years * 12 + months;
	}
}

package com.good.company.recruit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.good.company.model.CompanyDTO;
import com.good.company.model.RecruitDTO;
import com.good.company.repository.CompanyDAO;
import com.good.company.repository.RecruitDAO;

public class AddTest {
	public static void main(String[] args) {
		connectHireApi();
	}

	private static void parserApi(String result) {
		//RecruitDTO dto = new RecruitDTO();
		//RecruitDAO dao = new RecruitDAO();

		//CompanyDAO comdao = new CompanyDAO();
		//ArrayList<CompanyDTO> clist = comdao.rcrtCompany();
		try {

			JSONParser parser = new JSONParser();

			JSONObject root = (JSONObject) parser.parse(result);

			JSONArray list = new JSONArray();
			
			JSONObject jobs = (JSONObject) root.get("jobs");
			JSONArray job = (JSONArray) jobs.get("job");
			

			for(Object recruit: job) {
				
				
				//채용공고링크
				String link = (String)((JSONObject) recruit).get("url");
				//공고게시일
				String startdate = (String)((JSONObject) recruit).get("posting-timestamp");
				//공고마감일
				String enddate = (String)((JSONObject) recruit).get("expiration-timestamp");
				//연봉코드
				String salary_seq = (String)((JSONObject) ((JSONObject)recruit).get("salary")).get("code");
				//회사이름
				String cp_name = (String)((JSONObject) ((JSONObject)((JSONObject)recruit).get("company")).get("detail")).get("name");
				//근무지
				String rcrt_address=(String)((JSONObject) ((JSONObject)((JSONObject)recruit).get("position")).get("location")).get("name");
				//최소경력
				//int min_career = Integer.parseInt((String)((JSONObject) ((JSONObject)((JSONObject)recruit).get("position")).get("experience-level")).get("min"));

				//String min_career = (String)((JSONObject) ((JSONObject)((JSONObject)recruit).get("position")).get("experience-level")).get("min");
				String min_career = String.valueOf (((JSONObject) ((JSONObject)((JSONObject)recruit).get("position")).get("experience-level")).get("min"));
				//최대경력
				String max_career = String.valueOf (((JSONObject) ((JSONObject)((JSONObject)recruit).get("position")).get("experience-level")).get("max"));
				
				
				//관련직무 = 
				String job_seq =(String)((JSONObject) ((JSONObject)((JSONObject)recruit).get("position")).get("job-code")).get("code");
				//학력
				String edu_seq =(String)((JSONObject) ((JSONObject)((JSONObject)recruit).get("position")).get("required-education-level")).get("code");
				
				
				
				System.out.printf("[%s]\n", max_career);
			
		
			}
			//System.out.println(list);
			
		


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("AddRecruit.connetct");
		}

	}

	private static void connectHireApi() {


		String accessKey = "kGgodXZQ5kfDGTdZ1KRRvu0P2hr5jJAz2Bxm41owVOyPud5yqs1oW"; // 발급받은 accessKey";

		int startcnt = 0;
		int endcnt = 110;

		try {

			String text = URLEncoder.encode("", "UTF-8");
			String result = "";
			String apiURL = "https://oapi.saramin.co.kr/job-search?access-key=" + accessKey + "&keyword=" + text
					+ "&job_type=&fields=posting-date+expiration-date+keyword-code+count&count=110&job_mid_cd=2";

			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");

			BufferedReader bf;

			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			result = bf.readLine();
			bf.close();

			parserApi(result);
			// System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("AddRecruit.connetct");
		}

	}

}
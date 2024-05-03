package com.goodjob.test.heeyeon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.good.company.repository.RecruitDAO;
import com.good.matching.model.RecruitDTO;

public class AddTest {
	public static void main(String[] args) {

		HeeTestDAO dao = new HeeTestDAO();

		ArrayList<HeeCompany> list = dao.comList();
		for (HeeCompany com : list) {

			try {

				String text = URLEncoder.encode(com.getName(), "UTF-8");
				String result = "";
				String apiURL = "https://oapi.saramin.co.kr/job-search?access-key=kGgodXZQ5kfDGTdZ1KRRvu0P2hr5jJAz2Bxm41owVOyPud5yqs1oW&keywords="
						+ text + "&job_type=1&edu_lv=&count=110&job_mid_cd=2";

				URL url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Accept", "application/json");

				BufferedReader bf;

				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

				result = bf.readLine();
				bf.close();

				JSONParser parser = new JSONParser();

				JSONObject root = (JSONObject) parser.parse(result);
				
				JSONObject jobs = (JSONObject) root.get("jobs");
				
				JSONArray job = (JSONArray) jobs.get("job");

				for (Object recruit : job) {
					// 공고제목
					String name = (String) ((JSONObject) ((JSONObject) recruit).get("position")).get("title");
					// 채용공고링크
					String link = (String) ((JSONObject) recruit).get("url");
					// 공고게시일
					String startdate = (String) ((JSONObject) recruit).get("posting-timestamp");
					// 공고마감일
					String enddate = (String) ((JSONObject) recruit).get("expiration-timestamp");
					// 연봉코드
					String salary_seq = (String) ((JSONObject) ((JSONObject) recruit).get("salary")).get("code");
					// 회사이름
					String cp_name = (String) ((JSONObject) ((JSONObject) ((JSONObject) recruit).get("company"))
							.get("detail")).get("name");
					// 최소경력
					String min_career = String.valueOf(
							((JSONObject) ((JSONObject) ((JSONObject) recruit).get("position")).get("experience-level"))
									.get("min"));
					// 최대경력
					String max_career = String.valueOf(
							((JSONObject) ((JSONObject) ((JSONObject) recruit).get("position")).get("experience-level"))
									.get("max"));

					// 관련직무 =
					String job_seq = (String) ((JSONObject) ((JSONObject) ((JSONObject) recruit).get("position"))
							.get("job-code")).get("code");
					// 학력
					String edu_seq = (String) ((JSONObject) ((JSONObject) ((JSONObject) recruit).get("position"))
							.get("required-education-level")).get("code");
					startdate = changeEpoch(startdate);
					enddate = changeEpoch(enddate);
					
					// ing > 1만 체크해야함
					Long ing = (Long) ((JSONObject) recruit).get("active");

					if (ing == 1 && cp_name.equals(com.getName())) {
						RecruitDAO redao = new RecruitDAO();
						String nextseq = redao.getNextSeq();

						RecruitDTO dto = new RecruitDTO();
						dto.setRcrt_seq(nextseq);
						dto.setRcrt_name(name);
						dto.setStartdate(startdate);
						dto.setEnddate(enddate);
						dto.setMin_career(min_career);
						dto.setMax_career(max_career);
						dto.setRcrt_link(link);
						dto.setCp_seq(com.getSeq());
						dto.setEdu_seq(edu_seq);
						dto.setSalary_seq(salary_seq);

						int insert = redao.addRecruit(dto);
						int good = 0;
						
						if (insert == 1) {
							String[] jobcode = null;
							HashMap<String, String> map = new HashMap<>();
							map.put("seq", nextseq);

							if (job_seq.contains(",")) {
								jobcode = job_seq.split(",");
								for (String code : jobcode) {
									map.put("code", code);
									good = redao.addJobRcrt(map);
								}
							} else {
								map.put("code", job_seq);
								good = redao.addJobRcrt(map);
							}
						}
						
						System.out.printf("[%s: %d]\n", cp_name, good);
						
						/*
						 * System.out.printf("[%s: %s, %s, %s]\n", cp_name, startdate, salary_seq,
						 * max_career); System.out.printf("[%s: %s, %s]\n", cp_name, enddate, edu_seq);
						 * System.out.printf("[%s, %s]\n", min_career, link);
						 */

					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("AddRecruit");
			}
		}
	}

	private static String changeEpoch(String epochTime) {

		// Instant 객체로 변환
		
		Instant instant = Instant.ofEpochSecond(Long.parseLong(epochTime));

		// Instant를 LocalDate로 변환 (UTC를 기준으로)
		LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();

		// 날짜를 YYYY-MM-DD 형식으로 변환
		String formattedDate = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

		return formattedDate;
	}
	
}
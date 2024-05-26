package com.good.news;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 뉴스 검색을 위한 DAO 클래스
 * 
 * 이 클래스는 Naver 뉴스 검색 API를 사용하여 뉴스 검색 기능을 제공합니다. 검색 결과를 파싱하여 NewsDTO 객체로 변환하여
 * 반환합니다.
 */
public class NewsDAO {
	
    /**
     * 뉴스 검색 메소드
     * 
     * @param word 검색어
     * @return 검색 결과를 담은 NewsDTO 객체 리스트
     */
	public ArrayList<NewsDTO> search(String word) {

		ArrayList<NewsDTO> nlist = new ArrayList<NewsDTO>();

		String clientId = "";
		String clientSecret = "";

		try {
			word = URLEncoder.encode(word, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/news.json?display=2&query=" + word;
		// System.out.println(apiURL);

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);

		// System.out.println(responseBody);

		// 파싱
		JSONParser parser = new JSONParser();

		try {

			JSONObject root = (JSONObject) parser.parse(responseBody);

			JSONArray list = (JSONArray) root.get("items");

			for (int i = 0; i < list.size(); i++) {

				NewsDTO dto = new NewsDTO();
				dto.setTitle(((JSONObject) list.get(i)).get("title").toString());
				dto.setLink(((JSONObject) list.get(i)).get("link").toString());

				dto.setDescription(((JSONObject) list.get(i)).get("description").toString());
				dto.setPubDate(((JSONObject) list.get(i)).get("pubDate").toString());

				// System.out.println(dto);

				nlist.add(dto);

			} // for

			return nlist;

		} catch (Exception e) {
			System.out.println("NewsDAO.search");
			e.printStackTrace();
		}

		return null;
	}

    /**
     * API 요청 메소드
     * 
     * @param apiUrl API 요청 URL
     * @param requestHeaders 요청 헤더
     * @return API 응답 결과
     */
	private String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 오류 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

    /**
     * API 연결 메소드
     * 
     * @param apiUrl API URL
     * @return HttpURLConnection 객체
     */
	private HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

    /**
     * API 응답 읽기 메소드
     * 
     * @param body InputStream 객체
     * @return API 응답 결과
     */
	private String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
		}
	}
}

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

public class NewsDAO {
public ArrayList<NewsDTO> search(String word) {
		
		ArrayList<NewsDTO> nlist = new ArrayList<NewsDTO>();
		
		String clientId = "pLlr5465lv_1r5aUEl6Z";
        String clientSecret = "sQHqJT2l9k";
        
        try {
        	word = URLEncoder.encode(word, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/news.json?dispaly=2&query=" + word;
        //System.out.println(apiURL);

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        //System.out.println(responseBody);
        
        //파싱
        JSONParser parser = new JSONParser();
        
        try {
			
        	JSONObject root 
        		= (JSONObject)parser.parse(responseBody);
        	
        	
        	
        	JSONArray list = (JSONArray)root.get("items");
        	
        	for (int i=0; i<list.size(); i++) {
        		
        		
        		
        		NewsDTO dto = new NewsDTO();
        		dto.setTitle(((JSONObject)list.get(i)).get("title").toString());
        		dto.setLink(((JSONObject)list.get(i)).get("link").toString());
        		
        		dto.setDescription(((JSONObject)list.get(i)).get("description").toString());
        		dto.setPubDate(((JSONObject)list.get(i)).get("pubDate").toString());
        		
        		//System.out.println(dto);
        		
        		nlist.add(dto);
        		
        		
        		
        	}//for
        	
        	return nlist;
        	
		} catch (Exception e) {
			System.out.println("NewsDAO.search");
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
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


    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private String readBody(InputStream body){
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

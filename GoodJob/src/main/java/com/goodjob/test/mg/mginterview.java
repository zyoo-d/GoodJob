package com.goodjob.test.mg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/interview.do")
public class mginterview extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InterviewDAO dao = new InterviewDAO();
        ArrayList<InterviewDTO> list = dao.list();
        	
        JSONArray arr = new JSONArray();
        
        for (InterviewDTO dto : list) {
        	JSONObject obj = new JSONObject();
        	
        	obj.put("ITV_SEQ",dto.getITV_SEQ());
        	obj.put("ITV_CPNAME",dto.getITV_CPNAME());
        	obj.put("ITV_MEETDATE",dto.getITV_MEETDATE());
        	obj.put("ITV_EVALUATION",dto.getITV_EVALUATION());
        	obj.put("ITV_REGDATE",dto.getITV_REGDATE());
        	obj.put("CP_SEQ",dto.getCP_SEQ());
        	obj.put("ID",dto.getID());
        	obj.put("ITV_DIFFICULTY",dto.getITV_DIFFICULTY());
        	obj.put("ITV_CATEGORY",dto.getITV_CATEGORY());
        	obj.put("ITV_CAREER",dto.getITV_CAREER());
        	obj.put("ITV_PERSONNEL",dto.getITV_PERSONNEL());
        	obj.put("ITV_QUESTION",dto.getITV_QUESTION());
        	obj.put("ITV_TIP",dto.getITV_TIP());
        	obj.put("ITV_WHETHER",dto.getITV_WHETHER());
        	
        	arr.add(obj);
        }
        
        
        resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		
		writer.print(arr);
	
		writer.close();
		
		
    }
}

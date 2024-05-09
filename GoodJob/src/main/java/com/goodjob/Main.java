package com.goodjob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.company.model.CompanyDTO;
import com.good.company.repository.CompanyDAO;

@WebServlet("/main.do")
public class Main extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
	    Object idObject = session.getAttribute("id");
	    String id = idObject != null ? idObject.toString() : "Guest"; 
	    
	    String hiring = req.getParameter("hiring");
		if(hiring != null) {
			hiring = "y";
		}else {
			hiring = "n";
		}
		
		HashMap<String, String> map = new HashMap<>();
		map.put("hiring", hiring);
	    
		
	    CompanyDAO cdao = new CompanyDAO();
	    ArrayList<CompanyDTO> clist = cdao.mainComList(map);
//	    String cp_seq = "";
//	    
//	    for(CompanyDTO cdto : clist) {
//	    	cp_seq = cdto.getCp_seq();
//	    	
//	    }
	    String[] key = {
	    	    "인공지능", 
	    	    "머신러닝", 
	    	    "빅데이터", 
	    	    "클라우드 컴퓨팅", 
	    	    "사이버보안", 
	    	    "증강현실", 
	    	    "가상현실", 
	    	    "블록체인", 
	    	    "사물인터넷", 
	    	    "5G 기술", 
	    	    "로봇공학", 
	    	    "서버리스 아키텍처"
	    	};
	    
	    Random rnd = new Random();
	    ArrayList<String> keywords = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				keywords.add(key[rnd.nextInt(key.length)]);
			}
	    
	    req.setAttribute("keywords", keywords);
	    req.setAttribute("hiring", hiring);
	    req.setAttribute("clist", clist);
	    //System.out.println(clist);
		System.out.println(id);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/main.jsp");
		dispatcher.forward(req, resp);

	}

}
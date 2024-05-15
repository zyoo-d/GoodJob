package com.goodjob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
/**
 * Main 클래스는 웹 어플리케이션의 주 진입점을 제공하며, 사용자의 요청에 따라 적절한 응답을 반환합니다.
 * 이 클래스는 HTTP 요청을 처리하는 서블릿으로서, 특정 사용자 요청에 따른 정보 조회 및 전달을 담당합니다.
 */
@WebServlet("/main.do")
public class Main extends HttpServlet {
	/**
     * HTTP GET 요청을 처리합니다. 사용자의 세션 정보와 파라미터를 기반으로 기업 정보를 조회하고, 결과를 사용자에게 반환합니다.
     * 
     * @param req HTTP 요청 정보를 담고 있는 HttpServletRequest 객체입니다.
     * @param resp HTTP 응답 정보를 담고 있는 HttpServletResponse 객체입니다.
     * @throws ServletException 서블릿 실행 중 예외가 발생한 경우
     * @throws IOException 입출력 작업 중 예외가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Object idObject = session.getAttribute("id");
		String id = idObject != null ? idObject.toString() : "Guest";

		String hiring = req.getParameter("hiring");
		if (hiring == null || hiring.equals("y")) {
			hiring = "n";
		} else {
			hiring = "y";
		}

		HashMap<String, String> map = new HashMap<>();
		map.put("hiring", hiring);

		CompanyDAO cdao = new CompanyDAO();
		ArrayList<CompanyDTO> clist = cdao.mainComList(map);

		String[] key = { "인공지능", "머신러닝", "빅데이터", "클라우드 컴퓨팅", "사이버보안", "증강현실", "가상현실", "블록체인", "사물인터넷", "5G 기술", "로봇공학", "서버리스 아키텍처", "확장 현실", "디지털 신뢰", "3D 프린팅", "유전체학", "에지 컴퓨팅", "양자 컴퓨팅" };

		Random rnd = new Random();
		HashSet<String> keywords = new HashSet<>();
		while (keywords.size() < 4) {
			keywords.add(key[rnd.nextInt(key.length)]);
		}

		req.setAttribute("keywords", keywords);
		req.setAttribute("hiring", hiring);
		req.setAttribute("clist", clist);

		System.out.println(id);
		cdao.close();

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/main.jsp");
		dispatcher.forward(req, resp);

	}

}
package com.good.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.good.board.model.CommentDTO;
import com.good.company.model.CompanyDTO;
import com.good.company.model.RecruitDTO;
import com.good.company.model.ReviewDTO;
import com.good.company.repository.CompanyDAO;
import com.good.company.repository.RecruitDAO;
import com.good.company.repository.ReviewDAO;
import com.good.news.NewsDAO;
import com.good.news.NewsDTO;

@WebServlet("/user/company/companyview.do")
public class DetailCompany extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		String cp_seq = req.getParameter("cp_seq");
		String headcp_seq = req.getParameter("headcp_seq");
		String word = req.getParameter("word");
		String page = req.getParameter("page");
		String search = req.getParameter("search");
		String hiring = req.getParameter("hiring");

		if (headcp_seq != null && cp_seq == null) {
			cp_seq = headcp_seq;
		}

		CompanyDAO dao = new CompanyDAO();
		CompanyDTO dto = dao.get(cp_seq);

		// 평균연봉
		int avg_salary = dto.getHire_avr_salary();

		if (avg_salary == 0) {
			dto.setHire_avr_salary(0);
		}

		dto.setHire_avr_salary((int) Math.round((float) avg_salary / 10000));// (단위:만원)

		// 업계평균연봉
		int idst_avg_salary = dao.getIdstSalary(dto.getIdst_code());
		dto.setIdst_avg_salary(idst_avg_salary);

		// 기업재무정보
		ArrayList<Long>[] flist = dao.getCompanyFinance(cp_seq);
		// System.out.println(flist[0]);

		// 리뷰조회
		ReviewDAO rdao = new ReviewDAO();
		ArrayList<ReviewDTO> listReview = rdao.listReview(cp_seq);
		// System.out.println("Number of reviews fetched: " + listReview.size());
		// System.out.println(listReview);

		// 태그출력
		ReviewDAO tdao = new ReviewDAO();

		//ArrayList<ReviewDTO> ComTaglist = tdao.tagList(cp_seq); 
		// 상위 태그 목록 조회
        CompanyDAO cdao = new CompanyDAO();
        ArrayList<String> topTags = cdao.getTopTagsByCpSeq(cp_seq);

		


		// 기업직무정보
		RecruitDAO jdao = new RecruitDAO();
		ArrayList<String> comJobList = jdao.comJob(cp_seq);

		// 채용공고목록
		RecruitDAO rcdao = new RecruitDAO();
		ArrayList<RecruitDTO> comRecruitList = rcdao.comRecruitList(cp_seq);

		for (RecruitDTO rdto : comRecruitList) {
			// 날짜 조작
			rdto.setEnddate(rdto.getEnddate().substring(0, 10));
			// 주소 조작
			String address = rdto.getCp_address();
			if (address.contains("서울특별시")) {
				address = address.replaceAll("서울특별시", "서울");
			}
			int firstSpaceIndex = address.indexOf(' '); // 첫 번째 공백의 위치
			int secondSpaceIndex = address.indexOf(' ', firstSpaceIndex + 1); // 두 번째 공백의 위치
			address = address.substring(0, secondSpaceIndex);
			rdto.setCp_address(address);

		}

		// 기업뉴스

		String cp_name = dto.getCp_name();
		NewsDAO ndao = new NewsDAO();
		ArrayList<NewsDTO> nlist = ndao.search(cp_name);
		System.out.println(nlist);

		// 실시간 댓글
		ArrayList<CommentDTO> livecommentlist = dao.listComment(cp_seq);
		
		System.out.println(livecommentlist);
		req.setAttribute("nlist", nlist);

		req.setAttribute("dto", dto);
		req.setAttribute("word", word);
		req.setAttribute("page", page);
		req.setAttribute("listReview", listReview);
		req.setAttribute("comRecruitList", comRecruitList);
		req.setAttribute("search", search);
		req.setAttribute("hiring", hiring);
		//req.setAttribute("ComTaglist", ComTaglist);
		// req.setAttribute("flist",flist);
		
		req.setAttribute("livecommentlist", livecommentlist);
		//req.setAttribute("ComTaglist", ComTaglist);
		req.setAttribute("comJobList", comJobList);
		req.setAttribute("salesList", flist[0]);
		req.setAttribute("ebitList", flist[1]);
		req.setAttribute("incomeList", flist[2]);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/companyview.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String cp_seq = req.getParameter("cp_seq");
		RecruitDAO dao = new RecruitDAO();

	}
}
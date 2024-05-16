package com.good.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.good.company.model.CompanyDTO;
import com.good.company.model.ReviewDTO;
import com.good.company.repository.CompanyDAO;
import com.good.company.repository.ReviewDAO;
import com.good.company.repository.TagDAO;

/**
 * 리뷰 수정 서블릿
 * 
 * 이 서블릿은 리뷰 수정 페이지를 처리하고, 리뷰 수정 요청을 처리합니다. GET 요청 시 리뷰 수정 페이지를 로드하고, POST 요청 시
 * 리뷰 수정 처리를 수행합니다.
 */
@WebServlet("/user/company/review/editreview.do")
public class EditReview extends HttpServlet {

	/**
	 * GET 요청 처리 메소드
	 * 
	 * 리뷰 수정 페이지를 로드합니다. 세션에서 사용자 ID를 가져오고, 요청 파라미터에서 리뷰 시퀀스를 가져옵니다.
	 * 리뷰 정보와 기업 정보를 조회하여 JSP 페이지로 전달합니다. 관리자 여부와 작성자 여부를 확인하여 JSP 페이지에 전달합니다.
	 * 
	 * @param req  HttpServletRequest 객체
	 * @param resp HttpServletResponse 객체
	 * @throws ServletException 서블릿 예외 발생 시
	 * @throws IOException      입출력 예외 발생 시
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");

		String cp_rv_seq = req.getParameter("cp_rv_seq");

		ReviewDAO rdao = new ReviewDAO();
		ReviewDTO rdto = rdao.getReviewByCpRvSeq(cp_rv_seq);
		// 태그
		TagDAO tdao = new TagDAO();
		rdto.setTag_list(tdao.getReviewTag(cp_rv_seq));
		System.out.println(rdto);
		CompanyDAO cdao = new CompanyDAO();
		CompanyDTO cdto = cdao.getCompanyBySeq(rdto.getCp_seq());

		boolean isAdmin = id != null && id.equals("admin"); // 관리자 여부 확인 로직
		boolean isAuthor = id != null && id.equals(rdto.getId()); // 작성자 여부 확인 로직 추가

		if (rdto != null && cdto != null) {
			req.setAttribute("isAdmin", isAdmin);
			req.setAttribute("isAuthor", isAuthor);
			req.setAttribute("rdto", rdto);
			req.setAttribute("cdto", cdto);

			RequestDispatcher dispatcher = req
					.getRequestDispatcher("/WEB-INF/views/user/company/review/editreview.jsp");
			dispatcher.forward(req, resp);
		} else {
			resp.sendRedirect("/good/user/mypage/myreview.do");
		}
	}
	
    /**
     * POST 요청 처리 메소드
     * 
     * 리뷰 수정 요청을 처리합니다.
     * 세션에서 사용자 ID를 가져오고, 요청 파라미터에서 액션, 리뷰 시퀀스, 태그 키워드, 삭제할 태그 등을 가져옵니다.
     * 리뷰 정보와 태그 정보를 조회하고, 해시태그 수정 및 삭제 작업을 수행합니다.
     * 리뷰 수정 로직을 처리하고, 수정된 정보를 데이터베이스에 업데이트합니다.
     * 
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외 발생 시
     * @throws IOException      입출력 예외 발생 시
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		String action = req.getParameter("action");
		String cp_rv_seq = req.getParameter("cp_rv_seq");
		String tag_keyword = req.getParameter("tag_keyword");
		String[] removeTag = req.getParameterValues("removeTag");

		ReviewDAO rdao = new ReviewDAO();
		ReviewDTO rdto = rdao.getReviewByCpRvSeq(cp_rv_seq);
		TagDAO tdao = new TagDAO();

		// 해시태그 수정
		if (tag_keyword != null && !tag_keyword.equals("") && !tag_keyword.equals("[]")) {
			try {

				JSONParser parser = new JSONParser();
				JSONArray arr = (JSONArray) parser.parse(tag_keyword);

				for (Object obj : arr) {
					JSONObject tagObj = (JSONObject) obj;
					String tagKeyword = (String) tagObj.get("value");

					if (tdao.existHashtag(tagKeyword)) {
						tdao.addHashtag(tagKeyword);
					}

					String tag_seq = tdao.getHseq(tagKeyword);

					HashMap<String, String> map = new HashMap<String, String>();
					map.put("cp_rv_seq", cp_rv_seq);
					map.put("tag_seq", tag_seq);

					if (tdao.existTagging(map)) {

						tdao.addTagging(map, cp_rv_seq);
					} else {

					}
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		// 삭제
		HashMap<String, String> map = new HashMap<String, String>();

		if (removeTag != null) {
			for (String tagName : removeTag) {
				map.put("cp_rv_seq", cp_rv_seq);
				map.put("tag_keyword", tagName);

				tdao.delTagging(map);
			}
		}

		if (action != null && action.equals("update")) {
			// 리뷰 수정 로직
			rdto.setLinereview(req.getParameter("linereview"));
			rdto.setGood(req.getParameter("good"));
			rdto.setBad(req.getParameter("bad"));
			// 수정된 별점 설정
			rdto.setSalary_score(Double.parseDouble(req.getParameter("salary_score")));
			rdto.setWelfare_score(Double.parseDouble(req.getParameter("welfare_score")));
			rdto.setStability_score(Double.parseDouble(req.getParameter("stability_score")));
			rdto.setCulture_score(Double.parseDouble(req.getParameter("culture_score")));
			rdto.setGrowth_score(Double.parseDouble(req.getParameter("growth_score")));
			rdao.updateReviewByCpRvSeq(rdto);
		}
		resp.sendRedirect("/good/user/mypage/myreview.do");
	}

}

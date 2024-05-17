package com.good.matching;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.alert.Alert;
import com.good.matching.model.WishDTO;
import com.good.matching.repository.WishDAO;
/**
 * EditWish 서블릿 클래스는 사용자가 기존에 저장한 '위시리스트' 정보를 수정할 때 사용됩니다.
 * 사용자는 교육 수준, 원하는 연봉, 경력, 지역 선호, 복지 선호 등의 정보를 업데이트할 수 있습니다.
 * 데이터는 데이터베이스에 저장되며, 성공적으로 수정되면 사용자는 매칭 페이지로 리다이렉트됩니다.
 */
@WebServlet("/user/matching/editwish.do")
public class EditWish extends HttpServlet {
	/**
     * POST 요청을 통해 사용자의 수정된 '위시리스트' 정보를 받아 처리합니다.
     * 입력된 데이터를 기반으로 데이터베이스에 저장된 기존 정보를 업데이트하고,
     * 성공적으로 업데이트되면 매칭 페이지로 리다이렉트합니다.
     *
     * @param req 클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
     * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
     * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 예외 발생시 던져집니다.
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");

        String edu_seq = req.getParameter("edu_seq");
        String salary_seq = req.getParameter("salary_seq");
        String career = req.getParameter("career");
        String[] locations = req.getParameterValues("locations");
        String[] welfare = req.getParameterValues("welfare");

		WishDTO dto = new WishDTO();
		WishDAO dao = new WishDAO();

		dto.setId(id);
		dto.setEdu_seq(edu_seq);
		dto.setSalary_seq(salary_seq);
		dto.setCareer(career);
		ArrayList<String> lc_code = new ArrayList<String>();
		for(String location : locations) {
			lc_code.add(location);
		}
		dto.setLc_code(lc_code);
		ArrayList<String> wel_seq = new ArrayList<String>();
		for(String wel : welfare) {
			wel_seq.add(wel);
		}
		dto.setWel_seq(wel_seq);

		int result = dao.editWish(dto);
		dao.close();
		if(result==1){
			session.setAttribute("wish", "1"); 	
			resp.sendRedirect("/good/user/matching.do");
		} else {
			Alert.fail(resp);
		}

	}

}
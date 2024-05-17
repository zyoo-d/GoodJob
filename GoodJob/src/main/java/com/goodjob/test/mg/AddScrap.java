package com.goodjob.test.mg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.company.repository.CompanyDAO;

@WebServlet("/test/mg/addscrap.do")
public class AddScrap extends HttpServlet {

    /**
     * HTTP POST 요청을 처리하여 사용자의 스크랩 정보를 저장합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 관련 에러 발생 시
     * @throws IOException      I/O 에러 발생 시
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// 사용자 ID와 채용 정보 ID 가져오기
    	String id = req.getParameter("id");
        String cpSeq = req.getParameter("cp_seq");

        // CompanyDAO를 사용하여 사용자의 스크랩 정보 저장
        CompanyDAO dao = new CompanyDAO();
        
     
        int result = dao.addScrap(id, cpSeq);
        // 스크랩 저장 결과 응답 전송
        if (result > 0) {
            
            resp.getWriter().write("success");
        } else {
          
            resp.getWriter().write("error");
        }
    }

}
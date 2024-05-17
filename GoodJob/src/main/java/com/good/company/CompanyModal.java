package com.good.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.company.model.CompanyDTO;
import com.good.company.repository.CompanyDAO;
import com.good.company.repository.CompareDAO;
/**
 * 기업 선택을 위한 팝업 서블릿입니다.
 */
@WebServlet("/user/company/cp_selectModal.do")
public class CompanyModal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        

        String page = req.getParameter("page");

        int nowPage = 0; // 현재페이지
        int totalCount = 0; // 총 게시물 수
        int pageSize = 10; // 출력 게시물 수
        int totalPage = 0; // 총페이지
        int begin = 0; // 페이지 시작
        int end = 0; // 페이지 끝
        int n = 0; // 페이지바
        int loop = 0;
        int blockSize = 5;

        if (page == null || page.equals("")) {
            nowPage = 1;
        } else {
            nowPage = Integer.parseInt(page);
        }

        begin = ((nowPage - 1) * pageSize) + 1;
        end = begin + pageSize - 1;

        // 검색 기록 가져오기
        String sort = req.getParameter("sort");
        String column = (req.getParameter("column") != null ? req.getParameter("column") : "");

        String word = (req.getParameter("word") != null ? req.getParameter("word") : "");
        String hiring = req.getParameter("hiring");

        String search = "n"; // 목록보기(n), 검색하기(y)

        if (!word.isEmpty()) {
            search = "y";
        }
        if (hiring == null) {
            hiring = "n";
        }

        if (sort == null || sort.isEmpty()) {
            sort = "salary"; // 기본 정렬 기준
        }
        if ((column == null && word == null) || word.equals("")) {
            search = "n";
        } else {
            search = "y";
        }
        String salary_seq = req.getParameter("salary_seq");
        String[] cp_address = req.getParameterValues("cp_address");
        String selectedLocations = "";
        if (cp_address != null && cp_address.length > 0) {
            selectedLocations = String.join(",", cp_address);
        }

        
        String[] cp = req.getParameterValues("compareCp");
        String tag1 = (cp != null && cp.length > 0 && cp[0] != null) ? cp[0] : "";

        String[] tag1Array = tag1.split(",");
        
        req.setAttribute("tag1Array", tag1Array);
        System.out.println(Arrays.toString(tag1Array));
		
        
        
        HashMap<String, String> map = new HashMap<>();
        

        map.put("search", search);
        map.put("column", column);
        map.put("word", word);
        map.put("begin", begin + "");
        map.put("end", end + "");
        map.put("hiring", hiring);
        map.put("sort", sort);
        map.put("salary_seq", salary_seq);
        map.put("cp_address", selectedLocations);

        // 목록 출력
        CompareDAO dao = new CompareDAO();
		ArrayList<CompanyDTO> comListInfo = dao.comListInfo(map);
		
		

		String unit = "";
        int com_rcrt_cnt = 0;
        for (CompanyDTO dto : comListInfo) {

            // 주소
            String address = dto.getCp_address();
            if (address.contains("서울특별시")) {
                address = address.replaceAll("서울특별시", "서울");
            }
            int firstSpaceIndex = address.indexOf(' '); // 첫 번째 공백의 위치
            int secondSpaceIndex = address.indexOf(' ', firstSpaceIndex + 1); // 두 번째 공백의 위치
            address = address.substring(0, secondSpaceIndex);
            dto.setCp_address(address);

        }
        
        CompanyDAO cdao = new CompanyDAO();
        
        // 총게시물수
        totalCount = cdao.getCompanyCount(map);
		totalPage = (int) Math.ceil((double) totalCount / pageSize);

        // 페이지 바 작업
        StringBuilder sb = new StringBuilder();

        loop = 1; // 루프 변수(10바퀴)
        n = ((nowPage - 1) / blockSize) * blockSize + 1; // 페이지 번호 역할

        // 이전 5페이지
        if (n == 1) {
            sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>");
            sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>");
        } else if (n <= 5) {
            sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>");
            sb.append(String.format(
                    "<li class='page-item z-custom'><a class='page-link' href='/good/user/company/cp_selectModal.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
                    n - 1, hiring, word));
        } else if (n > 5) {
            sb.append(String.format(
                    "<li class='page-item z-custom'><a class='page-link' href='/good/user/company/cp_selectModal.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>",
                    n - 5, hiring, word));
            sb.append(String.format(
                    "<li class='page-item z-custom'><a class='page-link' href='/good/user/company/cp_selectModal.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
                    n - 1, hiring, word));
        }

        while (!(loop > blockSize || n > totalPage)) {
            if (n == nowPage) {
                sb.append(String.format(
                        "<li class='page-item z-custom'><a class='page-link' href='#!' style='background-color: #6777EE; color: #FFF; border-color: #6777EE;'>%d</a></li>",
                        n));
            } else {
                sb.append(String.format(
                        "<li class='page-item z-custom'><a class='page-link' href='/good/user/company/cp_selectModal.do?page=%d&hiring=%s&word=%s'>%d</a></li>",
                        n, hiring, word, n));
            }
            loop++;
            n++;
        }

        // 다음 5페이지
        if (n >= totalPage) {
            sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>");
            sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>");
        } else if (n >= totalPage - 5) {
            sb.append(String.format(
                    "<li class='page-item z-custom'><a class='page-link' href='/good/user/company/cp_selectModal.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
                    n, hiring, word));
            sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>");
        } else {
            sb.append(String.format(
                    "<li class='page-item z-custom'><a class='page-link' href='/good/user/company/cp_selectModal.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
                    n, hiring, word));
            sb.append(String.format(
                    "<li class='page-item z-custom'><a class='page-link' href='/good/user/company/cp_selectModal.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>",
                    n + 5, hiring, word));
        }

        req.setAttribute("comListInfo", comListInfo);
        req.setAttribute("map", map); // 페이지 begin. end hiring
        req.setAttribute("com_rcrt_cnt", com_rcrt_cnt);

        // 페이징
        req.setAttribute("nowPage", nowPage); // 페이지 번호
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPage", totalPage); // 페이지 번호
        req.setAttribute("pagebar", sb.toString()); // 페이지 바 작업
        req.setAttribute("tag1", tag1); // 페이지 바 작업

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/cp_selectModal.jsp");
        dispatcher.forward(req, resp);

    }

}

package com.good.matching;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.alert.Alert;
import com.good.company.repository.CompanyDAO;
import com.good.company.repository.CompareDAO;
import com.good.matching.model.MatchingDTO;
import com.good.matching.repository.MatchingDAO;
import com.good.user.repository.UserDAO;
/**
 * Matching 서블릿 클래스는 사용자의 선호도 및 요구 사항을 바탕으로 회사와의 매칭을 수행합니다.
 * 이 서블릿은 사용자의 프로필과 입력된 기업 데이터를 비교하여 가장 적합한 회사를 추천합니다.
 */
@WebServlet("/user/matching.do")
public class Matching extends HttpServlet {
	/**
     * GET 요청을 통해 매칭 프로세스를 실행하고 결과를 사용자에게 표시합니다.
     * 세션에서 사용자 정보를 확인하고, 설문조사 및 위시리스트 데이터를 기반으로 매칭을 수행합니다.
     *
     * @param req 클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
     * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
     * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 예외 발생시 던져집니다.
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		String wish = (String) session.getAttribute("wish");
		String prefer = (String) session.getAttribute("prefer");

		if (id == null || id.equals("")) {
			Alert.needLogin(resp, "/good/user/signin.do");
		} else if (prefer.equals("0")) {
			PrintWriter writer = resp.getWriter();
			writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('설문조사 후 이용 가능합니다.');");
			writer.println("location.href = \"/good/user/matching/viewsurvey.do\";");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
		} else if (wish.equals("0") && prefer.equals("1")) {
			PrintWriter writer = resp.getWriter();
			writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('선호근무조사 후 이용 가능합니다.');");
			writer.println("location.href = \"/good/user/matching/viewwish.do\";");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
		} else if (wish.equals("1") && prefer.equals("1")) {

			MatchingDAO dao = new MatchingDAO();
			UserDAO userDAO = new UserDAO();

			String name = userDAO.getName(id);

			HashMap<String, Integer> scoreMap = dao.getColumn(id);

			MatchingAlgo algo = new MatchingAlgo();

			String[] columnNames = algo.sort(scoreMap);

			ArrayList<MatchingDTO> list = dao.getMatching(columnNames, id);

			CompanyDAO companyDAO = new CompanyDAO();

			for (MatchingDTO mdto : list) {

				mdto.setDto(companyDAO.getMatchingInfo(mdto));
				mdto.getDto().setTag_list(companyDAO.getTaglist(mdto.getCp_seq()));

			}

			MatchingDTO dto = dao.getScore(id);
			String maxField = dto.findMax();
			String minField = dto.findMin();
			Map<String, String> keywords = new HashMap<>();

			switch (maxField) {
			case "salary":
				keywords.put("best", "부자가 되고싶고, ");
				break;
			case "welfare":
				keywords.put("best", "혜택을 중요하게 여기며, ");
				break;
			case "stability":
				keywords.put("best", "안정을 추구하고, ");
				break;
			case "culture":
				keywords.put("best", "함께 지내는 좋은 동료를 원하며, ");
				break;
			case "potential":
				keywords.put("best", "지속적으로 성장하길 원하며, ");
				break;
			}
			switch (minField) {
			case "salary":
				keywords.put("worst", "소득보다 만족을 중요시하는 ");
				break;
			case "welfare":
				keywords.put("worst", "최소한의 복지로 만족하는 ");
				break;
			case "stability":
				keywords.put("worst", "변화와 도전을 좋아하는 ");
				break;
			case "culture":
				keywords.put("worst", "혼자서도 잘하는 ");
				break;
			case "potential":
				keywords.put("worst", "성장이 더뎌도 괜찮은 ");
				break;
			}

			ScoreMatcher.calculateMatchingRate(list, dto);
			Collections.sort(list, Comparator.reverseOrder());
			
			System.out.println(list.size());
			
			ArrayList<MatchingDTO> top3 = new ArrayList<>(list.subList(0, 3));

			HashMap<String, String> map = new HashMap<>();
			int num = 1;
			for (MatchingDTO mdto : top3) {
				
				int hireAvrSalary = mdto.getDto().getHire_avr_salary();
	            int formattedHireAvrSalary = (int) Math.round((double) hireAvrSalary / 10000); 
	            mdto.getDto().setHire_avr_salary(formattedHireAvrSalary);
				
	            long fncEbit = mdto.getDto().getFnc_ebit();
	            long formattedFncEbit = Math.round((double) fncEbit / 10000000); 
	            mdto.getDto().setFnc_ebit(formattedFncEbit); 
	            
	            long fncSales = mdto.getDto().getFnc_sales();
	            long formattedFncSales = Math.round((double) fncSales / 10000000);
	            mdto.getDto().setFnc_sales(formattedFncSales);
	            
	            long fncIncome = mdto.getDto().getFnc_income();
	            long formattedFncIncome = Math.round((double) fncIncome / 10000000);
	            mdto.getDto().setFnc_income(formattedFncIncome);
	            
	            map.put("tag"+num, mdto.getDto().getCp_name());
				num++;
	            
				double matchScore = mdto.getMatchScore(); 
				int scoreCategory = (int) matchScore / 10;
				
				if (scoreCategory != 8) {
					switch (scoreCategory) {
					case 10:
					case 9:
						mdto.setMatchText("완벽한 매치! 회원님과<br>이 기업은 정말 잘 어울립니다.");
						break;
					case 7:
						mdto.setMatchText("꽤 좋은 매치네요!<br>함께 성장할 가능성이 보여요.");
						break;
					case 6:
						mdto.setMatchText("괜찮은 매치네요!<br>추가 정보들을 참고해보세요.");
						break;
					default:
						mdto.setMatchText("다양성을 추구한다면<br>이 기업도 고려해보세요.");
						break;
					}
				} else {
					switch ((int) matchScore) {
					case 89:
					case 87:
						mdto.setMatchText("놀라운 일치! 이곳과 함께라면<br>성장할 일만 남으셨어요!");
						break;
					case 88:
					case 86:
						mdto.setMatchText("아주 매력적인 매치네요!<br>지원 바로해야겠는걸요?");
						break;
					case 85:
					case 83:
						mdto.setMatchText("이 기업은 회원님의 열정과<br>매우 잘 맞습니다!");
						break;
					case 84:
					case 82:
						mdto.setMatchText("매우 가능성이 커보여요!<br>심도 있게 고려해 보세요.");
						break;
					case 81:
					case 80:
						mdto.setMatchText("이상적인 매치에요.<br>지원할 준비가 되었나요?");
						break;
					}

				}

			}

			list.subList(0, 3).clear();

			CompareDAO compareDAO = new CompareDAO();
			//재무 정보
			ArrayList<Long>[] flist = compareDAO.getCompanyFinance(map);

			req.setAttribute("flist", flist);
			req.setAttribute("map", map);
			
			req.setAttribute("name", name);
			req.setAttribute("top3", top3);
			req.setAttribute("list", list);
			req.setAttribute("dto", dto);
			req.setAttribute("keywords", keywords);

			dao.close();
			companyDAO.close();
			userDAO.close();
			compareDAO.close();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/matching/matching.jsp");
			dispatcher.forward(req, resp);

		}

	}

}

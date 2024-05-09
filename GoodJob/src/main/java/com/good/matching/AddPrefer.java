package com.good.matching;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.alert.Alert;
import com.good.matching.model.PreferDTO;
import com.good.matching.repository.PreferDAO;

@WebServlet("/user/matching/addprefer.do")
public class AddPrefer extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int sal_stab = Integer.parseInt(req.getParameter("연봉1"));
		int sal_wel = Integer.parseInt(req.getParameter("연봉3"));
		int sal_cul = Integer.parseInt(req.getParameter("연봉4"));
		int sal_pot = Integer.parseInt(req.getParameter("연봉5"));

		int wel_sal = Integer.parseInt(req.getParameter("복지18"));
		int wel_pot = Integer.parseInt(req.getParameter("복지19"));
		int wel_cul = Integer.parseInt(req.getParameter("복지20"));
		int wel_stab = Integer.parseInt(req.getParameter("복지21"));

		int stab_pot = Integer.parseInt(req.getParameter("조직안정성6"));
		int stab_wel = Integer.parseInt(req.getParameter("조직안정성7"));
		int stab_cul = Integer.parseInt(req.getParameter("조직안정성8"));
		int stab_sal = Integer.parseInt(req.getParameter("조직안정성9"));

		int cul_sal = Integer.parseInt(req.getParameter("조직문화10"));
		int cul_stab = Integer.parseInt(req.getParameter("조직문화11"));
		int cul_pot = Integer.parseInt(req.getParameter("조직문화12"));
		int cul_wel = Integer.parseInt(req.getParameter("조직문화13"));

		int pot_cul = Integer.parseInt(req.getParameter("성장가능성14"));
		int pot_wel = Integer.parseInt(req.getParameter("성장가능성15"));
		int pot_sal = Integer.parseInt(req.getParameter("성장가능성16"));
		int pot_stab = Integer.parseInt(req.getParameter("성장가능성17"));

		HashMap<String, Integer> map = new HashMap<>();

		map.put("sal_stab", sal_stab);
		map.put("sal_wel", sal_wel);
		map.put("sal_cul", sal_cul);
		map.put("sal_pot", sal_pot);
		map.put("wel_sal", wel_sal);
		map.put("wel_pot", wel_pot);
		map.put("wel_cul", wel_cul);
		map.put("wel_stab", wel_stab);
		map.put("stab_pot", stab_pot);
		map.put("stab_wel", stab_wel);
		map.put("stab_cul", stab_cul);
		map.put("stab_sal", stab_sal);
		map.put("cul_sal", cul_sal);
		map.put("cul_stab", cul_stab);
		map.put("cul_pot", cul_pot);
		map.put("cul_wel", cul_wel);
		map.put("pot_cul", pot_cul);
		map.put("pot_wel", pot_wel);
		map.put("pot_sal", pot_sal);
		map.put("pot_stab", pot_stab);

		String[] prefer = { "sal", "wel", "stab", "cul", "pot" };
		int sum = 0;

		for (int i = 0; i < prefer.length; i++) {

			for (int j = i + 1; j < prefer.length; j++) {

				int front = map.get(prefer[i] + "_" + prefer[j]);
				int back = map.get(prefer[j] + "_" + prefer[i]);

//                5 2 -2 -5 값 빼기 10 + 10 %
//                뺄경우에는 10이 정확도 100%
//                0, 3, 7, 10
//                연봉 > 조직안정성

				switch (Math.abs(front - back)) {
				case 10:
					sum += 10;
					break;
				case 7:
					sum += 8;
					break;
				case 3:
				case 4:
					sum += 6;
					break;
				case 0:
					sum += 4;
					break;
				}
				System.out.println(sum);
			}
		}
		
		String edit = req.getParameter("edit");

		// 정확도 = sum > 50% 아래면 return
		if (sum < 50) {
			PrintWriter writer = resp.getWriter();
			writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('"+sum+"정확도가 너무 낮아 정보 활용이 어렵습니다. 다시 진행해주세요 :)');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			
		} else {
			
			PreferDTO dto = new PreferDTO();
			PreferDAO dao = new PreferDAO();
			
			HttpSession session = req.getSession();
			String id = (String) session.getAttribute("id");
			
			if(edit.equals("Y")) {
				dao.delPrefer(id);
			}
			
			int sal = sal_stab + sal_wel + sal_cul + sal_pot;
			int wel = wel_sal + wel_pot + wel_cul + wel_stab;
			int stab = stab_pot + stab_wel + stab_cul + stab_sal;
			int cul = cul_sal + cul_stab + cul_pot + cul_wel;
			int pot = pot_cul + pot_wel + pot_sal + pot_stab;

			dto.setId(id);
			dto.setSalary(sal);
			dto.setWelfare(wel);
			dto.setStability(stab);
			dto.setCulture(cul);
			dto.setPotential(pot);

			int result = dao.addPrefer(dto);
			dao.close();

			if (result == 1) {
				session.setAttribute("prefer", "1"); 	
				resp.sendRedirect("/good/user/matching/viewwish.do");
			} else {
				Alert.fail(resp);
			}
		}
	}
}
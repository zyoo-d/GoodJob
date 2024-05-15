package com.good.board.study;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.alert.Alert;
import com.good.board.model.StudyDTO;
import com.good.board.repository.StudyDAO;
/**
 * AuthStudy 클래스는 사용자의 인증을 검사하여 특정 공부 세션에 대한 접근 권한을 확인합니다.
 */
public class AuthStudy {
	/**
     * 사용자가 특정 공부 세션에 대한 수정 권한을 가지고 있는지 검사합니다.
     * 사용자가 세션 소유자이거나 관리자 레벨을 가진 경우에만 허용합니다.
     * 
     * @param req HttpServletRequest 객체, 사용자 요청과 관련된 데이터 접근에 사용됩니다.
     * @param resp HttpServletResponse 객체, 필요한 경우 사용자에게 응답을 보냅니다.
     * @return boolean 권한이 없는 경우 true를 반환하고, 권한이 있는 경우 false를 반환합니다.
     * @throws IOException 네트워크 또는 서버 문제로 인해 입출력 문제가 발생할 경우 예외를 던집니다.
     */
	public static boolean check(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();
		String seq = req.getParameter("std_seq");

		// 글쓴이 본인?
		StudyDAO dao = new StudyDAO();
		StudyDTO dto = dao.getStudy(seq);
		dao.close();
		// id!=dto.id && lv !=2
		if (session.getAttribute("id") == null || (!session.getAttribute("id").toString().equals(dto.getId())
				&& !session.getAttribute("lv").toString().equals("2"))) {
			Alert.noPermission(resp);

			return true;
		}
		return false;
	}
}

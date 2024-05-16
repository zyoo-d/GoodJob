package com.good.board.qna;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.alert.Alert;
import com.good.board.model.QnaBoardDTO;
import com.good.board.model.StudyDTO;
import com.good.board.repository.QnaBoardDAO;
import com.good.board.repository.StudyDAO;

/**
 * QnA 게시글에 대한 접근 권한을 확인하는 유틸리티 클래스입니다.
 */
public class AuthQna {
	
	/**
     * 사용자의 QnA 게시글 접근 권한을 확인합니다.
     * 글쓴이 본인이거나 관리자(lv=2)인 경우에만 접근을 허용합니다.
     * 접근 권한이 없을 경우 경고 메시지를 표시하고 QnA 목록 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @return 접근 권한이 없는 경우 true, 접근 권한이 있는 경우 false
     * @throws IOException 입출력 예외
     */
	public static boolean check(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();
		String seq = req.getParameter("qna_seq");

		// 글쓴이 본인?
		QnaBoardDAO dao = new QnaBoardDAO();
		QnaBoardDTO dto = dao.getQna(seq);
		dao.close();
		// id!=dto.id && lv !=2
		if (session.getAttribute("id") == null || (!session.getAttribute("id").toString().equals(dto.getId())
				&& !session.getAttribute("lv").toString().equals("2"))) {
			Alert.noPermission(resp,"/good/user/qna/listqna.do");

			return true;
		}
		return false;
	}

}

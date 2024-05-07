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

public class AuthQna {
	
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
			Alert.noPermission(resp);

			return true;
		}
		return false;
	}

}

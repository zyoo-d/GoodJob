package com.good.board.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.alert.Alert;
import com.good.board.repository.QnaBoardDAO;

@WebServlet("/user/qna/delqna.do")
public class DelQna extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if(AuthQna.check(req, resp)) {
            return;
        }

        String qna_seq = req.getParameter("qna_seq");

        QnaBoardDAO dao = new QnaBoardDAO();
        int result = dao.delQna(qna_seq);

        if(result == 1) {

            resp.sendRedirect("/good/user/qna/listqna.do");


        } else {

            Alert.fail(resp);
            resp.sendRedirect("/good/user/qna/listqna.do?qna_seq=" + qna_seq);

        }





    }



}

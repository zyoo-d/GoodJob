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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String cpSeq = req.getParameter("cp_seq");

        System.out.println(id);
        System.out.println(cpSeq);
        
        CompanyDAO dao = new CompanyDAO();
        
     
        int result = dao.addScrap(id, cpSeq);

        if (result > 0) {
            
            resp.getWriter().write("success");
        } else {
          
            resp.getWriter().write("error");
        }
    }

}
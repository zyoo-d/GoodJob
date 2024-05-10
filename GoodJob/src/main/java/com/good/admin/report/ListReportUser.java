package com.good.admin.report;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.board.report.model.ReportCommonDTO;
import com.good.board.report.repository.ReportCommonDAO;

@WebServlet("/admin/listreportuser.do")
public class ListReportUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportCommonDAO reportCommonDAO = new ReportCommonDAO();
        String reportType = req.getParameter("rp_seq");
        Integer rp_seq = (reportType != null && !reportType.isEmpty()) ? Integer.parseInt(reportType) : null;
        int totalCount = reportCommonDAO.getTotalReportCount(rp_seq);
        int pageSize = 10;

        int currentPage = PageUtil.parseCurrentPage(req.getParameter("page"));

        PageUtil pageUtil = new PageUtil(totalCount, pageSize, currentPage);
        int startIndex = pageUtil.calculateStartIndex();
        int endIndex = pageUtil.calculateEndIndex();

        ArrayList<ReportCommonDTO> reportList = reportCommonDAO.getAllReportList(rp_seq, startIndex, endIndex);

        req.setAttribute("reportList", reportList);
        req.setAttribute("pageUtil", pageUtil);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/listreportuser.jsp");
        dispatcher.forward(req, resp);
        
    }
    
}



package com.good.admin.usermanagement;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.admin.block.model.BlockUserDTO;
import com.good.admin.block.repository.BlockUserDAO;

@WebServlet("/admin/listblockuser.do")
public class ListBlockUser extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BlockUserDAO blockUserDAO = new BlockUserDAO();
		int totalCount = blockUserDAO.getTotalBlockUserCount();
		int pageSize = 10;

		int currentPage = PageUtil.parseCurrentPage(req.getParameter("page"));

		PageUtil pageUtil = new PageUtil(totalCount, pageSize, currentPage);
		int startIndex = pageUtil.calculateStartIndex();
		int endIndex = pageUtil.calculateEndIndex();

		ArrayList<BlockUserDTO> blockUserList = blockUserDAO.getBlockUserList(startIndex, endIndex);

		req.setAttribute("blockUserList", blockUserList);
		req.setAttribute("pageUtil", pageUtil);
		
		blockUserDAO.close();

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/listblockuser.jsp");
		dispatcher.forward(req, resp);

	}

}

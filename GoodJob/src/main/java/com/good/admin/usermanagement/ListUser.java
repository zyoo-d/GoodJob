package com.good.admin.usermanagement;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

import com.good.admin.PageUtil;
import com.good.admin.block.model.UserManagementDTO;
import com.good.admin.repository.UserManagementDAO;
import com.good.user.repository.UserDAO;

@WebServlet("/admin/listuser.do")
public class ListUser extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UserManagementDAO userManagementDAO = new UserManagementDAO();
		int totalCount = userManagementDAO.getTotalUserCount();
		int pageSize = 10;

		int currentPage = PageUtil.parseCurrentPage(req.getParameter("page"));

		PageUtil pageUtil = new PageUtil(totalCount, pageSize, currentPage);
		int startIndex = pageUtil.calculateStartIndex();
		int endIndex = pageUtil.calculateEndIndex();

		ArrayList<UserManagementDTO> userList = userManagementDAO.getUserlist(startIndex, endIndex);

		req.setAttribute("userList", userList);
		req.setAttribute("pageUtil", pageUtil);

		userManagementDAO.close();

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/listuser.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] userIds = req.getParameterValues("userIds");
		System.out.println(Arrays.toString(userIds));

		UserDAO userDAO = new UserDAO();
		
		userDAO.unregister(userIds);
		
		userDAO.close();
		
		

	}


}

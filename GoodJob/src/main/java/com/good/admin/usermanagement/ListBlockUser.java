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
		
		ArrayList<BlockUserDTO> blockUserList = blockUserDAO.getBlockUserList();
		
		req.setAttribute("blockUserList", blockUserList);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/listblockuser.jsp");
		dispatcher.forward(req, resp);
		
	}

}

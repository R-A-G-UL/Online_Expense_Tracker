package com.servlet;

import java.io.IOException;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/deleteExpense")

public class DeleteExpenseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(req.getParameter("id"));
		ExpenseDao dao =new ExpenseDao(HibernateUtil.getSessionFactory()); 
		boolean f=dao.deleteExpense(id);
		HttpSession session=req.getSession();

		if(f) {
			session.setAttribute("msg","Deleted Successfully" );
			resp.sendRedirect("user/view_expense.jsp");

	}else {
		session.setAttribute("msg","Something Wrong on Server" );
		resp.sendRedirect("user/view_expense.jsp");

		
		
	}

	}
	
	

}

package com.servlet;

import java.io.IOException;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;
import com.entity.Expense;
import com.entity.User;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateExpense")
public class UpdateExpenseServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String title=req.getParameter("title");
		String date=req.getParameter("date");

		String time=req.getParameter("time");
		String description=req.getParameter("description");
		String price=req.getParameter("price");
		
		
		HttpSession session=req.getSession();
		User user = (User)session.getAttribute("loginUser");
		Expense ex=new Expense(title,date,time,description,price,user);
		ex.setId(id);
		ExpenseDao dao=new ExpenseDao(HibernateUtil.getSessionFactory());
		boolean f= dao.UpdateExpense(ex);
		
		if(f) {
			session.setAttribute("msg","Expense Updated Successfully" );
			resp.sendRedirect("user/view_expense.jsp");

	}else {
		session.setAttribute("msg","Something Wrong on Server" );
		resp.sendRedirect("user/view_expense.jsp");

		
		
	}

}

}

package com.servlet;

import java.io.IOException;



import com.dao.UserDao;
import com.db.HibernateUtil;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserRegister")

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String about = request.getParameter("about");

		User u = new User(fullName, email, password, about);

		//System.out.println(u);
		
		UserDao dao=new UserDao(HibernateUtil.getSessionFactory());
		boolean f=dao.saveuser(u);
		
		HttpSession session =request.getSession();
		if(f) {
			session.setAttribute("msg","Register Successfully" );
//			System.out.println("Register Sucessfully");
			response.sendRedirect("register.jsp");

	}else {
		session.setAttribute("msg","Something Wrong on Server" );
		response.sendRedirect("register.jsp");

		

//		System.out.println("Something Wrong on Server");
}
	}
}
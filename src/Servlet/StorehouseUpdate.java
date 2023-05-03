package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javabean.Storehouse;

/**
 * Servlet implementation class StudentUpdate
 */
@WebServlet("/update")
public class StorehouseUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StorehouseUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取表单传递的数据
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		Integer price = Integer.parseInt(request.getParameter("price"));
		String num = request.getParameter("num");
		String storehouseid = request.getParameter("storehouseid");
		String inventorydate = request.getParameter("inventorydate");
		Storehouse sh = new Storehouse(no, name, price, num,storehouseid,inventorydate);
		try {
			sh.update();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 跳转
		response.sendRedirect("storehouse_list.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

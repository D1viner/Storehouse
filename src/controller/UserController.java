package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
	@RequestMapping("/UserLogin")
	public String Login(HttpServletRequest request, HttpSession session, Model model)
			throws ClassNotFoundException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		User user = new User(username, password, role);
		try {
			if (user.login()) {
				session.setAttribute("current_user", user);
				if (role.equals("0")) {
					return "redirect:/sthouse_list";
				} else {
					session.setAttribute("current_user", user);
					return "redirect:/storehouse_list";
				}
			} else {
				return "redirect:/user_login.html";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return "redirect:/user_login.html";
		}
	}

	@RequestMapping("/UserRegister")
	public String Register(HttpServletRequest request, HttpSession session, Model model,
			RedirectAttributes redirectAttributes) throws ClassNotFoundException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		if (password.length() < 6) {
			return "redirect:/user_register.html";
		}

		User user = new User(username, password, role);
		try {
			user.register();
			return "redirect:/user_login.html";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return "redirect:/user_register.html";
		}
	}

	@RequestMapping("/user_list")
	public String list(HttpServletRequest request, HttpSession session, Model model)
			throws ClassNotFoundException, SQLException {
		User user = (User) session.getAttribute("current_user");
		
		if (user == null) {
		    return "redirect:/user_login.html";
		}
		
		String user_role;
		if (user.getRole().equals("0")) {
			user_role = "Admin";
		} else
			user_role = "User";

		String role = request.getParameter("role");

		int pageNo = 1;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		int pageSize =  6;
		int totalRow = new User().getTotalRow();
		int totalPage = totalRow % pageSize == 0 ? totalRow / pageSize : totalRow / pageSize + 1;
		ArrayList<Map> list = new User().querybypage(role, pageNo, pageSize);

		int begin = 1; 
		if (pageNo % 5 == 0)
			begin = (pageNo / 5 - 1) * 5 + 1;
		else
			begin = (pageNo / 5) * 5 + 1; 
		int end = begin + 4;
		if (end > totalPage)
			end = totalPage;

		model.addAttribute("list", list);
		model.addAttribute("user", user);
		model.addAttribute("role", role);
		model.addAttribute("user_role",user_role);
		
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalRow", totalRow);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
		return "user_list.jsp";
	}

	@RequestMapping("/useradd")
	public String add(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		System.out.print(username);
		System.out.print(password);
		System.out.print(role);

		User user = new User(username, password, role);
		try {
			user.register();
			return "redirect:/user_list";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/user_list";
	}
	
	@RequestMapping("/userdel")
	public String delete(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		String username = request.getParameter("username");
		User ur = new User(username);
		try {
			ur.del();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/user_list";
	}
	
	@RequestMapping("/usershow")
	public String show(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		String username = request.getParameter("username");
		User ur = new User().getOne(username);
		model.addAttribute("username", username);
		model.addAttribute("ur", ur);
		return "user_show.jsp";
	}

	@RequestMapping("/userupdate")
	public String update(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		User ur = new User(username, password, role);
		try {
			ur.update();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/user_list";
	} 
}

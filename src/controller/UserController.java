package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
	@RequestMapping("/UserLogin")
	public String Login(User user, HttpSession session, Model model) throws ClassNotFoundException, SQLException {

		String role = user.getRole();
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
				model.addAttribute("message", "用户名或密码错误!");
				return "forward:/user_login.jsp";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return "redirect:/user_login.jsp";
		}
	}

	@RequestMapping("/UserRegister")
	public String Register(User user, HttpSession session, Model model, RedirectAttributes redirectAttributes)
			throws ClassNotFoundException, SQLException {
		try {
			if (user.getPassword().length() < 6) { 
				model.addAttribute("error", "密码长度不能少于6位");
				return "forward:/user_register.jsp";
			}
			boolean containsUppercase = false, containsLowercase = false, containsNumber = false;
			for (char c : user.getPassword().toCharArray()) {
				if (Character.isUpperCase(c)) {
					containsUppercase = true;
				} else if (Character.isLowerCase(c)) {
					containsLowercase = true;
				} else if (Character.isDigit(c)) {
					containsNumber = true;
				}
			}
			if (!containsUppercase || !containsLowercase || !containsNumber) {
				model.addAttribute("error", "密码必须包含大小写字母和数字");
				return "forward:/user_register.jsp";
			}
			user.register();
			return "redirect:/user_login.jsp";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return "redirect:/user_register.jsp";
		}
	}

	@RequestMapping("/user_list")
	public String list(@RequestParam(defaultValue = "1") int pageNo, String role, HttpSession session, Model model)
			throws ClassNotFoundException, SQLException {
		User user = (User) session.getAttribute("current_user");

		if (user == null) {
			return "redirect:/user_login.jsp";
		}

		String user_role;
		if (user.getRole().equals("0")) {
			user_role = "Admin";
		} else
			user_role = "User";

		int pageSize = 6;
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
		model.addAttribute("user_role", user_role);

		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		return "user_list.jsp";
	}

	@RequestMapping("/useradd")
	public String add(User user) throws ClassNotFoundException, SQLException {
		user.register();
		return "redirect:/user_list";
	}

	@RequestMapping("/userdel")
	public String delete(String username) throws ClassNotFoundException, SQLException {
		User.del(username);
		return "redirect:/user_list";
	}

	@RequestMapping("/usershow")
	public String show(String username, Model model) throws ClassNotFoundException, SQLException {
		User ur = new User().getOne(username);
		model.addAttribute("username", username);
		model.addAttribute("ur", ur);
		return "user_show.jsp";
	}

	@RequestMapping("/userupdate")
	public String update(User user) throws ClassNotFoundException, SQLException {
		user.update();
		return "redirect:/user_list";
	}
}

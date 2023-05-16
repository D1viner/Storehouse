package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import model.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SthouseController {
	@RequestMapping("/sthouse_list")
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

		String id = request.getParameter("id");
		String key = request.getParameter("key");
		if (id == null)
			id = "";
		if (key == null)
			key = "";

		int pageNo = 1;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		int pageSize = 5;
		int totalRow = new Sthouse().getTotalRow();
		int totalPage = totalRow % pageSize == 0 ? totalRow / pageSize : totalRow / pageSize + 1;
		ArrayList<Map> list = new Sthouse().querybypage(id, key, pageNo, pageSize);

		int begin = 1;
		if (pageNo % 5 == 0)
			begin = (pageNo / 5 - 1) * 5 + 1;
		else
			begin = (pageNo / 5) * 5 + 1;
		int end = begin + 4;
		if (end > totalPage)
			end = totalPage;

		model.addAttribute("user", user);
		model.addAttribute("user_role", user_role);
		
		model.addAttribute("id", id);
		model.addAttribute("key", key);

		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("totalPage", totalPage);
		
		model.addAttribute("list", list);
		
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);

		return "sthouse_list.jsp";
	}
	
	@RequestMapping("/sthouseshow")
	public String show(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		String id = request.getParameter("id");
		Sthouse st = new Sthouse().getOne(id);
		model.addAttribute("id", id);
		model.addAttribute("st", st);
		return "sthouse_show.jsp";
	}

	@RequestMapping("/stupdate")
	public String update(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		Sthouse st = new Sthouse(id, name,address);
		try {
			st.update();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/sthouse_list";
	}
}

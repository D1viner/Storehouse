package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SthouseController {
	@RequestMapping("/sthouse_list")
	public String list(@RequestParam(defaultValue = "1") int pageNo, String id, String key, HttpSession session,
			Model model) throws ClassNotFoundException, SQLException {
		User user = (User) session.getAttribute("current_user");

		if (user == null) {
			return "redirect:/user_login.jsp";
		}

		String user_role;
		if (user.getRole().equals("0")) {
			user_role = "Admin";
		} else
			user_role = "User";

		if (id == null)
			id = "";
		if (key == null)
			key = "";

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
		model.addAttribute("totalPage", totalPage);

		model.addAttribute("list", list);

		model.addAttribute("begin", begin);
		model.addAttribute("end", end);

		return "sthouse_list.jsp";
	}

	@RequestMapping("/stadd")
	public String add(Sthouse st) throws ClassNotFoundException, SQLException {
		st.add();
		return "redirect:/sthouse_list";
	}

	@RequestMapping("/stdel")
	public String delete(String id) throws ClassNotFoundException, SQLException {
		Sthouse.del(id);
		return "redirect:/sthouse_list";
	}

	@RequestMapping("/sthouseshow")
	public String show(String id, Model model) throws ClassNotFoundException, SQLException {
		Sthouse st = new Sthouse().getOne(id);
		model.addAttribute("id", id);
		model.addAttribute("st", st);
		return "sthouse_show.jsp";
	}

	@RequestMapping("/stupdate")
	public String update(Sthouse st) throws ClassNotFoundException, SQLException {
		st.update();
		return "redirect:/sthouse_list";
	}
}

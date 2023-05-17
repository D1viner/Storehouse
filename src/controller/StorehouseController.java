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
public class StorehouseController {
	@RequestMapping("/storehouse_list")
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

		String no = request.getParameter("no");
		String key = request.getParameter("key");
		String inventorydatefrom = request.getParameter("inventorydatefrom");
		String inventorydateto = request.getParameter("inventorydateto");

		if (no == null)
			no = "";
		if (key == null)
			key = "";
		if (inventorydatefrom == null)
			inventorydatefrom = "";
		if (inventorydateto == null)
			inventorydateto = "";

		int pageNo = 1;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		int pageSize = 6;
		int totalRow = new Storehouse().getTotalRow();
		int totalPage = totalRow % pageSize == 0 ? totalRow / pageSize : totalRow / pageSize + 1;

		Storehouse st = new Storehouse();
		ArrayList<Map> list = new Storehouse().querybypage(no, key, inventorydatefrom, inventorydateto, pageNo,
				pageSize);

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
		model.addAttribute("no", no);
		model.addAttribute("key", key);
		model.addAttribute("inventorydatefrom", inventorydatefrom);
		model.addAttribute("inventorydateto", inventorydateto);
		model.addAttribute("pageNo", pageNo); 
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", list);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);

		return "storehouse_list.jsp";
	}

	@RequestMapping("/add")
	public String add(Storehouse sh) throws ClassNotFoundException, SQLException {
			sh.add();
		return "redirect:/storehouse_list";
	}

	@RequestMapping("/del")
	public String delete(String no) throws ClassNotFoundException, SQLException{
		Storehouse.del(no);
		return "redirect:/storehouse_list";
	}

	@RequestMapping("/show")
	public String show(String no, Model model) throws ClassNotFoundException, SQLException {
		Storehouse sh = new Storehouse().getOne(no);
		model.addAttribute("storehouse", sh);
		return "storehouse_show.jsp";
	}

	@RequestMapping("/update")
	public String update(Storehouse sh) throws ClassNotFoundException, SQLException {
		sh.update();
		return "redirect:/storehouse_list";
	}

}

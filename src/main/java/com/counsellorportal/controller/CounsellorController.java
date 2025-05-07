package com.counsellorportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.counsellorportal.dto.CounsellorDto;
import com.counsellorportal.dto.DashboardDto;
import com.counsellorportal.entity.Counsellor;
import com.counsellorportal.service.CounsellorService;
import com.counsellorportal.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class CounsellorController {
	@Autowired

	private CounsellorService counsellorService;

	@Autowired
	private EnquiryService enquiryService;

	@GetMapping("/")
	public String loginPage(Model model) {
		CounsellorDto counsellorDto = new CounsellorDto();
		model.addAttribute("counsellor", counsellorDto);
		return "index";
	}

	@PostMapping("/login")
	public String handlLogin(@ModelAttribute("counsellor") CounsellorDto counsellorDto, Model model,
			HttpServletRequest request) {
		CounsellorDto dto = counsellorService.login(counsellorDto.getEmail(), counsellorDto.getPassword());

		if (dto == null) {

			model.addAttribute("emsg", "Invalid crediential");

			return "index";
		}

		else {

			HttpSession session = request.getSession(true);

			session.setAttribute("COUNSELLOR_ID", dto.getCounsellorId());

			return "redirect:dashboard";
		}

	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		session.invalidate();

		return "redirect:/";

	}

	@GetMapping("/dashboard")

	public String buildDashboard(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		Integer counsellorId = (Integer) session.getAttribute("COUNSELLOR_ID");

		DashboardDto dashboardInfo = enquiryService.getDashboardInfo(counsellorId);

		model.addAttribute("dashboardInfo", dashboardInfo);

		return "dashboardReportView";

	}

	@GetMapping("/register")
	public String registerPage(Model model) {

		CounsellorDto counsellorDto = new CounsellorDto();

		model.addAttribute("counsellor", counsellorDto);

		return "registerView";

	}

	@PostMapping("/register")
	public String handleRegistration(@ModelAttribute("counsellor") CounsellorDto counsellorDto, Model model) {

		boolean status = counsellorService.isEmailUnique(counsellorDto.getEmail());

		if (status) {
			boolean register = counsellorService.register(counsellorDto);

			if (register) {

				model.addAttribute("smsg", "Registration Sucessfull");

			} else {

				model.addAttribute("emsg", "Registration Failed");

			}

		}

		else {

			model.addAttribute("emsg", "Duplicate email found");

		}

		return "registerView";

	}

}

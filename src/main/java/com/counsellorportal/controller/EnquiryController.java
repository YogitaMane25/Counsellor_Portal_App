package com.counsellorportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.counsellorportal.dto.EnquiryDto;
import com.counsellorportal.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	@Autowired

	private EnquiryService enquiryService;

	@GetMapping("/enquiry")
	public String addEnquiry(Model model) {

		EnquiryDto enquiryDto = new EnquiryDto();

		model.addAttribute("enq", enquiryDto);

		return "addEnquiry";

	}

	@PostMapping("/enquiry")
	public String handleAddEnquiry(@ModelAttribute("enq") EnquiryDto enq, HttpServletRequest request, Model model)

	{

		HttpSession session = request.getSession(false);

		Integer cid = (Integer) session.getAttribute("COUNSELLOR_ID");

		boolean upsertEnquiry = enquiryService.upsertEnquiry(enq, cid);

		if (upsertEnquiry) {
			model.addAttribute("smsg", "Enquiry Added");
		} else {

			model.addAttribute("emsg", "Failed to Add Enquiry");

		}

		return "addEnquiry";

	}
}

package com.counsellorportal.service;

import java.util.List;

import com.counsellorportal.dto.DashboardDto;
import com.counsellorportal.dto.EnquiryDto;
import com.counsellorportal.entity.Enquiry;

public interface EnquiryService {

	public DashboardDto getDashboardInfo(Integer counsellorId);

	public boolean upsertEnquiry(EnquiryDto enquiryDto, Integer counsellorId);

	public List<EnquiryDto> getEnquiries(Integer counsellorId);

	public List<EnquiryDto> filterEnqs(EnquiryDto filterDto, Integer counsellorId);

	public EnquiryDto getEnquiry(Integer enqId);

}

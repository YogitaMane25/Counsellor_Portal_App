package com.counsellorportal.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.counsellorportal.dto.DashboardDto;
import com.counsellorportal.dto.EnquiryDto;
import com.counsellorportal.entity.Counsellor;
import com.counsellorportal.entity.Enquiry;
import com.counsellorportal.repo.CounsellorRepo;
import com.counsellorportal.repo.EnquiryRepo;
import com.counsellorportal.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired

	private EnquiryRepo enquiryRepo;

	@Autowired

	private CounsellorRepo counsellorRepo;

	@Override
	public DashboardDto getDashboardInfo(Integer counsellorId) {

		List<Enquiry> enqList = enquiryRepo.findByCounsellorCounsellorId(counsellorId);

		int total = enqList.size();

		int open = enqList.stream().filter(e -> e.getEnqStatus().equals("Open")).collect(Collectors.toList()).size();

		int enrolled = enqList.stream().filter(e -> e.getEnqStatus().equals("Enrolled")).collect(Collectors.toList())
				.size();
		int lost = enqList.stream().filter(e -> e.getEnqStatus().equals("Lost")).collect(Collectors.toList()).size();

		return DashboardDto.builder().totalEnquiry(total).openEnquiry(open).enrolledEnquiry(enrolled).lostEnquiry(lost)
				.build();
	}

	@Override
	public boolean upsertEnquiry(EnquiryDto enqDto, Integer counsellorId) {

		Enquiry entity = new Enquiry();

		BeanUtils.copyProperties(enqDto, entity);

		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();

		entity.setCounsellor(counsellor);

		Enquiry saveEntity = enquiryRepo.save(entity);

		return saveEntity.getEnquiryId() != null;
	}

	@Override

	public List<EnquiryDto> getEnquiries(Integer counsellorId) {

		List<EnquiryDto> dtoList = new ArrayList<>();

		List<Enquiry> enqList = enquiryRepo.findByCounsellorCounsellorId(counsellorId);

		enqList.forEach(e -> {
			EnquiryDto dto = new EnquiryDto();

			BeanUtils.copyProperties(e, dto);
			dtoList.add(dto);

		});

		return dtoList;
	}

	@Override
	public List<EnquiryDto> filterEnqs(EnquiryDto filterDto, Integer counsellorId) {

		Enquiry entity = new Enquiry();

		if (filterDto.getClassMode() != null && !filterDto.getClassMode().equals("")) {
			entity.setClassMode(filterDto.getClassMode());
		}

		if (filterDto.getCourseName() != null && !filterDto.getCourseName().equals("")) {
			entity.setCourseName(filterDto.getCourseName());
		}

		if (filterDto.getEnqStatus() != null && !filterDto.getEnqStatus().equals("")) {
			entity.setEnqStatus(filterDto.getEnqStatus());
		}

		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
		entity.setCounsellor(counsellor);

		List<Enquiry> enqList = enquiryRepo.findAll(Example.of(entity));

		List<EnquiryDto> dtoList = new ArrayList<>();

		enqList.forEach(e -> {
			EnquiryDto dto = new EnquiryDto();

			BeanUtils.copyProperties(e, dto);
			dtoList.add(dto);

		});

		return dtoList;
	}

	@Override
	public EnquiryDto getEnquiry(Integer enqId) {

		Enquiry entity = enquiryRepo.findById(enqId).orElseThrow();

		EnquiryDto dto = new EnquiryDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

}

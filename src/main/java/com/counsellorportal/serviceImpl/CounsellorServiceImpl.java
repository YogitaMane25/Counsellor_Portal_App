package com.counsellorportal.serviceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.counsellorportal.dto.CounsellorDto;
import com.counsellorportal.entity.Counsellor;
import com.counsellorportal.repo.CounsellorRepo;
import com.counsellorportal.service.CounsellorService;

@Service
public class CounsellorServiceImpl implements CounsellorService {
	@Autowired

	private CounsellorRepo counsellorRepo;

	@Override
	public Boolean register(CounsellorDto counsellorDto) {
		Counsellor entity = new Counsellor();

		BeanUtils.copyProperties(counsellorDto, entity);

		Counsellor savedEntity = counsellorRepo.save(entity);

		return savedEntity.getCounsellorId() != null; // registration successfull

	}

	@Override
	public CounsellorDto login(String email, String password) {

		Counsellor entity = counsellorRepo.findByEmailAndPassword(email, password);

		if (entity != null) {

			CounsellorDto dto = new CounsellorDto();

			BeanUtils.copyProperties(entity, dto);

			return dto;

		}

		return null;
	}

	@Override
	public Boolean isEmailUnique(String email) {

		Optional<Counsellor> byEmail = counsellorRepo.findByEmail(email);

		if (byEmail.isPresent()) {
			return false;
		}

		return true;
	}

}

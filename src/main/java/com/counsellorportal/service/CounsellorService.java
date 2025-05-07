package com.counsellorportal.service;

import com.counsellorportal.dto.CounsellorDto;

public interface CounsellorService {

	public Boolean register(CounsellorDto counsellorDto);

	CounsellorDto login(String email, String password);

	Boolean isEmailUnique(String email);

}

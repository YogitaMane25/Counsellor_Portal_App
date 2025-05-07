package com.counsellorportal.dto;

import lombok.Data;

@Data
public class EnquiryDto {
	private Integer enquiryId;
	private String studentName;
	private Long phoneNo;
	private String courseName;
	private String classMode;
	private String enqStatus;
	private String action;

}

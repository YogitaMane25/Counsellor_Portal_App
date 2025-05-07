package com.counsellorportal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardDto {

	private Integer totalEnquiry;
	private Integer openEnquiry;
	private Integer enrolledEnquiry;
	private Integer lostEnquiry;

}

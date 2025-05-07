package com.counsellorportal.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

@Getter
@Setter
@Entity
@Table(name = "Enquiries")
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer enquiryId;
	private String studentName;
	private Long phoneNo;
	private String courseName;
	private String classMode;
	private String enqStatus;

	@CreationTimestamp
	private LocalDate createdAt;

	@UpdateTimestamp
	private LocalDate updatedAt;

	@ManyToOne
	@JoinColumn(name = "counsellor_id")
	private Counsellor counsellor;

}

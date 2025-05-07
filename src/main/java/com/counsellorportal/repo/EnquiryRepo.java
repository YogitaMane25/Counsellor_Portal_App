package com.counsellorportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.counsellorportal.entity.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

	List<Enquiry> findByCounsellorCounsellorId(Integer counsellorId);

}

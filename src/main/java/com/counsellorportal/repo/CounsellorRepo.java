package com.counsellorportal.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.counsellorportal.entity.Counsellor;

@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {

	public Counsellor findByEmailAndPassword(String email, String password);

	public Optional<Counsellor> findByEmail(String email);

}

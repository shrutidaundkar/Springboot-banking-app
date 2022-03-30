package com.xorbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.model.Document;

public interface DocRepository extends JpaRepository<Document, Integer>{

	Object findByUserUserId(Integer fileId);

}

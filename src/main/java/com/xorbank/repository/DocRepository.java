package com.xorbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.models.Document;

public interface DocRepository extends JpaRepository<Document, Integer>{

}

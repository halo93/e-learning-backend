package com.elearningbackend.repository;

import com.elearningbackend.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubcategoryRepository extends JpaRepository<Subcategory, String>{
}

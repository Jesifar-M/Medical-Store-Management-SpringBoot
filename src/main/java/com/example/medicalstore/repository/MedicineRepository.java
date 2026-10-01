package com.example.medicalstore.repository;

import com.example.medicalstore.entity.Medicine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository
        extends JpaRepository<Medicine, Long> {

    long countByUserId(Long userId);

    Page<Medicine> findByUserId(
            Long userId,
            Pageable pageable
    );

    List<Medicine> findByUserIdAndNameContainingIgnoreCase(
            Long userId,
            String name
    );
}
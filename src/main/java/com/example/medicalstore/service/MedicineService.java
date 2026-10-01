package com.example.medicalstore.service;

import com.example.medicalstore.entity.Medicine;
import com.example.medicalstore.entity.User;
import com.example.medicalstore.repository.MedicineRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public boolean addMedicine(
            Medicine medicine,
            User user) {

        long count =
                medicineRepository.countByUserId(
                        user.getId()
                );

        if (count >= 5) {
            return false;
        }

        medicine.setUser(user);

        medicineRepository.save(medicine);

        return true;
    }

    public Page<Medicine> getMedicines(
            User user,
            Pageable pageable) {

        return medicineRepository.findByUserId(
                user.getId(),
                pageable
        );
    }

    public List<Medicine> searchMedicine(
            User user,
            String name) {

        return medicineRepository
                .findByUserIdAndNameContainingIgnoreCase(
                        user.getId(),
                        name
                );
    }

    public Medicine getMedicine(Long id) {

        return medicineRepository
                .findById(id)
                .orElse(null);
    }

    public void deleteMedicine(Long id) {

        medicineRepository.deleteById(id);
    }

    public Medicine saveMedicine(
            Medicine medicine) {

        return medicineRepository.save(medicine);
    }
}
package com.example.medicalstore.controller;

import com.example.medicalstore.entity.Medicine;
import com.example.medicalstore.entity.User;
import com.example.medicalstore.service.MedicineService;

import jakarta.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    // Medicine List + Pagination
    @GetMapping("/medicines")
    public String medicines(
            @RequestParam(defaultValue = "0") int page,
            HttpSession session,
            Model model) {

        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        Pageable pageable =
                PageRequest.of(page, 3);

        Page<Medicine> medicinePage =
                medicineService.getMedicines(
                        user,
                        pageable
                );

        model.addAttribute(
                "medicines",
                medicinePage.getContent()
        );

        model.addAttribute(
                "currentPage",
                page
        );

        model.addAttribute(
                "totalPages",
                medicinePage.getTotalPages()
        );

        model.addAttribute(
                "medicine",
                new Medicine()
        );

        return "medicines";
    }

    // Add Medicine
    @PostMapping("/medicines/add")
    public String addMedicine(
            @ModelAttribute Medicine medicine,
            HttpSession session,
            Model model) {

        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        boolean added =
                medicineService.addMedicine(
                        medicine,
                        user
                );

        if (!added) {

            Pageable pageable =
                    PageRequest.of(0, 3);

            Page<Medicine> medicinePage =
                    medicineService.getMedicines(
                            user,
                            pageable
                    );

            model.addAttribute(
                    "error",
                    "You can add only 5 medicines!"
            );

            model.addAttribute(
                    "medicines",
                    medicinePage.getContent()
            );

            model.addAttribute(
                    "currentPage",
                    0
            );

            model.addAttribute(
                    "totalPages",
                    medicinePage.getTotalPages()
            );

            model.addAttribute(
                    "medicine",
                    new Medicine()
            );

            return "medicines";
        }

        return "redirect:/medicines";
    }

    // Search Medicine
    @GetMapping("/medicines/search")
    public String search(
            @RequestParam String name,
            HttpSession session,
            Model model) {

        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "medicines",
                medicineService.searchMedicine(
                        user,
                        name
                )
        );

        model.addAttribute(
                "medicine",
                new Medicine()
        );

        return "medicines";
    }

    // Edit Medicine
    @GetMapping("/medicines/edit/{id}")
    public String edit(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        Medicine medicine =
                medicineService.getMedicine(id);

        if (medicine == null) {
            return "redirect:/medicines";
        }

        model.addAttribute(
                "medicine",
                medicine
        );

        return "edit";
    }

    // Update Medicine
    @PostMapping("/medicines/update/{id}")
    public String update(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam int stock,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        Medicine existingMedicine =
                medicineService.getMedicine(id);

        if (existingMedicine == null) {
            return "redirect:/medicines";
        }

        existingMedicine.setName(name);
        existingMedicine.setStock(stock);

        medicineService.saveMedicine(
                existingMedicine
        );

        return "redirect:/medicines";
    }

    // Delete Medicine
    @GetMapping("/medicines/delete/{id}")
    public String delete(
            @PathVariable Long id,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        medicineService.deleteMedicine(id);

        return "redirect:/medicines";
    }
}
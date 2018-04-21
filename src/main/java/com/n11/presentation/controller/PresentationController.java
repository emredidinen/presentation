package com.n11.presentation.controller;

import com.n11.presentation.dto.PresentationDTO;
import com.n11.presentation.dto.Schedule;
import com.n11.presentation.enums.Success;
import com.n11.presentation.service.PresentationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Emre on 20.04.2018.
 */

@Controller
public class PresentationController {

    private final PresentationService presentationService;

    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @GetMapping("/defaultPresentation")
    public ResponseEntity<?> addDefaultData() {
        presentationService.addDefaultData();
        return ResponseEntity.ok(Success.DEFAULT_DATA_ADDED.getType());
    }

    @GetMapping("/presentation")
    public String getPresentationList(Model model) {
        model.addAttribute("presentation", new PresentationDTO());
        model.addAttribute("presentationList", presentationService.getAll());
        return "presentationEdit";
    }

    @GetMapping("/presentation/{id}")
    public String getPresentationById(Model model, @PathVariable Long id) {
        PresentationDTO dto = presentationService.findById(id);
        model.addAttribute("presentation", dto);
        model.addAttribute("presentationList", presentationService.getAll());
        return "presentationEdit";
    }

    @GetMapping("/presentation/remove/{id}")
    public String deletePresentationById(Model model, @PathVariable Long id) {
        presentationService.delete(id);
        return "redirect:/presentation";
    }

    @PostMapping("/presentation")
    public String greetingSubmit(@ModelAttribute PresentationDTO dto, Model model) {
        presentationService.save(dto);
        return getPresentationList(model);
    }

    @GetMapping("/schedule")
    public  String getSchedule(Model model) {
        model.addAttribute("schedule", presentationService.getSchedule());
        return "presentationList";
    }

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello World";
    }
}

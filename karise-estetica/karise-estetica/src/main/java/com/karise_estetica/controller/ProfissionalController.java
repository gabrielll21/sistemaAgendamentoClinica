package com.karise_estetica.controller;

import com.karise_estetica.model.Profissional;
import com.karise_estetica.service.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("profissional", new Profissional());
        return "profissional-form";
    }

    @PostMapping("/salvar")
    public String salvarProfissional(@Valid @ModelAttribute("profissional") Profissional profissional,
                                     BindingResult result,
                                     Model model) {
        if (result.hasErrors()) {
            return "profissional-form";
        }
        profissionalService.salvar(profissional);
        return "redirect:/profissionais/listar";
    }

    @GetMapping("/listar")
    public String listarProfissionais(Model model) {
        model.addAttribute("profissionais", profissionalService.listarTodos());
        return "profissional-lista";
    }

    @GetMapping("/deletar/{id}")
    public String deletarProfissional(@PathVariable Long id) {
        profissionalService.deletar(id);
        return "redirect:/profissionais/listar";
    }
}
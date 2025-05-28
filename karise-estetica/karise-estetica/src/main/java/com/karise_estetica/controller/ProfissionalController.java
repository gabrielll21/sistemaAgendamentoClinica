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
    private ProfissionalService service;

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("profissional", new Profissional());
        return "profissional-form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("profissional") Profissional prof,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "profissional-form";
        }
        service.salvar(prof);
        return "redirect:/profissionais/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("profissionais", service.listarTodos());
        return "profissional-lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        service.buscarPorId(id)
                .ifPresent(p -> model.addAttribute("profissional", p));
        return "profissional-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/profissionais/listar";
    }
}

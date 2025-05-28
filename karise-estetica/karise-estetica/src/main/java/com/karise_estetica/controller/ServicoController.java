package com.karise_estetica.controller;

import com.karise_estetica.model.Servico;
import com.karise_estetica.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @GetMapping({"/novo", "/editar/{id}"})
    public String form(@PathVariable(required = false) Long id, Model model) {
        if (id != null) {
            service.buscarPorId(id).ifPresent(s -> model.addAttribute("servico", s));
        } else {
            model.addAttribute("servico", new Servico());
        }
        return "servico-form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("servico") Servico servico,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "servico-form";
        }
        service.salvar(servico);
        return "redirect:/servicos/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("servicos", service.listarTodos());
        return "servico-lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/servicos/listar";
    }
}

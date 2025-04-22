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
    private ServicoService servicoService;

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("servico", new Servico());
        return "servico-form";
    }

    @PostMapping("/salvar")
    public String salvarServico(@Valid @ModelAttribute("servico") Servico servico,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "servico-form";
        }
        servicoService.salvar(servico);
        return "redirect:/servicos/listar";
    }

    @GetMapping("/listar")
    public String listarServicos(Model model) {
        model.addAttribute("servicos", servicoService.listarTodos());
        return "servico-lista";
    }

    @GetMapping("/deletar/{id}")
    public String deletarServico(@PathVariable Long id) {
        servicoService.deletar(id);
        return "redirect:/servicos/listar";
    }
}

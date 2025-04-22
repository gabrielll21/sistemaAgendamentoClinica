package com.karise_estetica.controller;


import com.karise_estetica.model.Agendamento;
import com.karise_estetica.service.AgendamentoService;
import com.karise_estetica.service.ClienteService;
import com.karise_estetica.service.ProfissionalService;
import com.karise_estetica.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/novo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("profissionais", profissionalService.listarTodos());
        model.addAttribute("servicos", servicoService.listarTodos());
        return "agendamento-form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("agendamento") Agendamento agendamento,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteService.listarTodos());
            model.addAttribute("profissionais", profissionalService.listarTodos());
            model.addAttribute("servicos", servicoService.listarTodos());
            return "agendamento-form";
        }
        agendamentoService.salvar(agendamento);
        return "redirect:/agendamentos/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("agendamentos", agendamentoService.listarTodos());
        return "agendamento-lista";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        agendamentoService.deletar(id);
        return "redirect:/agendamentos/listar";
    }
}

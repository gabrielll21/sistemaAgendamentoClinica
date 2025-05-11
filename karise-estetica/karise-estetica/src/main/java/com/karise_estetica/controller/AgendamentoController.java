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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;


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

    @GetMapping("/api/agendamentos")
    @ResponseBody
    public List<Map<String, String>> obterAgendamentos() {
        return agendamentoService.listarTodos().stream().map(agendamento -> {
            LocalDateTime dateTime = LocalDateTime.of(agendamento.getData(), agendamento.getHora());
            Map<String, String> evento = new HashMap<>();
            evento.put("title", agendamento.getServico().getNome() + " - " + agendamento.getCliente().getNome());
            evento.put("start", dateTime.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            return evento;
        }).collect(Collectors.toList());
    }



}

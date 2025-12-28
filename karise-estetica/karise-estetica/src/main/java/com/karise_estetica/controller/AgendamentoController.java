package com.karise_estetica.controller;

import com.karise_estetica.model.Agendamento;
import com.karise_estetica.service.AgendamentoService;
import com.karise_estetica.service.ClienteService;
import com.karise_estetica.service.ProfissionalService;
import com.karise_estetica.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping({"/novo", "/editar/{id}"})
    public String form(@PathVariable(required = false) Long id, Model model) {
        if (id != null) {
            agendamentoService.buscarPorId(id)
                    .ifPresent(a -> model.addAttribute("agendamento", a));
        } else {
            model.addAttribute("agendamento", new Agendamento());
        }
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

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        agendamentoService.deletar(id);
        return "redirect:/agendamentos/listar";
    }

    @GetMapping(value = "/api/agendamentos", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Map<String, String>>> obterAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.listarTodos();
        List<Map<String, String>> eventos = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            // Verifica se data e hora não são nulas
            if (agendamento.getData() == null || agendamento.getHora() == null) {
                continue;
            }
            
            Map<String, String> evento = new HashMap<>();
            
            // Combina data e hora para criar o datetime
            LocalDateTime dateTime = LocalDateTime.of(agendamento.getData(), agendamento.getHora());
            String start = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            
            // Cria o título com informações do agendamento
            StringBuilder titulo = new StringBuilder();
            
            if (agendamento.getServico() != null && agendamento.getServico().getNome() != null) {
                titulo.append(agendamento.getServico().getNome());
            } else {
                titulo.append("Serviço");
            }
            
            titulo.append(" - ");
            
            if (agendamento.getCliente() != null && agendamento.getCliente().getNome() != null) {
                titulo.append(agendamento.getCliente().getNome());
            } else {
                titulo.append("Cliente");
            }
            
            if (agendamento.getProfissional() != null && agendamento.getProfissional().getNome() != null) {
                titulo.append(" (").append(agendamento.getProfissional().getNome()).append(")");
            }
            
            evento.put("title", titulo.toString());
            evento.put("start", start);
            
            // Adiciona cor baseada no status
            if (agendamento.getStatus() != null) {
                switch (agendamento.getStatus().name()) {
                    case "AGENDADO":
                        evento.put("color", "#3b82f6"); // Azul
                        break;
                    case "CONCLUIDO":
                        evento.put("color", "#10b981"); // Verde
                        break;
                    case "CANCELADO":
                        evento.put("color", "#ef4444"); // Vermelho
                        break;
                    default:
                        evento.put("color", "#f4a734"); // Laranja padrão
                }
            } else {
                evento.put("color", "#f4a734");
            }
            
            eventos.add(evento);
        }

        return ResponseEntity.ok(eventos);
    }
}

package com.karise_estetica.controller;


import com.karise_estetica.model.Cliente;
import com.karise_estetica.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        clienteService.buscarPorId(id)
                .ifPresentOrElse(
                        cliente -> model.addAttribute("cliente", cliente),
                        () -> model.addAttribute("error", "Cliente não encontrado")
                );
        return "cliente-form";
    }

    @PostMapping("/salvar")
    public String salvarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "cliente-form";
        }
        clienteService.salvar(cliente);
        return "redirect:/clientes/listar";
    }

    @GetMapping("/listar")
    public String listarClientes(@RequestParam(value = "q", required = false) String busca, Model model) {
        List<Cliente> clientes;
        if (busca != null && !busca.trim().isEmpty()) {
            clientes = clienteService.buscarPorNome(busca);
        } else {
            clientes = clienteService.listarTodos();
        }
        model.addAttribute("clientes", clientes);
        model.addAttribute("q", busca); // mantém o termo no input
        return "cliente-lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCliente(@PathVariable Long id) {
        clienteService.deletar(id);
        return "redirect:/clientes/listar";
    }
}
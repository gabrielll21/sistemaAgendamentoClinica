package com.karise_estetica.controller;


import org.springframework.ui.Model;
//import com.karise_estetica.dto.UsuarioDTO;
//import com.karise_estetica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//@Controller
//public class UsuarioController {
//
//    private final UsuarioService usuarioService;
//
//    @Autowired
//    public UsuarioController(UsuarioService usuarioService) {
//        this.usuarioService = usuarioService;
//    }
//
//    @GetMapping("/cadastro")
//    public String exibirFormularioCadastro(Model model) {
//        model.addAttribute("usuario", new UsuarioDTO());
//        return "cadastro";
//    }
//
//    @PostMapping("/cadastro")
//    public String cadastrarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDto, Model model) {
//        try {
//            usuarioService.register(usuarioDto);
//        } catch (Exception e) {
//            model.addAttribute("error", e.getMessage());
//            return "cadastro";
//        }
//        return "redirect:/login";
//    }
//}
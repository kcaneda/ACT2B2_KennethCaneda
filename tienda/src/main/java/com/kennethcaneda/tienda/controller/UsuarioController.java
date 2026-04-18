package com.kennethcaneda.tienda.controller;

import com.kennethcaneda.tienda.entity.Usuario;
import com.kennethcaneda.tienda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuario", usuarioService.listar());
        model.addAttribute("nuevoUsuario", new Usuario());
        return "usuarios";
    }

    @PostMapping("/crear")
    public String crear(@Valid @ModelAttribute("nuevoUsuario") Usuario usuario, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("errorAlCrear", bindingResult.getAllErrors());
            return "usuarios";
        }
        usuarioService.crear(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model){
        model.addAttribute("usuario", usuarioService.listar());
        model.addAttribute("nuevoUsuario", new Usuario());
        model.addAttribute("usuarioEditar", usuarioService.buscarPorId(id));
        return "usuarios";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute @Valid Usuario usuario){
        usuarioService.actualizar(id, usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("eliminar/{id}")
    public String formularioEliminar(@PathVariable Integer id, Model model){
        model.addAttribute("usuario", usuarioService.listar());
        model.addAttribute("nuevoUsuario", new Usuario());
        model.addAttribute("usuarioEliminar", usuarioService.buscarPorId(id));
        return "usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }

}


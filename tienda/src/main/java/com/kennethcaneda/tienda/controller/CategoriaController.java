package com.kennethcaneda.tienda.controller;

import com.kennethcaneda.tienda.entity.Categoria;
import com.kennethcaneda.tienda.entity.Usuario;
import com.kennethcaneda.tienda.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("categoria", categoriaService.listar());
        model.addAttribute("nuevaCategoria", new Categoria());
        return "/categorias";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute("nuevaCategoria") @Valid Categoria categoria){
        categoriaService.crear(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model){
        model.addAttribute("categoria", categoriaService.listar());
        model.addAttribute("nuevaCategoria", new Categoria());
        model.addAttribute("categoriaAEditar", categoriaService.buscarPorId(id));
        return "categorias";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute("categoriaAEditar") @Valid Categoria categoria){
        categoriaService.actualizar(id, categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String formularioEliminar(@PathVariable Integer id, Model model){
        model.addAttribute("categoria", categoriaService.listar());
        model.addAttribute("nuevaCategoria", new Categoria());
        model.addAttribute("categoriaAEliminar", categoriaService.buscarPorId(id));
        return "categorias";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        categoriaService.eliminar(id);
        return "redirect:/categorias";
    }

}

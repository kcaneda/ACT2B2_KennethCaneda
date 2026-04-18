package com.kennethcaneda.tienda.controller;

import com.kennethcaneda.tienda.entity.Producto;
import com.kennethcaneda.tienda.service.ProductoService;
import com.kennethcaneda.tienda.service.ProductoServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("nuevoProducto", new Producto());
        model.addAttribute("producto", productoService.listar());
        return "productos";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute("nuevoProducto") @Valid Producto producto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("producto", productoService.listar());
            model.addAttribute("mostrarModalCrear", true);
            return "productos";
        }
        productoService.crear(producto);
        return "redirect:/productos";
    }

    @GetMapping("editar/{id}")
    public String formularioEditar(Model model, @PathVariable Integer id){
        model.addAttribute("producto", productoService.listar());
        model.addAttribute("nuevoProducto", new Producto());
        model.addAttribute("productoAEditar", productoService.buscarPorId(id));
        return "/productos";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@ModelAttribute("productoAEditar") @Valid Producto producto, @PathVariable Integer id, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            producto.setIdProducto(id);
            model.addAttribute("productoAEditar", producto);
            model.addAttribute("producto", productoService.listar());
            model.addAttribute("nuevoProducto", new Producto());
            return "productos";
        }
        productoService.actualizar(id, producto);
        return "redirect:/productos";
    }

    @GetMapping("eliminar/{id}")
    public String formularioEliminar(Model model, @PathVariable Integer id){
        model.addAttribute("producto", productoService.listar());
        model.addAttribute("nuevoProducto", new Producto());
        model.addAttribute("productoAEliminar", productoService.buscarPorId(id));
        return "/productos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}

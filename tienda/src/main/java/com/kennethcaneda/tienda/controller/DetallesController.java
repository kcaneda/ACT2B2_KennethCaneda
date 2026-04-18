package com.kennethcaneda.tienda.controller;

import com.kennethcaneda.tienda.entity.DetallePedido;
import com.kennethcaneda.tienda.entity.Producto;
import com.kennethcaneda.tienda.service.DetallesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/detalles")
public class DetallesController {
    @Autowired
    private final DetallesService detallesService;

    public DetallesController(DetallesService detallesService) {
        this.detallesService = detallesService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("detalle", detallesService.listar());
        model.addAttribute("nuevoDetalle", new DetallePedido());
        return "detalles";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute("nuevoDetalle") @Valid DetallePedido detallePedido, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("detalle", detallesService.listar());
            model.addAttribute("mostrarModalCrear", true);
            return "detalles";
        }
        detallesService.crear(detallePedido);
        return "redirect:/detalles";
    }

    @GetMapping("editar/{id}")
    public String formularioEditar(Model model, @PathVariable Integer id){
        model.addAttribute("detalle", detallesService.listar());
        model.addAttribute("nuevoDetalle", new DetallePedido());
        model.addAttribute("detalleAEditar", detallesService.buscarPorId(id));
        return "detalles";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@ModelAttribute("detalleAEditar") @Valid DetallePedido detallePedido, @PathVariable Integer id, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("detalle", detallesService.listar());
            model.addAttribute("nuevoDetalle", new DetallePedido());
            return "detalles";
        }
        detallesService.actualizar(id, detallePedido);
        return "redirect:/detalles";
    }

    @GetMapping("eliminar/{id}")
    public String formularioEliminar(Model model, @PathVariable Integer id){
        model.addAttribute("detalle", detallesService.listar());
        model.addAttribute("nuevoDetalle", new DetallePedido());
        model.addAttribute("detalleAEliminar", detallesService.buscarPorId(id));
        return "detalles";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        detallesService.eliminar(id);
        return "redirect:/detalles";
    }
}

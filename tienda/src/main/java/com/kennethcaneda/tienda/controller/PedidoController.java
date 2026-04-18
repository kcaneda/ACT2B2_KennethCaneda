package com.kennethcaneda.tienda.controller;

import com.kennethcaneda.tienda.entity.Categoria;
import com.kennethcaneda.tienda.entity.Pedido;
import com.kennethcaneda.tienda.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("pedido", pedidoService.listar());
        model.addAttribute("nuevoPedido", new Pedido());
        return "pedidos";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute("nuevoPedido") @Valid Pedido pedido){
        pedidoService.crear(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model){
        model.addAttribute("pedido", pedidoService.listar());
        model.addAttribute("nuevoPedido", new Pedido());
        model.addAttribute("pedidoAEditar", pedidoService.buscarPorId(id));
        return "pedidos";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute("pedidoAEditar") @Valid Pedido pedido){
        pedidoService.actualizar(id, pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/eliminar/{id}")
    public String formularioEliminar(@PathVariable Integer id, Model model){
        model.addAttribute("pedido", pedidoService.listar());
        model.addAttribute("nuevoPedido", new Pedido());
        model.addAttribute("pedidoAEliminar", pedidoService.buscarPorId(id));
        return "pedidos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        pedidoService.eliminar(id);
        return "redirect:/pedidos";
    }
}

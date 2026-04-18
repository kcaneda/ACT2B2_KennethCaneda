package com.kennethcaneda.tienda.service;

import com.kennethcaneda.tienda.entity.Pedido;
import com.kennethcaneda.tienda.exception.ResourceNotFoundException;
import com.kennethcaneda.tienda.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido crear(Pedido pedido) {
        pedido.setIdPedido(null);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido actualizar(Integer id, Pedido pedido) {
        Pedido nuevoPedido = buscarPorId(id);
        nuevoPedido.setDescripcionPedido(pedido.getDescripcionPedido());
        nuevoPedido.setFechaPedido(pedido.getFechaPedido());
        nuevoPedido.setTotalPedido(pedido.getTotalPedido());
        nuevoPedido.setIdUsuario(pedido.getIdUsuario());
        return pedidoRepository.save(nuevoPedido);
    }

    @Override
    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pedido no encontrado con ID: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        if(!pedidoRepository.existsById(id)){
            throw new ResourceNotFoundException("Pedido no encontrado por ID " + id );
        }
        pedidoRepository.deleteById(id);

    }
}

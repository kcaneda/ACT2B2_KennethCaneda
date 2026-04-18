package com.kennethcaneda.tienda.service;

import com.kennethcaneda.tienda.entity.Producto;
import com.kennethcaneda.tienda.exception.ResourceNotFoundException;
import com.kennethcaneda.tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto crear(Producto producto) {
        producto.setIdProducto(null);
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Integer id, Producto producto) {
        Producto productoExistente = buscarPorId(id);
        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setPrecioProducto(producto.getPrecioProducto());
        productoExistente.setStockProducto(producto.getStockProducto());
        return productoRepository.save(productoExistente);
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        if(!productoRepository.existsById(id)){
            throw new ResourceNotFoundException("Producto no encontrado con Id" + id);
        }
        productoRepository.deleteById(id);
    }
}

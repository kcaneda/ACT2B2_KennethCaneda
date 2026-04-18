package com.kennethcaneda.tienda.service;

import com.kennethcaneda.tienda.entity.Categoria;
import com.kennethcaneda.tienda.entity.Usuario;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listar();
    Categoria crear(Categoria categoria);
    Categoria actualizar(Integer id, Categoria categoria);
    Categoria buscarPorId(Integer id);
    void eliminar(Integer id);
}

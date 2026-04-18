package com.kennethcaneda.tienda.repository;

import com.kennethcaneda.tienda.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}

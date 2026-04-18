package com.kennethcaneda.tienda.repository;

import com.kennethcaneda.tienda.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallesRepository extends JpaRepository<DetallePedido, Integer> {
}

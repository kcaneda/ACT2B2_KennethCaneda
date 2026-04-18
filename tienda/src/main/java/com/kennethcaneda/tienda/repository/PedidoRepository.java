package com.kennethcaneda.tienda.repository;

import com.kennethcaneda.tienda.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
}

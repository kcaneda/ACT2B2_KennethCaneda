package com.kennethcaneda.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @NotBlank(message = "La descripción no puede ir vacía")
    @Size(min = 3, max = 150, message = "Ingrese una descripción válida, de 3 a 150 caracteres")
    @Column(name = "descripcion_pedido")
    private String descripcionPedido;

    @NotNull(message = "La fecha no puede estar vacía")
    @PastOrPresent
    @Column(name = "fecha_pedido")
    private Date fechaPedido;

    @NotNull(message = "El total del pedido no puede ir vacío")
    @Min(value = 0, message = "Ingrese un número válido positivo")
    @Column(name = "total_pedido")
    private BigDecimal totalPedido;

    @NotNull(message = "El ID del usuario no puede ir vacío")
    @Min(value = 1, message = "Ingrese un ID válido positivo")
    @Column(name = "id_usuario")
    private Integer idUsuario;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getDescripcionPedido() {
        return descripcionPedido;
    }

    public void setDescripcionPedido(String descripcionPedido) {
        this.descripcionPedido = descripcionPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}

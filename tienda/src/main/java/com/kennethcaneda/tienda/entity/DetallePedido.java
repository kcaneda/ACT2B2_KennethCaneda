package com.kennethcaneda.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @NotNull(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "La cantidad debe ser positiva y mayor a 1")
    @Column(name = "cantidad")
    private Integer cantidadPedido;

    @NotNull(message = "El precio unitario no puede estar vacío")
    @Min(value = 0, message = "El precio unitario debe ser positivo")
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @NotNull(message = "El ID del pedido no puede estar vacío")
    @Min(value = 1, message = "El ID del pedido debe ser un número válido positivo")
    @Column(name = "id_pedido")
    private Integer idPedido;

    @NotNull(message = "El ID del producto no puede ir vacío")
    @Min(value = 1, message = "El ID del producto debe ser un número válido positivo")
    @Column(name = "id_producto")
    private Integer idProducto;

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getCantidadPedido() {
        return cantidadPedido;
    }

    public void setCantidadPedido(Integer cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

}

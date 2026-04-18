package com.kennethcaneda.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 80, message = "El nombre debe tener entre 3 a 80 caracteres")
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @NotNull(message = "El precio no puede estar vacío")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(name = "precio_producto")
    private BigDecimal precioProducto;

    @NotNull(message = "El stock del producto no puede estar vacío")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name = "stock")
    private Integer stockProducto;

    @NotNull(message = "El ID de la categoría no puede ir vacío")
    @Min(value = 0, message = "El ID debe ser positivo")
    @Column(name = "id_categoria")
    private Integer idCategoria;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Integer getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(Integer stockProducto) {
        this.stockProducto = stockProducto;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}

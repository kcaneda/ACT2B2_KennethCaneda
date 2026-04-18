package com.kennethcaneda.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    @Column(name = "nombre_categoria")
    @Size(min = 3, max = 60, message = "El nombre de la categoría debe tener entre 3 a 60 caracteres")
    private String nombreCategoria;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 3, max = 150, message = "La descripcion de la categoría debe tener entre 3 a 150 caracteres")
    @Column(name = "descripcion_categoria")
    private String descripcionCategoria;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }
}

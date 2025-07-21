package com.ninfagarden.model;

import jakarta.persistence.Entity;

@Entity
public class Planta extends Producto {

    private String climaRecomendado;

    public Planta() {}

    public Planta(String nombre, String descripcion, double precio, int stock, String imagenUrl, String climaRecomendado) {
        super(nombre, descripcion, precio, stock, imagenUrl);
        this.climaRecomendado = climaRecomendado;
    }

    public String getClimaRecomendado() { return climaRecomendado; }
    public void setClimaRecomendado(String climaRecomendado) { this.climaRecomendado = climaRecomendado; }
}


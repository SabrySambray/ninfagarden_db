package com.ninfagarden.model;

import jakarta.persistence.Entity;

@Entity
public class Herramienta extends Producto {

    private String material;

    public Herramienta() {}

    public Herramienta(String nombre, String descripcion, double precio, int stock, String imagenUrl, String material) {
        super(nombre, descripcion, precio, stock, imagenUrl);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
}

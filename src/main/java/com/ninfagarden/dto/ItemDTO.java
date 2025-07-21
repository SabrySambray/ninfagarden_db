package com.ninfagarden.dto;

public class ItemDTO {
    private Long productoId;
    private String nombreProducto;
    private int cantidad;

    public ItemDTO() {}

    public ItemDTO(Long productoId, String nombreProducto, int cantidad) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
    }

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}


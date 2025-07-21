package com.ninfagarden.service;

import com.ninfagarden.excepciones.RecursoNoEncontradoException;
import com.ninfagarden.model.Producto;
import com.ninfagarden.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    public Producto getById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con id: " + id));
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto update(Long id, Producto productoActualizado) {
        Producto existente = getById(id);
        existente.setNombre(productoActualizado.getNombre());
        existente.setDescripcion(productoActualizado.getDescripcion());
        existente.setPrecio(productoActualizado.getPrecio());
        existente.setStock(productoActualizado.getStock());
        existente.setImagenUrl(productoActualizado.getImagenUrl());
        return productoRepository.save(existente);
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }
}

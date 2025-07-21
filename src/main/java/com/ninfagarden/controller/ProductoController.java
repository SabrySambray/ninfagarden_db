package com.ninfagarden.controller;

import com.ninfagarden.model.Herramienta;
import com.ninfagarden.model.Planta;
import com.ninfagarden.model.Producto;
import com.ninfagarden.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> getAll() {
        return productoService.getAll();
    }

    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id) {
        return productoService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        String nombre = (String) payload.get("nombre");
        String descripcion = (String) payload.get("descripcion");
        double precio = Double.parseDouble(payload.get("precio").toString());
        int stock = Integer.parseInt(payload.get("stock").toString());
        String imagenUrl = (String) payload.get("imagenUrl");
        String tipo = (String) payload.get("tipo");

        if ("Planta".equalsIgnoreCase(tipo)) {
            String clima = (String) payload.get("climaRecomendado");
            Planta planta = new Planta(nombre, descripcion, precio, stock, imagenUrl, clima);
            Producto saved = productoService.save(planta);
            return ResponseEntity.ok(saved);
        } else if ("Herramienta".equalsIgnoreCase(tipo)) {
            String material = (String) payload.get("material");
            Herramienta herramienta = new Herramienta(nombre, descripcion, precio, stock, imagenUrl, material);
            Producto saved = productoService.save(herramienta);
            return ResponseEntity.ok(saved);
        } else {
            return ResponseEntity.badRequest().body("Tipo inválido");
        }
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.update(id, producto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productoService.delete(id);
    }
}


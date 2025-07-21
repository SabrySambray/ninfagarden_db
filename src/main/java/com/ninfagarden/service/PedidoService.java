package com.ninfagarden.service;

import com.ninfagarden.dto.ItemDTO;
import com.ninfagarden.dto.PedidoDTO;
import com.ninfagarden.excepciones.RecursoNoEncontradoException;
import com.ninfagarden.excepciones.StockInsuficienteException;
import com.ninfagarden.model.LineaPedido;
import com.ninfagarden.model.Pedido;
import com.ninfagarden.model.Producto;
import com.ninfagarden.repository.PedidoRepository;
import com.ninfagarden.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    public Pedido crearPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(pedidoDTO.getUsuarioId());
        pedido.setEstado("pendiente");
        pedido.setItems(new ArrayList<>()); 

        double total = 0.0;

        for (ItemDTO item : pedidoDTO.getItems()) {
            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new RecursoNoEncontradoException(
                            "Producto no encontrado con id: " + item.getProductoId()));

            if (producto.getStock() < item.getCantidad()) {
                throw new StockInsuficienteException(
                        "Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);

            LineaPedido linea = new LineaPedido();
            linea.setProductoId(item.getProductoId());
            linea.setCantidad(item.getCantidad());
            pedido.getItems().add(linea);

            total += item.getCantidad() * producto.getPrecio();
        }

        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getPedidosByUsuarioId(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public PedidoDTO mapToDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setUsuarioId(pedido.getUsuarioId());
        dto.setTotal(pedido.getTotal());
        dto.setEstado(pedido.getEstado());

        List<ItemDTO> items = pedido.getItems().stream().map(linea -> {
            Producto prod = productoRepository.findById(linea.getProductoId()).orElse(null);
            String nombre = (prod != null) ? prod.getNombre() : "Producto eliminado";
            return new ItemDTO(linea.getProductoId(), nombre, linea.getCantidad());
        }).collect(Collectors.toList());

        dto.setItems(items);

        return dto;
    }

    @Transactional
    public void borrarPedidosByUsuarioId(Long usuarioId) {
        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(usuarioId);
        pedidoRepository.deleteAll(pedidos);
    }
}




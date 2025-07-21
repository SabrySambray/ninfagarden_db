package com.ninfagarden.controller;

import com.ninfagarden.dto.PedidoDTO;
import com.ninfagarden.model.Pedido;
import com.ninfagarden.repository.PedidoRepository;
import com.ninfagarden.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoService pedidoService, PedidoRepository pedidoRepository) {
        this.pedidoService = pedidoService;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedidoDTO);
        PedidoDTO respuesta = pedidoService.mapToDTO(nuevoPedido);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoDTO>> getPedidosByUsuarioId(@PathVariable Long usuarioId) {
        List<Pedido> pedidos = pedidoService.getPedidosByUsuarioId(usuarioId);
        List<PedidoDTO> dtos = pedidos.stream()
                .map(pedidoService::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> borrarPedidosPorUsuario(@PathVariable Long usuarioId) {
        pedidoService.borrarPedidosByUsuarioId(usuarioId);
        return ResponseEntity.ok().build();
    }
}




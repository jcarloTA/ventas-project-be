package com.jcarlo.dev.ventas.service;

import com.jcarlo.dev.ventas.model.Pedido;
import com.jcarlo.dev.ventas.model.PedidoProducto;
import com.jcarlo.dev.ventas.repository.PedidoProductoRepository;
import com.jcarlo.dev.ventas.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    PedidoProductoRepository pedidoProductoRepository;

    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Pedido createPedido(@RequestBody Pedido pedido) {
        List<PedidoProducto> detalles = pedido.getDetalles();
        pedido.setDetalles(null);

        Pedido tempPedido = pedidoRepository.save(pedido);
        if(detalles != null) {
            for(PedidoProducto t: detalles) {
                t.setIdPedido(tempPedido.getId());
                pedidoProductoRepository.save(t);
            }
        }

        tempPedido.setDetalles(detalles);
        return tempPedido;

    }
}


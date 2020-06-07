package com.jcarlo.dev.ventas.service;

import com.jcarlo.dev.ventas.model.Pedido;
import com.jcarlo.dev.ventas.model.PedidoProducto;
import com.jcarlo.dev.ventas.repository.PedidoProductoRepository;
import com.jcarlo.dev.ventas.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/pedidos")
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    PedidoProductoRepository pedidoProductoRepository;

    private EmitterProcessor<Pedido> notificationProcessor;

    @PostConstruct
    private void createProcessor() {
        notificationProcessor = EmitterProcessor.<Pedido>create();
    }


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

        notificationProcessor.onNext(tempPedido);

        return tempPedido;

    }

    /**
     * Flujo reactivo que contiene los datos de los pedidos
     *
     * @return
     */
    private Flux<ServerSentEvent<Pedido>> getPedidoSSE() {

        // notification processor retorna un FLUX en el cual podemos estar "suscritos" cuando este tenga otro valor ...
        return notificationProcessor
                .log().map(
                        (pedido) -> {
                            return ServerSentEvent.<Pedido>builder()
                                    .id(UUID.randomUUID().toString())
                                    .event("persona-result")
                                    .data(pedido)
                                    .build();
                        }).concatWith(Flux.never());
    }



    /**
     * Flujo reactivo que posee un "heartbeat" para que la conexión del cliente se mantenga
     *
     * @return
     */
    private Flux<ServerSentEvent<Pedido>> getNotificationHeartbeat() {
        return Flux.interval(Duration.ofSeconds(15))
                .map(i -> {
                    System.out.println(String.format("sending heartbeat [%s] ...", i.toString()));
                    return ServerSentEvent.<Pedido>builder()
                            .id(String.valueOf(i))
                            .event("heartbeat-result")
                            .data(null)
                            .build();
                });
    }

    /**
     * Servicio reactivo que retorna la combinación de los dos flujos antes declarados
     * Simplificacion de declaracion GET por "GetMapping"
     *
     * @return
     */
    @CrossOrigin
    @GetMapping( value = "/notification/sse" )
    public Flux<ServerSentEvent<Pedido>> getJobResultNotification() {
        return Flux.merge(
                getNotificationHeartbeat(),
                getPedidoSSE()
        );
    }
}


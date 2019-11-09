package com.jcarlo.dev.ventas.service;

import com.jcarlo.dev.ventas.model.Telefono;
import com.jcarlo.dev.ventas.model.Vendedor;
import com.jcarlo.dev.ventas.repository.PedidoRepository;
import com.jcarlo.dev.ventas.repository.TelefonoRepository;
import com.jcarlo.dev.ventas.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/vendedores")
@Transactional
public class VendedorService {

    @Autowired
    VendedorRepository vendedorRepository;
    @Autowired
    TelefonoRepository telefonoRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Vendedor> getAll() {
        return vendedorRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public List<Vendedor> deleteVendedor(@PathVariable("id") int id) {
        pedidoRepository.deleteByIdVendedor(id);
        vendedorRepository.deleteById(id);
        return vendedorRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Vendedor createVendedor(@RequestBody Vendedor vendedor) {
        if(vendedor.getId() > 0) {
            return vendedorRepository.save(vendedor);
        }
        List<Telefono> telefonos = vendedor.getTelefonos();
        vendedor.setTelefonos(null);
        Vendedor temp = vendedorRepository.save(vendedor);
        if(telefonos != null) {
            for(Telefono t: telefonos) {
                t.setIdVendedor(temp.getId());
                telefonoRepository.save(t);
            }
        }
        temp.setTelefonos(telefonos);

        return temp;
    }

}

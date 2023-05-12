package com.example.tiendautil.web;

import com.example.tiendautil.domain.Articulos;
import com.example.tiendautil.domain.Cliente;
import com.example.tiendautil.servicio.ArticulosService;
import com.example.tiendautil.servicio.ClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private ArticulosService articulosService;

    @Autowired
    ClienteService clienteService;


    @GetMapping("/")
    public String inicio(){
        return "index";
    }

    @GetMapping("/cliente")
    public String listarCliente(Model model, Cliente cliente){
        var clientes = clienteService.listarClientes();
        log.info("*****Listando clientes");
        model.addAttribute("clientes",clientes);
        return "/test/clientes";
    }

    @GetMapping("/articulo")
    public String listarArticulo(Model model){
        return "/test/articulos";
    }

    @GetMapping("/compra")
    public String compra(){
        return "/compraUtiles";
    }

    @PostMapping("/guardarCliente")
    public String guardarCliente(@Valid Cliente cliente, Errors errores){
        log.info("*****Guardando cliente");
        if (errores.hasErrors()){
            return "/test/clientes";
        }
        clienteService.guardar(cliente);
        log.info("*****Se guardo cliente");
        return "/test/clientes";
    }

}

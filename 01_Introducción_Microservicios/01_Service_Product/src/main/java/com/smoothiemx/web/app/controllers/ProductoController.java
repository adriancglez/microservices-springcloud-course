package com.smoothiemx.web.app.controllers;

import com.smoothiemx.web.app.models.Producto;
import com.smoothiemx.web.app.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductoController {

    private final IProductoService service;
    private Environment environment;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    public ProductoController(IProductoService service, Environment environment) {
        this.service = service;
        this.environment = environment;
    }

    @GetMapping("/listar")
    public List<Producto> listar() {
        return this.service.findAll().stream().map(producto -> {
            /*producto.setPort(Integer.parseInt(this.environment.getProperty("local.server.port")));*/
            producto.setPort(this.port);
            return producto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable("id") Long id) throws Exception {
        Producto producto = this.service.findById(id);
        /*producto.setPort(Integer.parseInt(this.environment.getProperty("local.server.port")));*/
        producto.setPort(this.port);
        return producto;
    }
}
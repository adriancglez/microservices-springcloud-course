package com.smoothiemx.web.app.controllers;

import com.smoothiemx.web.app.models.Producto;
import com.smoothiemx.web.app.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoController {

    private final IProductoService service;

    @Autowired
    public ProductoController(IProductoService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public List<Producto> listar() {
        return this.service.findAll();
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable("id") Long id) {
        System.out.println(id);
        return this.service.findById(id);
    }
}
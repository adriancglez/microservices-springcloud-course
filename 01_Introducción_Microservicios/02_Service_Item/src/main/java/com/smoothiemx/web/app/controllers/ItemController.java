package com.smoothiemx.web.app.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.smoothiemx.web.app.models.Item;
import com.smoothiemx.web.app.models.Producto;
import com.smoothiemx.web.app.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private final IItemService iItemService;

    @Autowired
    public ItemController(@Qualifier("itemServiceFeign") IItemService iItemService) {
        this.iItemService = iItemService;
    }

    @GetMapping("/listar")
    public List<Item> lsitar() {
        return this.iItemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
        return this.iItemService.findById(id, cantidad);
    }

    public Item metodoAlternativo(@PathVariable Long id, @PathVariable Integer cantidad) {
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Cámara Sony de Alternativo");
        producto.setPrecio(500.00);
        item.setProducto(producto);
        return item;
    }
}

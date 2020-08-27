package com.smoothiemx.web.app.services;

import com.smoothiemx.web.app.clientes.ProductoClienteRest;
import com.smoothiemx.web.app.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("itemServiceFeign")
public class ItemServiceFeign implements IItemService {

    private ProductoClienteRest productoClienteRest;

    @Autowired
    public ItemServiceFeign(ProductoClienteRest productoClienteRest) {
        this.productoClienteRest = productoClienteRest;
    }

    @Override
    public List<Item> findAll() {
        return this.productoClienteRest.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantdad) {
        return new Item(this.productoClienteRest.detalle(id), cantdad);
    }
}
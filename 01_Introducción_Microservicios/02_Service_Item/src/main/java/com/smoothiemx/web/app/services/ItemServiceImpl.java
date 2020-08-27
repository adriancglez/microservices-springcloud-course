package com.smoothiemx.web.app.services;

import com.smoothiemx.web.app.models.Item;
import com.smoothiemx.web.app.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service("itemServiceImpl")
public class ItemServiceImpl implements IItemService {

    private final RestTemplate clienteRest;

    @Autowired
    public ItemServiceImpl(RestTemplate clienteRest) {
        this.clienteRest = clienteRest;
    }

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(Objects.requireNonNull(clienteRest.getForObject("http://servicio-productos/listar", Producto[].class)));
        return productos.stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantdad) {

        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Producto producto = this.clienteRest.getForObject("http://servicio-productos/ver/{id}", Producto.class, pathVariables);

        return new Item(producto, cantdad);
    }
}

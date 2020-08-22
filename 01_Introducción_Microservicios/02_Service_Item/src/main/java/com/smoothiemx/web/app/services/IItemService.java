package com.smoothiemx.web.app.services;

import com.smoothiemx.web.app.models.Item;

import java.util.List;

public interface IItemService {

    public List<Item> findAll();

    public Item findById(Long id, Integer cantdad);
}

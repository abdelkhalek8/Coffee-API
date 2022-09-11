package com.example.springjwt.service;

import com.example.springjwt.domain.Item;
import com.example.springjwt.domain.User;

import java.util.List;

public interface ItemService {

    List<Item> getItems();

    Item addItem(Item item);

    String deleteItem(Long id);
}

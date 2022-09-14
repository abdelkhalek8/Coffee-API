package com.example.springjwt.service;

import com.example.springjwt.domain.Item;
import com.example.springjwt.repo.ItemRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Component("itemServiceImpl")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;


    @Override
    public List<Item> getItems() {
        return itemRepo.findAll();

    }

    @Override
    public Item addItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public String deleteItem(Long id) {
        itemRepo.deleteById(id);
        return "Item deleted";
    }
}


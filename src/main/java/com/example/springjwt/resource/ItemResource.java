package com.example.springjwt.resource;

import com.example.springjwt.domain.Item;
import com.example.springjwt.domain.User;
import com.example.springjwt.service.ItemService;
import com.example.springjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") @RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class ItemResource {
    @Autowired

    private final ItemService itemService;
    @Autowired
    private final UserService userService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItem(){
        return ResponseEntity.ok().body(itemService.getItems());
     /*   try {

        }catch (Exception e){
            return null;
        }*/
    }
    @PostMapping(value = "/user/save", headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user){
      log.info("in rout");
        return ResponseEntity.ok().body(userService.saveUser(user));

    }

    @PostMapping("/item")
    public ResponseEntity<Item> addItem(@RequestBody Item item){
        return ResponseEntity.ok().body(itemService.addItem(item));
      /*  try {

        }catch (Exception e){
            return null;
        }*/
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(itemService.deleteItem(id));

        }catch (Exception e){
            return null;
        }
    }
}

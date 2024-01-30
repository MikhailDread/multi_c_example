package com.example.demo.service;

import com.example.demo.data.entity.ItemEntity;
import com.example.demo.data.entity.UserEntity;
import com.example.demo.data.xml.Item;
import com.example.demo.data.xml.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUserPurchase(User user) {
        UserEntity userEntity = getUserEntity(user, getItemEntity(user.getPurchaseItem()));

        userRepository.save(userEntity);
    }

    private UserEntity getUserEntity(User user, List<ItemEntity> itemEntity) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setPurchases(itemEntity);
        userEntity.setAmount(user.getAmount());
        userEntity.setCount(user.getCount());
        userEntity.setPurchaseDate(user.getPurchaseDate());
        userEntity.setLastName(user.getLastName());

        return userEntity;
    }

    private List<ItemEntity> getItemEntity(List<Item> items) {
        List<ItemEntity> itemEntities = new ArrayList<>();
        items.forEach(itm -> {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setId(itm.getId());
            itemEntity.setName(itm.getName());
        });

        return itemEntities;
    }
}

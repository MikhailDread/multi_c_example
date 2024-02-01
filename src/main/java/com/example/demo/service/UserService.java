package com.example.demo.service;

import com.example.demo.data.entity.ItemEntity;
import com.example.demo.data.entity.UserEntity;
import com.example.demo.data.xml.Item;
import com.example.demo.data.xml.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapperService mapperService;

    public void saveUserPurchase(User user) {
        UserEntity userEntity = getUserEntity(user, getItemEntity(user.getPurchaseItem()));
        LOGGER.info("Сохранение данных о пользователе в БД");

        userRepository.save(userEntity);
    }

    private UserEntity getUserEntity(User user, List<ItemEntity> itemEntity) {
        return mapperService.mapToNewUser(user, itemEntity);
    }

    private List<ItemEntity> getItemEntity(List<Item> items) {
        List<ItemEntity> itemEntities = new ArrayList<>();
        if(items != null && !items.isEmpty()) {
            items.forEach(itm -> {
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setId(itm.getId());
                itemEntity.setName(itm.getName());
                itemEntities.add(itemEntity);
            });
        }

        return itemEntities;
    }
}

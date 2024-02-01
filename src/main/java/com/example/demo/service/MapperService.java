package com.example.demo.service;

import com.example.demo.data.entity.ItemEntity;
import com.example.demo.data.entity.UserEntity;
import com.example.demo.data.xml.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperService {

    public UserEntity mapToNewUser(User user, List<ItemEntity> itemEntity) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setAge(userEntity.getAge());
        userEntity.setPurchases(itemEntity);
        userEntity.setAmount(user.getAmount());
        userEntity.setCount(user.getCount());
        userEntity.setPurchaseDate(user.getPurchaseDate());
        userEntity.setLastName(user.getLastName());

        return userEntity;
    }
}

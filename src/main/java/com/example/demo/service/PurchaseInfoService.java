package com.example.demo.service;

import com.example.demo.controller.PurchaseItemController;
import com.example.demo.data.dto.ItemDto;
import com.example.demo.data.dto.UserDto;
import com.example.demo.data.entity.ItemEntity;
import com.example.demo.data.entity.UserEntity;
import com.example.demo.data.entity.UsersItemsEntity;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UsersItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseItemController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UsersItemsRepository usersItemsEntity;

    public List<ItemDto> purchaseFromLastWeek() {
        LOGGER.info("Процесс получения покупок за последнюю неделю");
        List<UsersItemsEntity> itemsInLastWeek = usersItemsEntity.getItemsInLastWeek();
        List<ItemEntity> itemsById = itemRepository.findAllById(itemsInLastWeek.stream()
                .map(UsersItemsEntity::getItem)
                .collect(Collectors.toList())
        );

        List<ItemDto> itemsNames = new ArrayList<>();
        if(!itemsById.isEmpty()) {
            itemsById.forEach(item -> {
                ItemDto itemDto = new ItemDto();
                itemDto.setName(item.getName());
                itemsNames.add(itemDto);
            });
        } else {
            LOGGER.error("В процессе получения списка покупок не удалось извлечь данные из БД");
        }

        LOGGER.info("Покупки за последнюю неделю извлечены и отправлены для формирования ответа");

        return itemsNames;
    }

    public ItemDto mostPurchaseInAll() {
        ItemDto itemDto = new ItemDto();
        LOGGER.info("Процесс получения самого покупаемого товара");
        ItemEntity itemEntity = itemRepository.mostBuyItem();
        if (itemEntity != null) {
            itemDto.setName(itemEntity.getName());
        } else {
            LOGGER.error("В процессе получения самого покупаемого товара не удалось извлечь данные из БД");
        }
        LOGGER.info("Самый покупаемый товар найден и отправлен для формирования ответа");

        return itemDto;
    }

    public UserDto biggestBuyer() {
        UserDto userDto = new UserDto();
        LOGGER.info("Процесс получения пользователя с самым большим кол-во покупок");
        UserEntity topBuyer = userRepository.getTopBuyer();
        if (topBuyer != null) {
            userDto.setName(topBuyer.getName());
            userDto.setLastName(topBuyer.getLastName());
        } else {
            LOGGER.error("В процессе получения пользователя не удалось извлечь данные из БД");
        }
        LOGGER.info("Пользователь с самым большим кол-вом покупок найден и отправлен для формирования ответа");

        return userDto;
    }

    public ItemDto age18Interesting() {
        ItemDto itemDto = new ItemDto();
        LOGGER.info("Получение товаров интересных аудитории 18 лет");
        ItemEntity popularItemIn18Age = itemRepository.getPopularItemIn18Age();
        if (popularItemIn18Age != null) {
            itemDto.setName(popularItemIn18Age.getName());
        } else {
            LOGGER.error("В процессе получения интересного товара в возрасте 18 лет не удалось извлечь данные из БД");
        }
        LOGGER.info("Товар найден и отправлен на формирование ответа");

        return itemDto;
    }
}

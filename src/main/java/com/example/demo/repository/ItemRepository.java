package com.example.demo.repository;

import com.example.demo.data.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    @Query(value = "SELECT item.id, item.name, COUNT(users_items.item_id) AS purchase_count\n" +
            "FROM item\n" +
            "JOIN users_items ON item.id = users_items.item_id\n" +
            "JOIN users ON users_items.user_id = users.id\n" +
            "WHERE users.purchase_date >= CURRENT_DATE - INTERVAL '1' MONTH\n" +
            "GROUP BY item.id, item.name\n" +
            "ORDER BY purchase_count DESC\n" +
            "LIMIT '1'",
            nativeQuery = true)
    ItemEntity mostBuyItem();

    @Query(value = "SELECT item.id, item.name, COUNT(users_items.item_id) AS purchase_count\n" +
            "FROM item\n" +
            "JOIN users_items ON item.id = users_items.item_id\n" +
            "JOIN users ON users_items.user_id = users.id\n" +
            "WHERE users.age = 18\n" +
            "GROUP BY item.id, item.name\n" +
            "ORDER BY purchase_count DESC\n" +
            "LIMIT '1'",
            nativeQuery = true)
    ItemEntity getPopularItemIn18Age();
}

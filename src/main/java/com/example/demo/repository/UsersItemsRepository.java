package com.example.demo.repository;

import com.example.demo.data.entity.UsersItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersItemsRepository extends JpaRepository<UsersItemsEntity, Integer> {
@Query(value = "SELECT users_items.user_id, users_items.item_id\n" +
        "FROM users_items\n" +
        "JOIN users ON users_items.user_id = users.id\n" +
        "WHERE users.purchase_date >= CURRENT_DATE - INTERVAL '7' DAY",
        nativeQuery = true)
    List<UsersItemsEntity> getItemsInLastWeek();
}

package com.example.demo.repository;

import com.example.demo.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT users.id, users.name, users.last_name, users.age, users.amount, users.count, users.purchase_date, COUNT(users_items.item_id) AS purchase_count\n" +
            "FROM users\n" +
            "JOIN users_items ON users.id = users_items.user_id\n" +
            "WHERE users.purchase_date >= CURRENT_DATE - INTERVAL '6' MONTH\n" +
            "GROUP BY users.id, users.name, users.last_name\n" +
            "ORDER BY purchase_count DESC\n" +
            "LIMIT '1'",
    nativeQuery = true)
    UserEntity getTopBuyer();
}

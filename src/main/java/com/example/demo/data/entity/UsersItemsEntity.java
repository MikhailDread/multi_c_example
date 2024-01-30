package com.example.demo.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_items")
@ApiModel(description = "БД сущность связи пользователя с купленными товарами")
public class UsersItemsEntity {
    @Column(name = "user_id")
    @Id
    @ApiModelProperty(value = "ID пользователя")
    private Integer user;

    @Column(name = "item_id")
    @ApiModelProperty(value = "ID товара")
    private Integer item;


    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersItemsEntity that = (UsersItemsEntity) o;
        return Objects.equals(user, that.user) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, item);
    }
}

package com.example.demo.data.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@ApiModel(description = "БД сущность пользователя")
public class UserEntity {
    @Id
    @ApiModelProperty(value = "ID пользователя")
    private Integer id;
    @Column(length = 255)
    @ApiModelProperty(value = "Имя пользователя")
    private String name;
    @Column(name = "last_name", length = 255)
    @ApiModelProperty(value = "Фамилия пользователя")
    private String lastName;
    @ApiModelProperty(value = "Возраст пользователя")
    private Integer age;
    @ApiModelProperty(value = "Кол-во покупок пользователя")
    private int count;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_items",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @ApiModelProperty(value = "Купленные товары пользователя")
    private List<ItemEntity> purchases;
    @Column(precision = 10, scale = 2)
    @ApiModelProperty(value = "Общая сумма по покупкам пользователя")
    private Double amount;
    @Column(name = "purchase_date")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(value = "Последняя дата покупки пользователя")
    private Date purchaseDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ItemEntity> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<ItemEntity> purchases) {
        this.purchases = purchases;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return count == that.count && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(age, that.age) && Objects.equals(purchases, that.purchases) && Objects.equals(amount, that.amount) && Objects.equals(purchaseDate, that.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, age, count, purchases, amount, purchaseDate);
    }
}

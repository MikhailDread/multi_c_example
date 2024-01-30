package com.example.demo.data.xml;

import com.example.demo.service.DateAdapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "user")
@XmlType(namespace = "https://www.example.org/user")
@ApiModel(description = "Java-представление xml-объекта пользователя")
public class User {
    @ApiModelProperty(value = "ID пользователя")
    private Integer id;
    @ApiModelProperty(value = "Имя пользователя")
    private String name;
    @ApiModelProperty(value = "Фамилия пользователя")
    private String lastName;
    @ApiModelProperty(value = "Возраст пользователя")
    private Integer age;
    @ApiModelProperty(value = "Покупки пользователя")
    private List<Item> purchaseItem;
    @ApiModelProperty(value = "Сумма за покупки пользователя")
    private Double amount;
    @ApiModelProperty(value = "Вол-во покупок пользователя")
    private Integer count;
    @ApiModelProperty(value = "Дата покупки пользователя")
    private Date purchaseDate;

    @XmlElement
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement(name = "lastname")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Item> getPurchaseItem() {
        return purchaseItem;
    }

    @XmlElement(name = "purchase")
    public void setPurchaseItem(List<Item> purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    @XmlElement
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    @XmlElement
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @XmlElement(name = "purchase_date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}

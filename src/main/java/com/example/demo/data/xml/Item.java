package com.example.demo.data.xml;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(namespace = "https://www.example.org/item")
@ApiModel(description = "Java-представление xml-объекта товара")
public class Item {
    @ApiModelProperty(value = "ID товара")
    private Integer id;
    @ApiModelProperty(value = "Название товара")
    private String name;

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
}

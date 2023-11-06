package com.example.projectbookshop.dto;

import java.math.BigDecimal;

public class ProductDTO {
    private int id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    public ProductDTO() {

    }

    public ProductDTO(int id, String name, BigDecimal price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }



    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +

                '}';
    }
}

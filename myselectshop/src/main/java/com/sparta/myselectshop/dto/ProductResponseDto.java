package com.sparta.myselectshop.dto;

import com.sparta.myselectshop.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String title;
    private String link;
    private String image;
    private int lPrice;
    private int myPrice;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.link = product.getLink();
        this.image = product.getImage();
        this.lPrice = product.getLprice();
        this.myPrice = product.getMyprice();
    }
}
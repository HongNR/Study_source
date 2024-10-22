package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Item {

    @Id @GeneratedValue
    @Column(name = "ITME_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantitiy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantitiy() {
        return stockQuantitiy;
    }

    public void setStockQuantitiy(int stockQuantitiy) {
        this.stockQuantitiy = stockQuantitiy;
    }
}

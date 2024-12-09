package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ITME_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantitiy;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

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

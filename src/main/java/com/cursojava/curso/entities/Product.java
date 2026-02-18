package com.cursojava.curso.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id // chave primaria da tabela do banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY) // por ser uma chave numérica, ela vai ser alto incremental na tabela
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    // set -> ele garante que não vai ter um produto com mais de uma categoria. Set é uma ‘interface’, não pode ser instanciado
    // foi instanciada para não começar nula
    // @Transient -> impede a interpretação do JPA
    @ManyToMany() // Associação de muitos e muitos
    // Nome da tabela e quais chaves seram associadas.
    // JoinColmuns -> nome da chave
    // inverseJoinColumns -> outra chave
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    // set -> ele garante que não vai ter um produto com mais de uma categoria. Set é uma ‘interface’, não pode ser instanciado
    // foi instanciada para não começar nula
    // associação um para um

    // aqui tem uma coleção de orderItem
    @OneToMany(mappedBy = "id.product") // id.order -> no OrderItem tem o atributo id e esse atributo do tipo OrderItemPK, e esse tipo OrderItemPK tem o order // id -> OrderItem e product -> OrderItemPK
    private Set<OrderItem> items = new HashSet<>();

    public Product() {}

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    // product com mais de um OrderItem, aonde será varrido para OrderItem e para cada OrderItem vai ser pegado a Order associado a ele
    @JsonIgnore // para não ter um loop, porque é aqui que se chama o pedido associado ao item de pedido
    public Set<Order> getOrders() {
        Set<Order> set = new HashSet<>();
        for (OrderItem x : items) {
            set.add(x.getOrder());
        }
        return set;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

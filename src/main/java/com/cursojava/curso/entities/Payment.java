package com.cursojava.curso.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import javax.sound.midi.Instrument;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_payment") // colocar outro nome para não dar conflito com a palavra do banco, nesse caso User
public class Payment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id // chave primaria da tabela do banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY) // por ser uma chave numérica, ela vai ser alto incremental na tabela
    private Long id;
    private Instant moment;

    @JsonIgnore
    @OneToOne // (Sempre na classe dependente) Associação um para um, aonde a classe Ordem é independente e a classe Payment é dependente, aonde mesmo que tenha zero pagamentos ele pode entrar no bando de dados de Order
    @MapsId
    private Order order;

    public Payment() {}

    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
        Payment other = (Payment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

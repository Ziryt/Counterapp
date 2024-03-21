package com.ziryt.counter;

import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Objects;

@Entity
public class Counter {

    @Id
    @SequenceGenerator(
            name = "counter_id_generator",
            sequenceName = "counter_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "counter_id_generator"
    )
    private Integer Id;
    private String name;
    private Integer count;
    private Integer limited;
    private String color;

    public Counter(Integer id,
                   String name,
                   Integer count,
                   Integer limited,
                   String color) {
        Id = id;
        this.name = name;
        this.count = count;
        this.limited = limited;
        this.color = color;
    }

    public Counter() {
    }



    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLimited() {
        return limited;
    }

    public void setLimited(Integer limited) {
        this.limited = limited;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counter counter = (Counter) o;
        return Objects.equals(Id, counter.Id)
                && Objects.equals(name, counter.name)
                && Objects.equals(count, counter.count)
                && Objects.equals(limited, counter.limited)
                && Objects.equals(color, counter.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, count, limited, color);
    }

    @Override
    public String toString() {
        return "Counter{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", limit=" + limited +
                ", color='" + color + '\'' +
                '}';
    }
}

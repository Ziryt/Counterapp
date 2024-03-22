package com.ziryt.counter;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class Counter {

    @Id
    @SequenceGenerator(
            name = "counter_id_sequence",
            sequenceName = "counter_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "counter_id_sequence"
    )
    private Integer Id;
    private String name;
    private Integer initial_value;
    private Integer current_value;
    private Integer limited;
    private String color;

    public Counter(Integer id,
                   String name,
                   Integer initial_value,
                   Integer current_value,
                   Integer limited,
                   String color) {
        Id = id;
        this.name = name;
        this.initial_value = initial_value;
        this.current_value = current_value;
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

    public Integer getInitial_value() {
        return initial_value;
    }

    public void setInitial_value(Integer initial_value) {
        this.initial_value = initial_value;
    }

    public Integer getCurrent_value() {
        return current_value;
    }

    public void setCurrent_value(Integer current_value) {
        this.current_value = current_value;
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
                && Objects.equals(initial_value, counter.initial_value)
                && Objects.equals(current_value, counter.current_value)
                && Objects.equals(limited, counter.limited)
                && Objects.equals(color, counter.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, initial_value, current_value, limited, color);
    }

    @Override
    public String toString() {
        return "Counter{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", initial value=" + initial_value +
                ", current value=" + current_value +
                ", limit=" + limited +
                ", color='" + color + '\'' +
                '}';
    }
}

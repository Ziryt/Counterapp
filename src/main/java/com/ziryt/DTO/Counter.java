package com.ziryt.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Objects;

@Entity
@Table(
        name = "counter",
        uniqueConstraints = {
                @UniqueConstraint(name = "counter_unique_name", columnNames = "name")
        }
)
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
    @Column(
            name = "id",
            updatable = false
    )
    private Integer Id;
    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;
    @Column(
            name = "initial_value",
            nullable = false
    )
    private Integer initialValue;
    @Column(
            name = "current_value",
            nullable = false
    )
    private Integer currentValue;
    @Column(
            name = "top_limit"
    )
    private Integer topLimit;
    @Column(
            name = "bottom_limit"
    )
    private Integer bottomLimit;
    private String color;

    public Counter(Integer id,
                   String name,
                   Integer initialValue,
                   Integer currentValue,
                   Integer topLimit,
                   String color) {
        Id = id;
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = currentValue;
        this.topLimit = topLimit;
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

    public Integer getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Integer initialValue) {
        this.initialValue = initialValue;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getTopLimit() {
        return topLimit;
    }

    public void setTopLimit(Integer topLimit) {
        this.topLimit = topLimit;
    }

    public Integer getBottomLimit() {
        return bottomLimit;
    }

    public void setBottomLimit(Integer bottomLimit) {
        this.bottomLimit = bottomLimit;
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
                && Objects.equals(initialValue, counter.initialValue)
                && Objects.equals(currentValue, counter.currentValue)
                && Objects.equals(topLimit, counter.topLimit)
                && Objects.equals(bottomLimit, counter.bottomLimit)
                && Objects.equals(color, counter.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, initialValue, currentValue, topLimit, bottomLimit, color);
    }

    @Override
    public String toString() {
        return "Counter{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", initial value=" + initialValue +
                ", current value=" + currentValue +
                ", top limit=" + topLimit +
                ", bottom limit=" + bottomLimit +
                ", color='" + color + '\'' +
                '}';
    }
}

package com.ziryt.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(
        name = "counter",
        uniqueConstraints = {
                @UniqueConstraint(name = "counter_unique_name", columnNames = "name")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
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

}

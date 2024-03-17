package com.ziryt;

import java.util.Objects;

public class Counter {
    private Integer Id;
    private String name;
    private Integer count;
    private Integer limit;
    private String color;

    public Counter(Integer id,
                   String name,
                   Integer count,
                   Integer limit,
                   String color) {
        Id = id;
        this.name = name;
        this.count = count;
        this.limit = limit;
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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
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
                && Objects.equals(limit, counter.limit)
                && Objects.equals(color, counter.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, count, limit, color);
    }

    @Override
    public String toString() {
        return "Counter{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", limit=" + limit +
                ", color='" + color + '\'' +
                '}';
    }
}

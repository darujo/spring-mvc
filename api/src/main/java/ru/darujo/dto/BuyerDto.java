package ru.darujo.dto;

public class BuyerDto {
    private long id;
    private String Name;

    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public BuyerDto(long id, String name) {
        this.id = id;
        Name = name;
    }
}

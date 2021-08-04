package org.max.trello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Card {
    private String id;
    private String name;
    private Date due;

    public Card(String id, String name, Date due) {
        this.id = id;
        this.name = name;
        this.due = due;
    }

    public Card() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    @Override
    public String toString()  {

        return "Card{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", due='" + due + '\'' +
                '}';
    }
}
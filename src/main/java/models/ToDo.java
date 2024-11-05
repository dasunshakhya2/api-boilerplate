package models;


import lombok.Data;

@Data
public class ToDo {
    private String title;
    private int userId;
    private int id;
    private boolean completed;


}

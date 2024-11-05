package models;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "todo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ToDo {
    private String title;
    private int userId;
    private int id;
    private boolean completed;


}

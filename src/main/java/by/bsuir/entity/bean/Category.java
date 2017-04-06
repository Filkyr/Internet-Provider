package by.bsuir.entity.bean;

import lombok.Data;
import java.io.Serializable;

@Data
public class Category implements Serializable {
    private static long serialVersionUID = 1L;

    private int id;
    private String name;
    private boolean subscribe;

    public Category(){}

    public Category(int id){
        this.id = id;
    }
}
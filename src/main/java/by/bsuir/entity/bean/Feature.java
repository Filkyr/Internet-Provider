package by.bsuir.entity.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Feature implements Serializable {
    private static long serialVersionUID = 1L;

    private int id;
    private int productId;
    private String feature;

    public Feature(){}

    public Feature(String feature){
        this.feature = feature;
    }
}

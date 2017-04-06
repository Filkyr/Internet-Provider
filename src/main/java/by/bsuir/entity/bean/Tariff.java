package by.bsuir.entity.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Tariff implements Serializable {
    private static long serialVersionUID = 1L;

    private int id;
    private String name;
    private double monthlyCost;
    private String description;
    private List<Feature> features;
    private Category category;
    private boolean used;
}

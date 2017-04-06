package by.bsuir.entity.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static long serialVersionUID = 1L;

    private int id;
    private String email;
    private transient byte[] password;
    private boolean admin;
}

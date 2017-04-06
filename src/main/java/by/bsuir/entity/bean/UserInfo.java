package by.bsuir.entity.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static long serialVersionUID = 1L;

    private int userId;
    private String surname;
    private String name;
    private String lastName;
    private String mobilePhone;
}

package by.bsuir.entity.dto;

import lombok.Data;

@Data
public class ServerAnswer<T> {
    private String error;
    private T content;
}

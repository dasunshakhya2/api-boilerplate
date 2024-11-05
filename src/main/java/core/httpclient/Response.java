package core.httpclient;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private T entity;
    private int statusCode;
    private boolean isValidSchema;
}

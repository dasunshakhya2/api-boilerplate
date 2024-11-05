package controllers;

import configs.Configurations;
import core.httpclient.HttpClient;
import core.httpclient.Response;
import core.httpclient.Result;
import core.json.JsonParser;
import core.schema.SchemaValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import models.ToDo;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoController {


    public static Response<ToDo> getToDoById(int todoId) {
        String url = Configurations.BASE_URL + "/todos/" + todoId;
        Result result = HttpClient.sendGetRequest(url);
        ToDo toDo = JsonParser.convertJsonStringToObject(ToDo.class, result.getResBody());
        boolean isValidSchema = SchemaValidator.validateSchema(ToDo.class, result.getResBody(), false).isValid();
        return new Response<>(toDo, result.getStatusCode(), isValidSchema);

    }

    public static Response<List<ToDo>> getToDos() {
        String url = Configurations.BASE_URL + "/todos/";
        Result result = HttpClient.sendGetRequest(url);
        List<ToDo> toDoList = JsonParser.convertJsonStringToObjectList(ToDo.class, result.getResBody());
        boolean isValidSchema = SchemaValidator.validateSchema(ToDo.class, result.getResBody(), true).isValid();
        return new Response<>(toDoList, result.getStatusCode(), isValidSchema);
    }

}

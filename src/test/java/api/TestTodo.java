package api;

import controllers.TodoController;
import core.httpclient.Response;
import models.ToDo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class TestTodo {

    @Test
    public void testGetToDoById() {
        Response<ToDo> response = TodoController.getToDoById(1);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getEntity().getTitle(), "delectus aut autem");
        Assert.assertTrue(response.isValidSchema());
    }

    @Test
    public void testGetToDos() {
        Response<List<ToDo>> response = TodoController.getToDos();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.isValidSchema());
    }
}

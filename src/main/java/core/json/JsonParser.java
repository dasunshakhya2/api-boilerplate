package core.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonParser {

    private static final Gson gson = new Gson();


    public static <T> T convertJsonStringToObject(Class<T> type, String json) {
        return gson.fromJson(json, type);
    }

    public static <T> List<T> convertJsonStringToObjectList(Class<T> type, String json) {
        Type userListType = TypeToken.getParameterized(List.class, type).getType();
        return gson.fromJson(json, userListType);
    }

    public static <T> String convertObjectToString(T type) {
        return gson.toJson(type);
    }
}

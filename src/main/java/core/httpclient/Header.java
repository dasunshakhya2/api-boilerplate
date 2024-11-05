package core.httpclient;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Header {

    private final Map<String, Object> headerValues = new HashMap<>();

    public void addHeaderValue(String key, Object value) {
        headerValues.put(key, value);
    }

}

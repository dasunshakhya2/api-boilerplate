package configs;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Configurations {

    public static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "https://jsonplaceholder.typicode.com";
    public static final boolean VALIDATE_SCHEMA = Boolean.parseBoolean(System.getenv("VALIDATE_SCHEMA"));


}
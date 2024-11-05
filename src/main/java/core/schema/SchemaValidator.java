package core.schema;


import configs.Configurations;
import core.utils.FileLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SchemaValidator {

    private static final Logger LOGGER = Logger.getLogger("JsonValidator");

    private static Validation validate(Path path, String jsonPayload, boolean isFlux) {
        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(path))) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(rawSchema);
            if (isFlux) {
                schema.validate(new JSONArray(jsonPayload));
            } else {
                schema.validate(new JSONObject(jsonPayload));
            }

        } catch (ValidationException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return Validation.builder().validationErrors(e.getAllMessages()).build();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return Validation.builder().validationErrors(new ArrayList<>()).build();
    }


    public static <T> Validation validateSchema(Class<T> type, String jsonPayload, boolean isFlux) {
        if (Configurations.VALIDATE_SCHEMA) {
            String schemaKey = "";
            if (isFlux) {
                schemaKey = type.getSimpleName().toLowerCase() + "s.schema.json";
            } else {
                schemaKey = type.getSimpleName().toLowerCase() + ".schema.json";
            }
            Path schemaPath = FileLoader.getFilePath(schemaKey);
            return validate(schemaPath, jsonPayload, isFlux);
        }
        return Validation.builder().validationErrors(new ArrayList<>()).build();
    }


}

package core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileLoader {
    private static final Logger logger = Logger.getLogger("FileLoader");
    private static final Map<String, Path> FILE_MAP = new HashMap<>();

    static {
        try (Stream<Path> filePathStream = Files.find(Paths.get("src/main/resources"),
                Integer.MAX_VALUE, (filePath, fileAttr) ->
                        (fileAttr.isRegularFile()))) {
            filePathStream.forEach(f -> {
                if (f.getFileName().toString().endsWith("json")) {
                    FILE_MAP.put(f.getFileName().toString(), f);
                }
            });
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
            System.exit(-1);
        }
    }

    public static Path getFilePath(String path) {
        return FILE_MAP.get(path);
    }


}

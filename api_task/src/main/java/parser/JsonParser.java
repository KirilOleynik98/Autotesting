package parser;
import aquality.selenium.core.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.PostPojo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class JsonParser {

    private static String testPath = "src/main/resources/TestData.json";
    private static String configPath = "src/main/resources/Config.json";
    static ObjectMapper objectMapper = new ObjectMapper();

    public static List<PostPojo> parseTestData() {
        File file = new File(testPath);
        List<PostPojo> testData = new ArrayList<>();
        try {
            testData = Arrays.asList(objectMapper.readValue(file, PostPojo.class));
        } catch (IOException e) {
            Logger.getInstance().error(String.valueOf(e));
            throw new NullPointerException();
        }
        return testData;
    }

    public static HashMap<String, String> parseConfig() {
        File file = new File(configPath);
        HashMap<String, String> config = new HashMap<>();
        try {
            config = objectMapper.readValue(file, HashMap.class);
        } catch (IOException e) {
            Logger.getInstance().error(String.valueOf(e));
            throw new NullPointerException();
        }
        return config;
    }
}

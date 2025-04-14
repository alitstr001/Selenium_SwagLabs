package Utilties;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class DataUtils {
    private static final String Test_Data_Path = "src/test/resources/TestData/";

    public static String getJsonData(String fileName, String key) throws FileNotFoundException {
        FileReader reader = new FileReader(Test_Data_Path + fileName + ".json");
        JsonElement jsonElement = JsonParser.parseReader(reader);
        return jsonElement.getAsJsonObject().get(key).getAsString();
    }
}

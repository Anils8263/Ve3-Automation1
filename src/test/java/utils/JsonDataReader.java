package utils;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonDataReader {

    public static Object[][] getCredentials(String filePath) {
        List<Object[]> data = new ArrayList<>();
        try {
            ClassLoader classLoader = JsonDataReader.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(filePath);

            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new InputStreamReader(inputStream));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray users = (JSONArray) jsonObject.get("users");

            for (Object userObj : users) {
                JSONObject user = (JSONObject) userObj;
                String username = (String) user.get("username");
                String password = (String) user.get("password");
                data.add(new Object[]{username, password});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toArray(new Object[0][0]);
    }
}

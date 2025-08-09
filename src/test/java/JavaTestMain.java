import utils.JsonDataReader;
public class JavaTestMain {

    //public class JsonTestMain {
        public static void main(String[] args) {
            String filePath = "src/test/resources/users.json";
            Object[][] credentials = JsonDataReader.getCredentials(filePath);

            for (Object[] user : credentials) {
                System.out.println("Username: " + user[0]);
                System.out.println("Password: " + user[1]);
            }
        }
    }



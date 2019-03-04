import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User;
import utils.SheetsServiceUtil;
import utils.SpreadsheetUtil;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest {
    /*
     * Tải file credentials.json enable cho account (copy vào resources/credentials.json)
     * Lần chạy đầu tiên sẽ lưu token vào disk hoặc memory
     * */
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String TOKENS_DIRECTORY_PATH = "D:/token";

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        SpreadsheetUtil spreadsheetUtil = new SpreadsheetUtil(SheetsServiceUtil.getSheetsSerivce(CREDENTIALS_FILE_PATH, TOKENS_DIRECTORY_PATH));

        String spreadsheetId = spreadsheetUtil.create("Test Sheet");

        Gson gson = new Gson();

        List<User> users = new ArrayList<>();
        users.add(new User("john", "san diago", 21));
        users.add(new User("thomas", "miami", 22));

        // convert to json
        String jsonString = gson.toJson(users);

        // convert to object
        List<User> userList = gson.fromJson(jsonString, new TypeToken<List<User>>() {
        }.getType());

        // convert to list để ghi vào các cell excel
        List<List<Object>> result = new ArrayList<>();
        for (User user : userList) {
            List<Object> t = Arrays.asList(user.toValues());
            result.add(t);
        }

        spreadsheetUtil.updateValues(spreadsheetId, "Sheet1", "RAW", result);
    }
}

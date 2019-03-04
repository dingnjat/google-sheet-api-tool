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

    private static final String SPREADSHEET_ID = "1z8NlBfxH330BxI79M8bxHPvYZqpxy8HEYjKmWkLo-Wc";

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        SpreadsheetUtil spreadsheetUtil = new SpreadsheetUtil(SheetsServiceUtil.getSheetsSerivce());
        Gson gson = new Gson();
        List<User> users = new ArrayList<>();
        users.add(new User("john", "san diago", 21));
        users.add(new User("thomas", "miami", 22));
        String jsonString = gson.toJson(users);
        List<User> userList = gson.fromJson(jsonString, new TypeToken<List<User>>() {
        }.getType());
        List<List<Object>> result = new ArrayList<>();
        for (User user : userList) {
            List<Object> t = Arrays.asList(user.toValues());
            result.add(t);
        }
        spreadsheetUtil.updateValues(SPREADSHEET_ID, "Sheet1", "RAW", result);
    }
}

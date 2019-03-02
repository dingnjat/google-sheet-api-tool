import com.google.api.services.sheets.v4.Sheets;
import utils.SheetsServiceUtil;
import utils.SpreadsheetTools;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainTest {

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        Sheets service = SheetsServiceUtil.getSheetsSerivce();
        SpreadsheetTools spreadsheetTools = new SpreadsheetTools(service);
        spreadsheetTools.create("sheets");
    }
}

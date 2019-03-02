package utils;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpreadsheetTools {

    private Sheets service;

    public SpreadsheetTools(Sheets service) {
        this.service = service;
    }

    public String create(String title) throws IOException {
        Spreadsheet spreadsheet = new Spreadsheet().setProperties(new SpreadsheetProperties().setTitle(title));
        spreadsheet = service.spreadsheets().create(spreadsheet).setFields("spreadsheetId").execute();
        return spreadsheet.getSpreadsheetId();
    }

    public UpdateValuesResponse updateValues(String spreadsheetId, String range, String valueInputOption, List<List<Object>> values) throws IOException {
        ValueRange body = new ValueRange().setValues(values);
        return service.spreadsheets().values().update(spreadsheetId, range, body)
                .setValueInputOption(valueInputOption)
                .execute();
    }

    public UpdateValuesResponse updateValues(String spreadsheetId, String range, String valueInputOption, Object[][] values) throws IOException {
        List<List<Object>> list = new ArrayList<>();
        for (Object[] array : values) {
            list.add(Arrays.asList(array));
        }
        return this.updateValues(spreadsheetId, range, valueInputOption, list);
    }
}

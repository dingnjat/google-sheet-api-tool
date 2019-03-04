package utils;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpreadsheetUtil {

    private Sheets service;

    public SpreadsheetUtil(Sheets service) {
        this.service = service;
    }

    public String create(String title) throws IOException {
        Spreadsheet spreadsheet = new Spreadsheet().setProperties(new SpreadsheetProperties().setTitle(title));
        spreadsheet = service.spreadsheets().create(spreadsheet).setFields("spreadsheetId").execute();
        return spreadsheet.getSpreadsheetId();
    }

    public ValueRange getValues(String spreadsheetId, String range) throws IOException {
        return service.spreadsheets().values().get(spreadsheetId, range).execute();
    }

    public BatchGetValuesResponse batchGetValues(String spreadsheetId, List<String> ranges) throws IOException {
        return service.spreadsheets().values().batchGet(spreadsheetId).setRanges(ranges).execute();
    }

    public UpdateValuesResponse updateValues(String spreadsheetId, String range, String valueInputOption, List<List<Object>> values) throws IOException {
        ValueRange body = new ValueRange().setValues(values);
        return service.spreadsheets().values().update(spreadsheetId, range, body).setValueInputOption(valueInputOption).execute();
    }

    public UpdateValuesResponse updateValues(String spreadsheetId, String range, String valueInputOption, Object[][] values) throws IOException {
        List<List<Object>> list = new ArrayList<>();
        for (Object[] array : values) {
            list.add(Arrays.asList(array));
        }
        return this.updateValues(spreadsheetId, range, valueInputOption, list);
    }

    public AppendValuesResponse appendValues(String spreadsheetId, String range, String valueInputOption, List<List<Object>> values) throws IOException {
        ValueRange body = new ValueRange().setValues(values);
        return service.spreadsheets().values().append(spreadsheetId, range, body).setValueInputOption(valueInputOption).execute();
    }

    public AppendValuesResponse appendValues(String spreadsheetId, String range, String valueInputOption, Object[][] values) throws IOException {
        List<List<Object>> list = new ArrayList<>();
        for (Object[] array : values) {
            list.add(Arrays.asList(array));
        }
        return this.appendValues(spreadsheetId, range, valueInputOption, list);
    }
}

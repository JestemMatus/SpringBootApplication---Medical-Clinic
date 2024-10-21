package pl.dmcs.amatuszewski.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomTimeEditor extends PropertiesEditor {

    private final SimpleDateFormat timeFormat;

    public CustomTimeEditor(SimpleDateFormat timeFormat, boolean allowEmpty) {
        this.timeFormat = timeFormat;
        this.timeFormat.setLenient(false);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(new Time(timeFormat.parse(text).getTime()));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse time: " + text, e);
        }
    }

    @Override
    public String getAsText() {
        Time value = (Time) getValue();
        return (value != null ? this.timeFormat.format(value) : "");
    }
}

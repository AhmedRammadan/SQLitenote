package note.com;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class item_Note {
    private String title , description   ;
    int color;

    public item_Note() {
    }

    DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
    String date = df.format(Calendar.getInstance().getTime());
    public item_Note(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public item_Note(String title, String description , int color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

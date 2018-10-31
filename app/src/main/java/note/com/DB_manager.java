package note.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB_manager extends SQLiteOpenHelper {
    public DB_manager(Context context) {
        super(context, "DB NOTE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists notes(id Integer primary key autoincrement , title varchar , description varchar , color Integer)");
      //  defaultData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    void defaultData(SQLiteDatabase db ){
        db.execSQL(" insert into notes values (null,'title 1','description 1 ');");
        db.execSQL(" insert into notes values (null,'title 2','description 2 ');");
        db.execSQL(" insert into notes values (null,'title 3','description 3 ');");
        db.execSQL(" insert into notes values (null,'title 4','description 4 ');");
        db.execSQL(" insert into notes values (null,'title 5','description 5 ');");
    }
    public ArrayList<item_Note> getAllNotes(SQLiteDatabase db){
        ArrayList<item_Note> item_notes =new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from notes",null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                item_Note note = new item_Note(title,description);
                item_notes.add(note);
            }while (cursor.moveToNext());
        }
        return item_notes;
    }
    public boolean addData(SQLiteDatabase db , item_Note item_note){
        ContentValues cv = new ContentValues();
        cv.put("title",item_note.getTitle());
        cv.put("description",item_note.getDescription());
        cv.put("color",item_note.getColor());
        long result = db.insert("notes",null,cv);
        if (result ==-1)return false;
        else return true;
    }
    public String getdescription_note(SQLiteDatabase db , String title){
        String description_note = "empty";
        Cursor cursor =db.rawQuery("select description from notes",null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                description_note  = cursor.getString(cursor.getColumnIndex("description"));
            }while (cursor.moveToNext());
        }
        return description_note;
    }
    Integer getColor (SQLiteDatabase db , String title){
        int color = 0;
        Cursor cursor =db.rawQuery("select color from notes",null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                color = cursor.getInt(cursor.getColumnIndex("color"));
            }while (cursor.moveToNext());
        }
        return color;
    }
    String getTitle (SQLiteDatabase db ){
        String title ="";
        Cursor cursor =db.rawQuery("select title from notes",null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                title = cursor.getString(cursor.getColumnIndex("title"));
            }while (cursor.moveToNext());
        }
        return title;
    }

}

package note.com;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AddNotes extends AppCompatActivity {
    DB_manager manager ;
    SQLiteDatabase db ;
    EditText et_title ;
    EditText et_desc;
    LinearLayout linearlayout;
    item_Note item_note ;
    int color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotes);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Add Note");
        item_note = new item_Note();
        manager= new DB_manager(AddNotes.this);
        db=manager.getWritableDatabase();
        et_title =findViewById(R.id.title);
        et_desc=findViewById(R.id.description);
        linearlayout=findViewById(R.id.linearlayout);
        color = getResources().getColor(R.color.color1);



    }
    public void add(View v){
        String title = et_title.getText().toString();
        String desc = et_desc.getText().toString();

        if (!title.equals("") && !desc.equals("")) {
            item_Note addNote = new item_Note(title, desc,color);
            boolean result = manager.addData(db, addNote);
            if (result) {
                startActivity(new Intent(AddNotes.this,MainActivity.class));
            } else {
                Toast.makeText(AddNotes.this, "not add", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(AddNotes.this, "pleas Enter title or description", Toast.LENGTH_LONG).show();
        }
    }
    public void color1(View v){
        color = getResources().getColor(R.color.color1);
        item_note.setColor(color);
        linearlayout.setBackgroundColor(item_note.getColor());
    }
    public void color2(View v){
        color = getResources().getColor(R.color.color2);
        item_note.setColor(color);
        linearlayout.setBackgroundColor(item_note.getColor());
    }
    public void color3(View v){
        color = getResources().getColor(R.color.color3);
        item_note.setColor(color);
        linearlayout.setBackgroundColor(item_note.getColor());
    }
    public void color4(View v){
        color = getResources().getColor(R.color.color4);
        item_note.setColor(color);
        linearlayout.setBackgroundColor(item_note.getColor());
    }
}

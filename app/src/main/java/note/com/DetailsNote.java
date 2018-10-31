package note.com;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsNote extends AppCompatActivity {
    TextView tv_title,tv_description_note ;
    SQLiteDatabase db ;
    DB_manager manager ;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_note);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Details");
        tv_title = findViewById(R.id.details_title);
        tv_description_note =findViewById(R.id.details_description_note);
        linearLayout = findViewById(R.id.details_linearlayout);

        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");

        manager = new DB_manager(DetailsNote.this);
        db = manager.getWritableDatabase();
        int color =manager.getColor(db,title);

        linearLayout.setBackgroundColor(color);
        tv_title.setText(title);
        tv_description_note.setText(desc);


    }


}

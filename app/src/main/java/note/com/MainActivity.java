package note.com;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DB_manager manager ;
    SQLiteDatabase db;
    ArrayList<item_Note> note_list ;
   // ListView listNote ;
   // Adaper adaper;
    RecyclerView recyclerView ;
    LinearLayout linearLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.LinearLayout_item);


        manager =new DB_manager(MainActivity.this);
        db=manager.getWritableDatabase();
       // listNote = findViewById(R.id.list_not);
       /* String title =manager.getTitle(db);
        int color = manager.getColor(db,title);
        linearLayout.setBackgroundColor(color);*/
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,AddNotes.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        note_list= manager.getAllNotes(db);
        AdaptewithRec adapter = new AdaptewithRec(MainActivity.this,note_list);
       // adaper =new Adaper(MainActivity.this,0,note_list);
        //listNote.setAdapter(adaper);
        recyclerView.setAdapter(adapter);
    }

    public void AlertDialog(){
        final AlertDialog builder = new AlertDialog.Builder(MainActivity.this).create();
        final View dialogShow= getLayoutInflater().inflate(R.layout.addnote,null);
        builder.setView(dialogShow);
        builder.show();
        builder.setCancelable(true);
        final EditText et_title =dialogShow.findViewById(R.id.title);
        final EditText et_desc=dialogShow.findViewById(R.id.description);
        Button add =dialogShow.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String desc = et_desc.getText().toString();
               if (!title.equals("") && !desc.equals("")) {
                   item_Note addNote = new item_Note(title, desc);
                   boolean result = manager.addData(db, addNote);
                   if (result) {
                       Toast.makeText(MainActivity.this, "add", Toast.LENGTH_SHORT).show();
                       builder.dismiss();

                   } else {
                       Toast.makeText(MainActivity.this, "not add", Toast.LENGTH_SHORT).show();
                   }
               }else {
                   Toast.makeText(MainActivity.this, "pleas Enter title or description", Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}

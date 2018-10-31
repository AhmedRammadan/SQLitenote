package note.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaper extends ArrayAdapter<item_Note> {
    public Adaper(Context context, int resource, ArrayList<item_Note> notes) {
        super(context, 0, notes);
    }


    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        View v = convertView;
        if (v==null){
            v= LayoutInflater.from(getContext()).inflate(R.layout.item_note,parent,false);
        }
        item_Note item_note = getItem(position);
        TextView title = v.findViewById(R.id.title);
        title.setText(item_note.getTitle());
        TextView desc= v.findViewById(R.id.date);
        desc.setText(item_note.getDate());
        return v;
    }
}

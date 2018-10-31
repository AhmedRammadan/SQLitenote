package note.com;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptewithRec extends RecyclerView.Adapter<AdaptewithRec.ViewHolder> {
    Context context ;
    ArrayList<item_Note> item_notes ;

    public AdaptewithRec(Context context, ArrayList<item_Note> item_notes) {
        this.context = context;
        this.item_notes = item_notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_note,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.date.setText(item_notes.get(i).getDate());
        viewHolder.title.setText(item_notes.get(i).getTitle());
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(context,DetailsNote.class);
                intent.putExtra("title",item_notes.get(i).getTitle());
                intent.putExtra("desc",item_notes.get(i).getDescription());
                intent.putExtra("color",item_notes.get(i).getColor());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title ,date ;

        ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            date= itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
        }


        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
    }
}

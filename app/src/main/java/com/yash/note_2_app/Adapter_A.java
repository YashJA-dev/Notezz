package com.yash.note_2_app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.yash.note_2_app.AndroidRoom.Database.Tables.Archived_Table;

public class Adapter_A extends ListAdapter<Archived_Table, Adapter_A.ExampleViewHolder_A> {
public  Context context;
    Onlongclick onlongclick;

    public class ExampleViewHolder_A extends RecyclerView.ViewHolder{
        TextView date;
        TextView title;
        TextView note;
        CardView cardView;

        public ExampleViewHolder_A(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date_m_popup1);
            title=itemView.findViewById(R.id.title_m_popup);
            note=itemView.findViewById(R.id.notes_popup2);
            cardView=itemView.findViewById(R.id.container);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onlongclick.onClick();
                    return false;
                }
            });
        }
    }
    public void setLongClick(Onlongclick onlongclick){
        this.onlongclick=onlongclick;
    }
    protected Adapter_A(Context context) {
        super(DIFFUTILL_CALLBACK);
        this.context=context;

    }

    private static DiffUtil.ItemCallback<Archived_Table> DIFFUTILL_CALLBACK=new DiffUtil.ItemCallback<Archived_Table>() {
        @Override
        public boolean areItemsTheSame(@NonNull Archived_Table oldItem, @NonNull Archived_Table newItem) {

        return  oldItem.getId()==newItem.getId() ;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Archived_Table oldItem, @NonNull Archived_Table newItem) {
            return oldItem.getId()==newItem.getId()&&
                    oldItem.getNote().equals(newItem.getNote())&&
                    oldItem.getTitle().equals(newItem.getTitle());
        }
    };


    @NonNull
    @Override
    public ExampleViewHolder_A onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_view,parent,false);
        return new ExampleViewHolder_A(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder_A holder, int position) {
        Archived_Table archived_table=getItem(position);
        holder.note.setText(archived_table.getNote());
        holder.date.setText(archived_table.getDate()+" And "+archived_table.getTime());
        holder.title.setText(archived_table.getTitle());
        holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.white));
    }
    public Archived_Table getNoteAt(int position){
        return getItem(position);
    }
    public interface Onlongclick{
        public void onClick();
    }

}

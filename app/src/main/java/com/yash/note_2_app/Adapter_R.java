package com.yash.note_2_app;

import android.content.Context;
import android.content.res.ColorStateList;
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

import com.yash.note_2_app.AndroidRoom.Constant_m;
import com.yash.note_2_app.AndroidRoom.Database.Tables.Main_Table;

import java.util.ArrayList;

import static android.graphics.Color.valueOf;

public class Adapter_R extends ListAdapter<Main_Table,Adapter_R.ExampleViewHolder> {
    Context context;
    ObjectClickListener objectClickListener;
    int n=0;

    protected Adapter_R(Context context) {

        super(diff_callback);
        this.context=context;
    }
    private static final DiffUtil.ItemCallback<Main_Table> diff_callback=new
            DiffUtil.ItemCallback<Main_Table>() {
                @Override
                public boolean areItemsTheSame(@NonNull Main_Table oldItem, @NonNull Main_Table newItem) {
                    return oldItem.getId()==newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Main_Table oldItem, @NonNull Main_Table newItem) {
                    return oldItem.getNote().equals(newItem.getNote())&&
                            oldItem.getId()==newItem.getId()&&
                            oldItem.getDate().equals(newItem.getDate())&&
                            oldItem.getTime().equals(newItem.getTime())&&
                            oldItem.getTitle().equals(newItem.getTitle());
                }
            };

    public void lister(ObjectClickListener objectClickListener){
        this.objectClickListener=objectClickListener;
    }
    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView title;
        TextView note;
        CardView cardView;
        View v;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date_m_popup1);
            title=itemView.findViewById(R.id.title_m_popup);
            note=itemView.findViewById(R.id.notes_popup2);
            cardView=itemView.findViewById(R.id.container);
        }
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.items_view,parent,false);

        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, final int position) {
        Main_Table main_table=getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectClickListener.onClick(getItem(position),holder.cardView.getBackgroundTintList());
            }
        });

        if(n==0){
            holder.cardView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.grey)));
            print(main_table,holder);
            n++;
        }else if(n==1){
            holder.cardView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.blue)));
            print(main_table,holder);
            n++;
        }else if(n==2){
            holder.cardView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.green)));
            print(main_table,holder);
            n++;
        }else if(n==3){
            holder.cardView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.yellow)));
            print(main_table,holder);
            n++;
        }else if(n==4){
            holder.cardView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.brown)));
            print(main_table,holder);
            n++;
        }else if(n==5){
            holder.cardView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.white)));
            print(main_table,holder);
            n++;
        }else if(n==6){
            holder.cardView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.purple)));
            print(main_table,holder);
            n++;
        } else if(n==7){
            holder.cardView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.orange)));
            print(main_table,holder);
            n=0;
        }
    }
    private void print( Main_Table main_table,ExampleViewHolder holder){
        holder.note.setText(main_table.getNote());
        holder.title.setText(main_table.getTitle());
        holder.date.setText(main_table.getDate()+"  And  "+main_table.getTime());
    }
    public Main_Table getNoteAt(int position){
        return getItem(position);
    }
    public interface ObjectClickListener{
        public void onClick(Main_Table main_table,ColorStateList colorStateList);
    }


}

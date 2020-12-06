package com.yash.note_2_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.yash.note_2_app.AndroidRoom.Database.Tables.Archived_Table;
import com.yash.note_2_app.AndroidRoom.Database.Tables.Main_Table;
import com.yash.note_2_app.AndroidRoom.ViewModel.ViewModelM;

import java.util.List;

public class Archived extends AppCompatActivity {
RecyclerView recyclerView;
Adapter_A adapter_a;
ImageView back;
ViewModelM viewModelM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archived);
        Window window;
        window=getWindow();
        window.setBackgroundDrawableResource(R.drawable.topbg);
        back=findViewById(R.id.backk0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Archived.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_lift,R.anim.slide_out_right);
                finish();
            }
        });

        recyclerView=findViewById(R.id.RecyclerView_A);
        adapter_a=new Adapter_A(getApplicationContext());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        viewModelM= new ViewModelProvider(this).get(ViewModelM.class);
        viewModelM.getListArchived().observe(this, new Observer<List<Archived_Table>>() {
            @Override
            public void onChanged(List<Archived_Table> archived_tables) {
                adapter_a.submitList(archived_tables);
            }
        });
        adapter_a.setLongClick(new Adapter_A.Onlongclick() {
            @Override
            public void onClick() {
                Toast.makeText(Archived.this,"Right swipe to remove from archive\nand" +
                        "\n" +
                        "Right swipe to delete this Note",Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(adapter_a);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction==ItemTouchHelper.LEFT){
                    Archived_Table archived_table=adapter_a.getNoteAt(viewHolder.getAdapterPosition());
                    viewModelM.deleteA(archived_table);
                    Toast.makeText(Archived.this,"Note Deleted!",Toast.LENGTH_SHORT).show();
                }
                if (direction==ItemTouchHelper.RIGHT){
                    Archived_Table archived_table=adapter_a.getNoteAt(viewHolder.getAdapterPosition());
                    Main_Table main_table=new Main_Table(archived_table.getNote(),
                            archived_table.getDate(),
                            archived_table.getTime(),
                            archived_table.getTitle(),
                            archived_table.getId());
                    viewModelM.insertm(main_table);
                    viewModelM.deleteA(archived_table);
                }

            }
        }).attachToRecyclerView(recyclerView);

    }

}
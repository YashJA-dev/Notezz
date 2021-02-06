package com.yash.note_2_app.AndroidRoom.Screens;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yash.note_2_app.AndroidRoom.Constants.Constant_m;
import com.yash.note_2_app.AndroidRoom.Database.Tables.Archived_Table;
import com.yash.note_2_app.AndroidRoom.Database.Tables.Main_Table;
import com.yash.note_2_app.AndroidRoom.Database.ViewModel.ViewModelM;
import com.yash.note_2_app.AndroidRoom.Views.Adapter_R;
import com.yash.note_2_app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Calendar calendar;
    ImageView archived;
    SimpleDateFormat simpleFormatter, simpleTimeFormat;
    String date, time;
    Dialog dialog;
    Dialog dialog2;
    //observer creation
    ViewModelM viewModel;
    FloatingActionButton floatingActionButton;
    Adapter_R adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.tool));
        Window window=getWindow();
        window.setBackgroundDrawableResource(R.drawable.topbg);
        initialize();
        viewModel.getGetList_main().observe(this, new Observer<List<Main_Table>>() {
            @Override
            public void onChanged(List<Main_Table> main_tables) {
                adapter.submitList(main_tables);
            }
        });

        /**
         * floating action button click listener
         */
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText title_button, note_button;
                final TextView time_button;
                Button save, cancle;
                time_button = dialog2.findViewById(R.id.date_m_popup_button);
                title_button = dialog2.findViewById(R.id.title_m_popup_button);
                note_button = dialog2.findViewById(R.id.notes_popup_button);
                save = dialog2.findViewById(R.id.save_button);
                cancle = dialog2.findViewById(R.id.cancel_button2);
                //date formater
                simpleFormatter = new SimpleDateFormat("dd-MM-yyyy");
                simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                time = simpleTimeFormat.format(calendar.getTime());
                date = simpleFormatter.format(calendar.getTime());
                time_button.setText(time + " and " + date);
                title_button.setText("");
                note_button.setText("");
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = title_button.getText().toString();
                        String note = note_button.getText().toString();
                        if (note.matches("")){
                            Toast.makeText(getApplicationContext()," Note missing!",Toast.LENGTH_SHORT).show();
                            viverator(100);
                        }else {
                                Main_Table main_table = new Main_Table(note, date, time, title);
                                viewModel.insertm(main_table);
                                dialog2.dismiss();
                                recyclerView.smoothScrollToPosition(0);
                            }

                        }
                });
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                dialog2.show();
            }
        });
        recyclerView.setAdapter(adapter);
        //on swipe item recycler view
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    Main_Table main_table = adapter.getNoteAt(viewHolder.getAdapterPosition());
                    viewModel.deletem(main_table);
                }

            }
        }).attachToRecyclerView(recyclerView);

        //recycle view listenter

        adapter.lister(new Adapter_R.ObjectClickListener() {
            @Override
            public void onClick(final Main_Table main_table, ColorStateList colorStateList) {
                final EditText title, note;
                final TextView time;
                Button cancle,save;
                CardView view = dialog.findViewById(R.id.card);
                view.setBackgroundTintList(colorStateList);
                time = dialog.findViewById(R.id.date_m_popup_button);
                title = dialog.findViewById(R.id.title_m_popup_button);
                note = dialog.findViewById(R.id.notes_popup_button);
                time.setText(main_table.getDate()+" And "+main_table.getTime());
                title.setText(main_table.getTitle());

                note.setText(main_table.getNote());
                save=dialog.findViewById(R.id.save_button);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //date formater
                        simpleFormatter = new SimpleDateFormat("dd-MM-yyyy");
                        simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
                        String time2 = simpleTimeFormat.format(calendar.getTime());
                        String date2 = simpleFormatter.format(calendar.getTime());
                        String title2=title.getText().toString();
                        String note2=note.getText().toString();
                        if(note2.matches("")){
                            Toast.makeText(getApplicationContext()," Note missing!",Toast.LENGTH_SHORT).show();
                            viverator(100);
                        }else {
                            if(title2.matches("")){
                                Main_Table main_table1=new Main_Table(note2,date2,time2,title2,main_table.getId());
                                viewModel.updateM(main_table1);
                                dialog.dismiss();
                            }
                            Main_Table main_table1=new Main_Table(note2,date2,time2,title2,main_table.getId());
                            viewModel.updateM(main_table1);
                            dialog.dismiss();
                        }

                    }
                });
                cancle=dialog.findViewById(R.id.cancel_button2);
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //recycle view long click listenter

        adapter.setObjectLongClickListener(new Adapter_R.ObjectLongClickListener() {
            @Override
            public void onClick(Main_Table main_table) {
                Archived_Table archived_table=new Archived_Table(main_table.getNote(),
                        main_table.getDate(),
                        main_table.getTime(),
                        main_table.getTitle(),
                        main_table.getId());
                viewModel.insertA(archived_table);
                viewModel.deletem(main_table);
                Toast.makeText(MainActivity.this,"Archived!",Toast.LENGTH_SHORT).show();
            }
        });
        archived=findViewById(R.id.Archived_button);
        archived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Archived.class);
                startActivity(intent);
            }
        });
    }
    private void initialize() {
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter_R(getApplicationContext());
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        calendar = Calendar.getInstance();
        viewModel = ViewModelProviders.of(this).get(ViewModelM.class);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        //floting button dialog
        dialog2 = new Dialog(this);
        dialog2.setContentView(R.layout.popup);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //recycleview click dialog

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void viverator(int value){
        final Vibrator vibe = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        vibe.vibrate(value);
    }
}
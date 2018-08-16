package com.example.waterusagediary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DiaryEntries extends AppCompatActivity {
    private String day;
    private LoadEntries loadEntries;
    private ArrayList<Day> days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entries);

        days = new ArrayList<>();
        day = getIntent().getStringExtra("day");
        loadEntries=new LoadEntries();
        loadEntries.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onClick(View view){
        int num = 0;
        for(Day daY:days){
            if(daY.getDay().compareTo(day)==0){
                break;
            }
            num++;
        }

        if(view.getId()==R.id.next){
            num++;
            if(num>=days.size()){
                num=0;
            }
        }

        else if(view.getId()==R.id.prev){
            num--;
            if(num<0){
                num=days.size()-1;
            }
        }

        Intent intent = new Intent(this,DiaryEntries.class);
        intent.putExtra("day",days.get(num).getDay());
        startActivity(intent);
        finish();
    }
    class LoadEntries extends Thread{
        Context context;
        DatabaseHelper databaseHelper;
        ArrayList<Entry> entries;
        @Override
        public void run(){
            context = DiaryEntries.this;
            databaseHelper = new DatabaseHelper(context,null,null,1);
            entries = databaseHelper.getEntries();

            Button button2 = (Button)findViewById(R.id.day);
            button2.setText(day);

            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.listScrollView);
            linearLayout.removeAllViews();

            double total=0; int numOfDays = 0;
            for(int i = 0; i<entries.size();i++){

                boolean found = false;
                for(Day day:days){
                    if(day.getDay().compareTo(entries.get(i).getDate())==0){
                        found = true;
                    }
                }
                if(!found){
                    Day day = new Day(entries.get(i).getDate());
                    days.add(day);
                }



                if(day.compareTo(entries.get(i).getDate())==0){

                    CardView cardView = new CardView(context);
                    cardView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    cardView.setCardElevation(10);
                    cardView.setPadding(0,15,0,15);
                    cardView.setCardBackgroundColor(
                            context.getColor(R.color.design_default_color_primary)
                    );
                    LinearLayout linearLayout2 = new LinearLayout(context);
                    linearLayout2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    linearLayout2.setOrientation(LinearLayout.VERTICAL);
                    linearLayout2.setGravity(1);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    TextView textView1 = new TextView(context); TextView textView2 = new TextView(context);
                    textView1.setLayoutParams(params);textView2.setLayoutParams(params);
                    textView1.setText(entries.get(i).getName());
                    textView2.setText(entries.get(i).getAmount()+" litres.");
                    linearLayout2.addView(textView1);
                    linearLayout2.addView(textView2);
                    cardView.addView(linearLayout2);
                    linearLayout.addView(cardView);
                    View view = new View(context);
                    view.setLayoutParams(params);
                    view.setMinimumHeight(2);
                    linearLayout.addView(view);
                    total += entries.get(i).getAmount();


                }
            }
            Button button = (Button)findViewById(R.id.dayTotal);
            button.setText("Total: "+total+" litres");


        }
    }
}

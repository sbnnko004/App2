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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadEntries loadEntries = new LoadEntries();
        loadEntries.start();
    }

    public void openCalculator(View view){
        Intent intent = new Intent(this, Calculator.class);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        //LoadEntries loadEntries = new LoadEntries();
        //loadEntries.start();
    }
    class LoadEntries extends Thread{
        Context context;
        DatabaseHelper databaseHelper;
        ArrayList<Entry> entries;
        ArrayList<Day> days = new ArrayList<>();
        @Override
        public void run(){
            context = MainActivity.this;
            databaseHelper = new DatabaseHelper(context,null,null,1);
            entries = databaseHelper.getEntries();
            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear);
            linearLayout.removeAllViews();
            double total=0; int numOfDays = 0;
            for(int i = 0; i<entries.size();i++){
                boolean found = false;
                for(Day day:days){
                    if(day.getDay().compareTo(entries.get(i).getDate())==0){

                        day.addToTotal(entries.get(i).getAmount());
                        found = true;

                    }
                }
                if(!found){
                    System.out.println(entries.get(i).getDate());

                    Day day = new Day(entries.get(i).getDate());
                    day.addToTotal(entries.get(i).getAmount());
                    days.add(day);
                }

                /*
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
                TextView textView1 = new TextView(context); TextView textView2 = new TextView(context);TextView textView3 = new TextView(context);
                textView1.setLayoutParams(params);textView2.setLayoutParams(params);textView3.setLayoutParams(params);
                textView1.setText(entries.get(i).getName());
                textView2.setText(entries.get(i).getAmount()+" litres.");
                textView3.setText(entries.get(i).getDate());
                linearLayout2.addView(textView1);
                linearLayout2.addView(textView2);
                linearLayout2.addView(textView3);
                cardView.addView(linearLayout2);
                linearLayout.addView(cardView);
                View view = new View(context);
                view.setLayoutParams(params);
                view.setMinimumHeight(2);
                linearLayout.addView(view);
                */
                total += entries.get(i).getAmount();
            }

            for(Day day:days){
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
                TextView textView1 = new TextView(context); TextView textView2 = new TextView(context);//TextView textView3 = new TextView(context);
                textView1.setLayoutParams(params);textView2.setLayoutParams(params);//textView3.setLayoutParams(params);
                textView1.setText(day.getDay());
                textView2.setText(day.getTotal()+" litres.");
                //textView3.setText(entries.get(i).getDate());
                linearLayout2.addView(textView1);
                linearLayout2.addView(textView2);
                //linearLayout2.addView(textView3);
                cardView.addView(linearLayout2);
                cardView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(context,DiaryEntries.class);
                                                    intent.putExtra("day",((TextView)((LinearLayout)((CardView)view).getChildAt(0)).getChildAt(0)).getText().toString());
                                                    MainActivity.this.startActivity(intent);
                                                }
                                            });
                linearLayout.addView(cardView);
                View view = new View(context);
                view.setLayoutParams(params);
                view.setMinimumHeight(2);
                linearLayout.addView(view);

            }


            Button button = (Button)findViewById(R.id.totalButton);
            button.setText(button.getText().toString()+" "+total+" litres.");

            Button button1 = (Button)findViewById(R.id.averageButton);
            if(days.size()==0)
                button1.setText(button1.getText().toString()+" "+total+" litres.");
            else
                button1.setText(button1.getText().toString()+" "+total/days.size()+" litres.");

        }
    }
}

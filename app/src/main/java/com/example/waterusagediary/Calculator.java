package com.example.waterusagediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    private DatabaseHelper databaseHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        databaseHelper = new DatabaseHelper(this, null, null, 1);
    }

    public void onClick(View v){
        Editable holder;
        if(v.getId()==R.id.imageButton){

            //Shower
            holder = ((EditText)findViewById(R.id.id2)).getText();
            double shower=0;
            if(holder!=null&&!((holder.toString()).equals(""))){

              shower = Double.parseDouble(holder.toString());
              databaseHelper.addEntry(new Entry("Shower",shower));
              holder.clear();
            }


            //Toilet
            holder = ((EditText)findViewById(R.id.id4)).getText();
            double Toilet;
            if(holder.toString()!=null&&!((holder.toString()).equals(""))){

                Toilet = Double.parseDouble(holder.toString());
                databaseHelper.addEntry(new Entry("Toilet",Toilet));
                holder.clear();
            }


            //Hygiene
            holder = ((EditText)findViewById(R.id.id6)).getText();
            double Hygiene;
            if(holder.toString()!= null && !((holder.toString()).equals(""))){

                Hygiene = Double.parseDouble(holder.toString());
                databaseHelper.addEntry(new Entry("Hygiene",Hygiene));
                holder.clear();
            }


            //Laundry
            holder = ((EditText)findViewById(R.id.id8)).getText();
            double Laundry;
            if(holder.toString()!=null && !((holder.toString()).equals(""))){

                Laundry = Double.parseDouble(holder.toString());
                databaseHelper.addEntry(new Entry("Laundry",Laundry));
                holder.clear();
            }

            //Dishes
            holder = ((EditText)findViewById(R.id.id10)).getText();
            double Dishes;
            if(holder.toString()!=null && !((holder.toString()).equals(""))) {
                Dishes = Double.parseDouble(holder.toString());
                databaseHelper.addEntry(new Entry("Dishes",Dishes));
                holder.clear();
            }


            //Drinking
            holder = ((EditText)findViewById(R.id.id12)).getText();
            double Drinking;
            if(holder.toString()!=null && !((holder.toString()).equals(""))){

                Drinking = Double.parseDouble(holder.toString());
                databaseHelper.addEntry(new Entry("Drinking",Drinking));
                holder.clear();
            }


            //Cooking
            holder = ((EditText)findViewById(R.id.id14)).getText();
            double Cooking;
            if(holder!=null && !((holder.toString()).equals(""))){

                Cooking = Double.parseDouble(holder.toString());
                databaseHelper.addEntry(new Entry("Cooking",Cooking));
                holder.clear();
            }

            //Cleaning
            holder = ((EditText)findViewById(R.id.id16)).getText();
            double Cleaning;
            if(holder!=null && !((holder.toString()).equals(""))){

                Cleaning = Double.parseDouble(holder.toString());
                databaseHelper.addEntry(new Entry("Cleaning",Cleaning));
                holder.clear();
            }

            //Other
            holder = ((EditText)findViewById(R.id.editText9)).getText();
            double Other;
            if(holder!=null && !((holder.toString()).equals(""))){

                Other = Double.parseDouble(holder.toString());
                databaseHelper.addEntry(new Entry("Other",Other));
                holder.clear();
            }



        }
    }


}

package com.example.anithavikkiraman.db_trial;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button next_bt, view_bt,sub_bt;
    EditText efid,equants ;

    AutoCompleteTextView autoTextView;
    int sprice,samt,total = 0;
    String eprice,x,smsmsg;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        myDb.insertData();
        next_bt = (Button) findViewById(R.id.btnext);
        view_bt = (Button) findViewById(R.id.btview);
        sub_bt = (Button) findViewById(R.id.btsub);


        String[] MenuItems = { "Dosa", "Chapathi", "Poori", "Fried Rice", "Pongal", "Noodles",
                "Idly", "Icecream", "Milkshake", "Meals", "Burger", "Sandwich" };
        autoTextView = (AutoCompleteTextView) findViewById(R.id.autocompleteEditTextView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, MenuItems);
        //Used to specify minimum number of
        //characters the user has to type in order to display the drop down hint.
        autoTextView.setThreshold(1);
        //Setting adapter
        autoTextView.setAdapter(arrayAdapter);

        Nextitem();
        Viewbill();
        Submit();




       /* AddData();
        Viewbill();
        newAddData();
        newviewAll();*/

    }

    private void Submit() {
        sub_bt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int amt;
                        Cursor res = myDb.getAllData();


                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        while (res.moveToNext()) {

                            //amt=Integer.parseInt(res.getString(4)) ;
                            //total=total+amt;
                            x = res.getString(4);
                            if (x != null) {
                                amt = Integer.parseInt(x);
                                total = total + amt;
                            }


                        }
                        showMessage("Your bill amount is ","Rs."+Integer.toString(total));




                    }


                }
        );


    }

    private void Nextitem() {
        next_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equants = (EditText) findViewById(R.id.valquants);
                String sfname = autoTextView.getText().toString();
                int squants = Integer.parseInt(equants.getText().toString());

                Cursor res = myDb.newgetAllData(sfname);
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Nothing found", Toast.LENGTH_LONG).show();
                    return;
                }

                System.out.println("Im here");
                while (res.moveToNext()) {

                    eprice = res.getString(3);
                }
                sprice=Integer.parseInt(eprice);
                samt=sprice*squants;
                myDb.newinsertData(sfname,squants,sprice,samt);

                Intent nextIntent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(nextIntent);
            }
        });



    }

    public void Viewbill() {
        view_bt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        buffer.append("S.no\t\t");
                        buffer.append("Item \t\t");
                        buffer.append("Quantity \t");
                        buffer.append("Price\t");
                        buffer.append("Amount\t");
                        while (res.moveToNext()) {
                            buffer.append("\n"+res.getString(0)+"\t\t\t\t\t\t");
                            buffer.append(res.getString(1)+"\t\t\t\t\t\t\t");
                            buffer.append(res.getString(2)+"\t\t\t\t\t\t");
                            buffer.append(res.getString(3)+"\t\t\t\t\t\t");
                            buffer.append(res.getString(4)+"\n");

                        }
                        buffer.append("\n Bill Amount"+total);

                        // Show all data
                        showMessage("Epicure",buffer.toString());
                        smsmsg=buffer.toString();
                    }


                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}




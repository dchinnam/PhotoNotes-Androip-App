package com.example.deepank.photo_notes;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    TextView list;
    String titlevalue="";
    String value;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PhotoActivity.class));


            }
        });

        Bundle extras1 = getIntent().getExtras();
        if (extras1 != null) {
            value = extras1.getString("sentdata");
            titlevalue=extras1.getString("edittextstring");}
        else{



            dbHandler=new DBHandler(this,null,null,1);

            value = dbHandler.databsetoString();
            titlevalue = dbHandler.databsetoString1();
            if(value == "" || titlevalue == "" || value == null || titlevalue == null ){
                value="";
                titlevalue="";
                Toast.makeText(getApplicationContext(), "Take photo to add notes!", Toast.LENGTH_LONG).show();

            }
        }


        //creating a list view
//         String value1 = extras1.getString("sentdata");
        final  String[] ary = value.split("\n");
        final String[] titleary=titlevalue.split("\n");

//
//

        ListAdapter myAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titleary);
        ListView myListView= (ListView) findViewById(R.id.listView);
        myListView.setAdapter(myAdapter);

        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        String foodname = String.valueOf(parent.getItemAtPosition(position));

                        int index = -1;
                        for (int i=0;i<titleary.length;i++) {
                            if (titleary[i].equals(foodname)) {
                                index = i;
                                break;
                            }
                        }


                        // Intent sentdatatophotodetail = new Intent(getApplicationContext(),MainActivity.class);
                        // sentdatatophotodetail.putExtra("sentdatatophotodetail",foodname );

                        Toast.makeText(getApplicationContext(),ary[index] , Toast.LENGTH_LONG).show();
                        Intent  photoDetailIntent= new Intent(getApplicationContext(), photo_detail.class);
                        //  photoDetailIntent.putExtra("sentdatatophotodetail",foodname );
                        photoDetailIntent.putExtra("sentdatatophotodetail",ary[index] );
                        photoDetailIntent.putExtra("titlesentdatatophotodetail",foodname );
                        startActivity(photoDetailIntent);
                    }
                }
        );



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT ).show();
            return true;
        }

        if (id == R.id.action_uninstall) {
            Uri packageURI = Uri.parse("package:com.example.deepank.photo_notes");
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            startActivity(uninstallIntent);

            Toast.makeText(getApplicationContext(),"Uninstall",Toast.LENGTH_SHORT ).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

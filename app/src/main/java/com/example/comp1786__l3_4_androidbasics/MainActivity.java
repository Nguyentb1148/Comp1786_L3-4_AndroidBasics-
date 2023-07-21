package com.example.comp1786__l3_4_androidbasics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    Toolbar myToolBar;
    private String[] workStatus ={"Employed", "Unemployed"};
    Spinner spinner;
    TextView dobControl;
    //DatePicker Fragment inside MainActivity
    public  static  class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
    {
        @NonNull
        @Override
        public  Dialog onCreateDialog (@Nullable Bundle savedInstanceState){
            LocalDate d = null;
            int year = 0;
            int month =0;
            int day=0 ;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                d = LocalDate.now();
                year =d.getYear();
                month=d.getMonthValue();
                day =d.getDayOfMonth();
            }
            return new DatePickerDialog(getActivity(),this,year,--month,day);
        }
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day){
            LocalDate dob = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dob = LocalDate.of(year, ++month, day);
            }
            ((MainActivity)getActivity()).updateDOB(dob);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {//Create menu
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add date picker
        dobControl=findViewById(R.id.dob_control);
        dobControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment=new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(),"datePicker");
            }
        });
        // Get a reference from the toolbar
//        myToolBar = findViewById(R.id.toolbar);
//
//// Set toolbar as actionbar for the activity
//        setSupportActionBar(myToolBar);
        //Get reference to spinner
        spinner= findViewById(R.id.spinner);
        // Create an adapter
        ArrayAdapter<String> dataAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,workStatus);
        //Connect adepter to spinner
        spinner.setAdapter(dataAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.itemNext){
            getInputs();
            return true;
        }
        else if (item.getItemId() == R.id.itemExit){
            Toast.makeText(
                    getApplicationContext(),
                    "You asked to exit, but why not start another app?",
                    Toast.LENGTH_LONG
            ).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void updateDOB(LocalDate dob){
        TextView dobControl = findViewById(R.id.dob_control);
        dobControl.setText(dob.toString());
    }

    private void getInputs() {
    }
}
//public class MainActivity extends ListActivity {
//    private static final String[] WEEKDAYS = new String[]{
//            "Monday",
//            "Tuesday",
//            "Wednesday",
//            "Thursday",
//            "Friday",
//            "Saturday",
//            "Sunday"
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_main);
//        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, WEEKDAYS));
//        ListView lv = getListView();
//        lv.setTextFilterEnabled(true);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long ig) {
//                Toast.makeText(getApplicationContext(),
//                        ((TextView)view).getText(), Toast.LENGTH_SHORT
//                ).show();
//            }
//        });
//    }
//}
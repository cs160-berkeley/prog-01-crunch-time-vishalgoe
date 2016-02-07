package com.example.vishalgoel.calorieconversionapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    double num1,sum;
    EditText firstNumber;
    TextView addResult;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Pushup");
        categories.add("Situp");
        categories.add("Jumping Jacks");
        categories.add("Jogging");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        firstNumber = (EditText)findViewById(R.id.inputRepsOrMins);
        addResult = (TextView)findViewById(R.id.caloriesBurned);
        btnAdd = (Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = Double.parseDouble(firstNumber.getText().toString());
                String exercise = spinner.getSelectedItem().toString();
                if (exercise == "Pushup") {
                    sum = num1 * 100 / 350;
                } else if (exercise == "Situp") {
                    sum = num1 * 100 / 200;
                } else if (exercise == "Jumping Jacks") {
                    sum = num1 * 100 / 10;
                } else if (exercise == "Jogging") {
                    sum = num1 * 100 / 12;
                }
                addResult.setText("You burned " + Double.toString(sum) + " calories!");

                TextView exercises = (TextView) findViewById(R.id.exercises);
                exercises.setText(
                                "Exercise Equivalents: " + "\n" +
                                "Pushups: " + sum * 350 / 100 + " reps" + "\n" +
                                "Situps: " + sum * 200 / 100 + " reps" + "\n" +
                                "Jumping Jacks: " + sum * 10 / 100 + " mins" + "\n" +
                                "Jogging: " + sum * 12 / 100 + " mins");
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_reps:
                if (checked)
                    // Repetitions have been picked
                    break;
            case R.id.radio_mins:
                if (checked)
                    // Minutes have been picked
                    break;
        }
    }

    //@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

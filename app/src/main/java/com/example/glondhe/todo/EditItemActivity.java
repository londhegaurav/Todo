package com.example.glondhe.todo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditItemActivity extends AppCompatActivity {

    public String type = null;
    public String color = null;

    EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        String editKeyText = getIntent().getStringExtra("editKeyText");
        EditText editText = (EditText) findViewById(R.id.editTextId);
        editText.setText(editKeyText);
        editText.setSelection(editText.getText().length());

        editTextName = (EditText) findViewById(R.id.editTextId);
        editTextName.addTextChangedListener(textWatcher);
        changeTextUpdate();

    }

    public String datetoString() {

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePickerId);

        int day = datePicker.getDayOfMonth();
        int year = datePicker.getYear();
        int month = datePicker.getMonth();

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");
        //  String formatedDate = sdf.format(new Date(year, month, day));
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        String dateString = sdf.format(c.getTime());

        return dateString;
    }

    //TextWatcher
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            changeTextUpdate();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private void changeTextUpdate() {

        Button b = (Button) findViewById(R.id.edittTextButton);

        if (editTextName.getText().toString().trim().isEmpty())
            b.setEnabled(false);
        else
            b.setEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
        }
    };

    public void clickSubmitButton(View v) {

        int compareflag = 100;

        EditText editTextName = (EditText) findViewById(R.id.editTextId);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePickerId);

        if (editTextName.length() == 0 || editTextName.equals("") || editTextName == null) {
            Toast.makeText(EditItemActivity.this, "Please add Todo Name!!", Toast.LENGTH_LONG).show();
        }

        int day = datePicker.getDayOfMonth();
        int year = datePicker.getYear();
        int month = datePicker.getMonth();

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");
        //  String formatedDate = sdf.format(new Date(year, month, day));
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        String formatedDate = sdf.format(c.getTime());

        compareflag = c.compareTo(Calendar.getInstance());

        if (compareflag == 0) {
            type = "Due Today: ";
            color = "#8cc4df";
        } else if (compareflag == -1) {
            type = "Past Due: ";
            color = "#79bab6";
        } else if (compareflag == 1) {
            type = "Due on: ";
            color = "#7195a3";
        }

        Intent data = new Intent();

        Log.v("getIntent().getIntExtra(editKeyPosition", String.valueOf(getIntent().getIntExtra("editKeyPosition", 0)));
        data.putExtra("editKeyText", editTextName.getText().toString());
        data.putExtra("editKeyPosition", getIntent().getIntExtra("editKeyPosition", 0));
        data.putExtra("date", formatedDate);
        data.putExtra("type", type);
        data.putExtra("color", color);
        setResult(RESULT_OK, data);
        finish();
    }

}

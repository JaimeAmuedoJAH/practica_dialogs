package com.jah.practica_dialogs;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    String[] arrLanguages, arrCourses;
    boolean[] checkedLanguages;
    int[] checkedCourse;
    String str;
    ArrayAdapter<CharSequence> adapter;
    SwitchCompat switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initComponents();

        //button.setOnClickListener(view -> createDialogSingleChoice());
        //button.setOnClickListener(view -> dialogMultiChoice());
        button.setOnClickListener(view -> createDialogView());
        switch1.setOnCheckedChangeListener((compoundButton, b) -> lockOrientation());

        if(savedInstanceState != null){
            textView.setText(savedInstanceState.getString("textview"));
            arrLanguages = savedInstanceState.getStringArray("arrLanguages");
            arrCourses = savedInstanceState.getStringArray("arrCourses");
            checkedLanguages = savedInstanceState.getBooleanArray("checkedLanguages");
            checkedCourse = savedInstanceState.getIntArray("checkedCourse");
        }
    }

    private void lockOrientation() {
        if(switch1.isChecked()){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("textview", textView.getText().toString());
        outState.putStringArray("arrLanguages", arrLanguages);
        outState.putStringArray("arrCourses", arrCourses);
        outState.putBooleanArray("checkedLanguages", checkedLanguages);
        outState.putIntArray("checkedCourse", checkedCourse);
    }

    private void createDialogView() {
        final View custom_layout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        EditText txtName = custom_layout.findViewById(R.id.txtName);
        Spinner spinner = custom_layout.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.baseline_lightbulb_24)
                .setTitle(R.string.dialog_title)
                .setView(custom_layout)
                .setNeutralButton(R.string.dialog_neutral, (dialogInterface, i) -> textView.setText(""))
                .setNegativeButton(R.string.dialog_negative, null)
                .setPositiveButton(R.string.dialog_positive, (dialogInterface, i) -> textView.setText(
                        txtName.getText().toString() + " " + spinner.getSelectedItem().toString()))
                .create()
                .show();
    }

    /*private void dialogMultiChoice() {
        str = "";
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.baseline_lightbulb_24)
                .setTitle(R.string.dialog_title)
                .setMultiChoiceItems(arrLanguages, checkedLanguages, (dialogInterface, which, checked) -> checkedLanguages[which] = checked)
                .setNeutralButton(R.string.dialog_neutral, (dialogInterface, i) -> {
                    Arrays.fill(checkedLanguages, false);
                    str = "";
                    textView.setText("");
                })
                .setNegativeButton(R.string.dialog_negative, null)
                .setPositiveButton(R.string.dialog_positive, (dialogInterface, i) -> showCheckedItems())
                .create()
                .show();
    }

    private void showCheckedItems() {
        for(int ind = 0;ind < checkedLanguages.length;ind++){
            if(checkedLanguages[ind]){
                str += arrLanguages[ind] + "\n";
            }
        }
        textView.setText(str);
    }*/

    /*private void createDialogSingleChoice() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.baseline_lightbulb_24)
                .setTitle(R.string.dialog_title)
                .setSingleChoiceItems(arrCourses, checkedCourse[0], (dialogInterface, which) -> checkedCourse[0] = which)
                .setNeutralButton(R.string.dialog_neutral, (dialogInterface, i) -> {
                    textView.setText("");
                    checkedCourse[0] = -1;
                })
                .setNegativeButton(R.string.dialog_negative, null)
                .setPositiveButton(R.string.dialog_positive, (dialogInterface, i) -> textView.setText(arrCourses[checkedCourse[0]]))
                .create()
                .show();
    }*/

    private void initComponents() {
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        switch1 = findViewById(R.id.switch1);
        arrLanguages = new String[5];
        arrLanguages = getResources().getStringArray(R.array.langugaes);
        arrCourses = new String[3];
        arrCourses = getResources().getStringArray(R.array.Course);
        checkedLanguages = new boolean[5];
        Arrays.fill(checkedLanguages, false);
        checkedCourse = new int[]{-1};
        adapter = ArrayAdapter.createFromResource(this, R.array.langugaes, android.R.layout.simple_gallery_item);
    }
}
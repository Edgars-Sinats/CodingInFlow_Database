
package eu.alfo.rtu_pit.debuging;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import eu.alfo.rtu_pit.R;
import eu.alfo.rtu_pit.db.CustomAdapter;
import eu.alfo.rtu_pit.db.QuizDbHelper;

import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import eu.alfo.rtu_pit.db_rtu.DataGenerator;


public class DebugingActivity extends AppCompatActivity {
    private TextView textViewChooseTable;
    private Spinner spinnerChosenTable;
    private EditText editTextChosenNumber;
    private Button buttonCreateDbInsert;
    private Button buttonShowDb;
    private TextView textViewDebugInfo;
    private ListView listViewDbObject;

    QuizDbHelper dbHelper;
    ViewDbHandler viewDbHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        textViewChooseTable = findViewById(R.id.textViewChosenTable);
        spinnerChosenTable = findViewById(R.id.spinnerTableChoose);
        editTextChosenNumber = findViewById(R.id.textViewChosenNumber);
        buttonCreateDbInsert = findViewById(R.id.buttonCreateDbInserts);
        buttonShowDb = findViewById(R.id.buttonShowDb);

        textViewDebugInfo = findViewById(R.id.textViewDebugInfo);
        listViewDbObject = findViewById(R.id.listViewCreatedObject);

        this.dbHelper = QuizDbHelper.getInstance(this);
        this.viewDbHandler = new ViewDbHandler(this.dbHelper);

        String[] mas = DataGenerator.getAviableTables();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mas);
        spinnerChosenTable.setAdapter(adapter);

        buttonCreateDbInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chosenTable = adapter.getItem(spinnerChosenTable.getSelectedItemPosition());

                textViewDebugInfo.setText(chosenTable);
                createNewInserts(chosenTable);

            }
        });


        buttonShowDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //@ToDo Show List

                String chosenTable = adapter.getItem(spinnerChosenTable.getSelectedItemPosition());
                List<ArrayList<String>> rows = viewDbHandler.getOutputList(chosenTable);
                String[] columnNames = viewDbHandler.columnNames;

                CustomAdapter customAdapter = new CustomAdapter(v.getContext(), rows, columnNames);
                listViewDbObject.setAdapter(customAdapter); //ToDo is this working?
            }
        });
    }


    private void createNewInserts(String table){
        DataGenerator dataGenerator = new DataGenerator(this.dbHelper);

        switch (table){
            case "Teacher":
                dataGenerator.genTeachers(5);
                customDialog("Notification", "Teachers has been created");
                break;

            case "News":
                textViewDebugInfo.setText("News has been created");
                dataGenerator.genNews(5,2,5);
                customDialog("News", "News has been created");
                break;

            case "Subject":
                dataGenerator.genSubjects(5);
                customDialog("Subject", "Subject has been created");
                break;
            case "Something":
                dataGenerator.genTeachers(5);
                customDialog("sd", "s");
            default:
                textViewDebugInfo.setText((CharSequence) textViewDebugInfo + " Tabula nav izveidota");
        }
    }

    private void customDialog(String title, String message){
        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.ic_notification);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);
        builderSingle.show();
    }
}

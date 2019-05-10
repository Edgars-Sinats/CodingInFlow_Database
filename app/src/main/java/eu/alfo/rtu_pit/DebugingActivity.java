
package eu.alfo.rtu_pit;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import eu.alfo.rtu_pit.db_rtu.DataGenerator;

public class DebugingActivity extends AppCompatActivity {
    private TextView textViewChosenTable;
    private Spinner spinnerChosenTable;
    private EditText editTextChosenNumber;
    private Button buttonCreateDbInsert;
    private TextView textViewDebugInfo;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        textViewChosenTable = findViewById(R.id.textViewChosenTable);
        spinnerChosenTable = findViewById(R.id.spinnerTableChoose);
        editTextChosenNumber = findViewById(R.id.textViewChosenNumber);
        buttonCreateDbInsert = findViewById(R.id.buttonCreateDbInserts);
        textViewDebugInfo = findViewById(R.id.textViewDebugInfo);

        String[] mas = DataGenerator.getAviableTables();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mas);
        spinnerChosenTable.setAdapter(adapter);


        buttonCreateDbInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chosenTable = adapter.getItem(spinnerChosenTable.getSelectedItemPosition());
//                textViewDebugInfo.setText(chosenTable);
                createNewInserts(chosenTable);
            }
        });

    }


    private void createNewInserts(String table){
        switch (table){
            case "Teacher":
                DataGenerator.getInstance().genTeachers(5);

                break;

            case "News":
                DataGenerator.getInstance().genNews(5,4,3);
                break;

            case "Subject":
                DataGenerator.getInstance().genSubjects(5);
                break;
        }
    }
}

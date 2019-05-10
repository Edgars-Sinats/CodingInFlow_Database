package eu.alfo.rtu_pit.rtu_teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import eu.alfo.rtu_pit.R;

import static java.lang.String.valueOf;

public class RTU_TeacherActivity extends AppCompatActivity{

    private Spinner spinnerDocuments1;
    private Spinner spinnerTest1;
    private Button buttonCalendar;
    private TextView textViewTeachTest;
    private TextView textViewTeachDoc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rtu_teacher_activity);

        spinnerDocuments1 = findViewById(R.id.spinnerDocuments);
        spinnerTest1 = findViewById(R.id.spinnerTest);
        buttonCalendar = findViewById(R.id.buttonCalendar);
        textViewTeachDoc = findViewById(R.id.textViewDocTeach);
        textViewTeachTest = findViewById(R.id.textViewTestTeach);

        spinnerTest1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textViewTeachTest.setText(String.valueOf(spinnerTest1.getSelectedItemId()));
                textViewTeachDoc.setText(String.valueOf(spinnerDocuments1.getSelectedItemId()));
                String a = (String.valueOf(spinnerTest1.getSelectedItemId()));

                if(a.equals("1"))
                    startActivity(new Intent(RTU_TeacherActivity.this, NewQuestionActivity.class));
                if(a.equals("2"))
                    startActivity(new Intent(view.getContext(), RTU_TeacherEditTestActivity.class));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerDocuments1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textViewTeachTest.setText(String.valueOf(spinnerTest1.getSelectedItemId()));
                textViewTeachDoc.setText(String.valueOf(spinnerDocuments1.getSelectedItemId()));
                String a = (String.valueOf(spinnerDocuments1.getSelectedItemId()));

                if(a.equals("1"))
                    startActivity(new Intent(RTU_TeacherActivity.this, RTU_TeacherAddMaterialActivity.class));
                if(a.equals("2"))
                    startActivity(new Intent(view.getContext(), RTU_TeacherEditMaterialActivity.class));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




//
//        spinnerDocuments1.setOnItemClickListener((parent, view, position, id) -> {
//            String a = (String.valueOf(spinnerDocuments1.getSelectedItemId()));
//            if(a.equals("IZVEIDOT")) {
//                startActivity(new Intent(RTU_TeacherActivity.this, NewQuestionActivity.class));
//            }else if (a.equals("LABOT")){
//                startActivity(new Intent(view.getContext(), RTU_TeacherEditTestActivity.class));
//            }
//        });



//        spinnerTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RTU_TeacherActivity.this, RTU_TeacherTestActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        spinnerDocuments.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RTU_TeacherActivity.this, RTU_TeacherMaterialActivity.class);
//                startActivity(intent);
//            }
//        });

    }

//    private void loadResOption(){
//        String[] masTest = getResources().getStringArray(R.array.Test);
//        String[] masDoc = getResources().getStringArray(R.array.Material);
//
//        ArrayAdapter<String> adapterTest = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, masTest);
//        ArrayAdapter<String> adapterDoc = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, masDoc);
//        adapterTest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        adapterDoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinnerTest.setAdapter(adapterTest);
//        spinnerDocuments.setAdapter(adapterDoc);
//    }

//    protected void startAct(){
//        int a = Integer.parseInt(String.valueOf(spinnerTest.getSelectedItemId()));
//        int b = Integer.parseInt(String.valueOf(spinnerDocuments.getSelectedItemId()));
//        String.valueOf(spinnerTest.onTouchEvent())
//    }
}

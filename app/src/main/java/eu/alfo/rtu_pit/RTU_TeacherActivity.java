package eu.alfo.rtu_pit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RTU_TeacherActivity extends AppCompatActivity{

    private Button buttonDocuments;
    private Button buttonTest;
    private Button buttonCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rtu_teacher_activity);

        buttonDocuments = findViewById(R.id.buttonDocuments);
        buttonTest = findViewById(R.id.buttonTest);
        buttonCalendar = findViewById(R.id.buttonCalendar);


        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RTU_TeacherActivity.this, RTU_TeacherTestActivity.class);
                startActivity(intent);
            }
        });

        buttonDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RTU_TeacherActivity.this, RTU_TeacherMaterialActivity.class);
                startActivity(intent);
            }
        });

    }
}

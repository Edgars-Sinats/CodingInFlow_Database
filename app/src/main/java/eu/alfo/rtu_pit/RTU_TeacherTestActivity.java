package eu.alfo.rtu_pit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RTU_TeacherTestActivity extends AppCompatActivity {

    private Button buttonMakeTest;
    private Button buttonEditTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rtu_teacher_test_activity);

        buttonEditTest = findViewById(R.id.buttonEditTest);
        buttonMakeTest= findViewById(R.id.buttonMakeTest);

        buttonMakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RTU_TeacherTestActivity.this, NewQuestionFragment.class);
                startActivity(intent);
            }
        });

        buttonEditTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RTU_TeacherTestActivity.this, RTU_TeacherEditTestActivity.class);
                startActivity(intent);
            }
        });
    }



//    Intent intent = new Intent(RTU_TeacherTestActivity.this, );
}

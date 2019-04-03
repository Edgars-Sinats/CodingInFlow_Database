package eu.alfo.rtu_pit.rtu_teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.List;

import eu.alfo.rtu_pit.R;
import eu.alfo.rtu_pit.db.Category;
import eu.alfo.rtu_pit.db.QuizDbHelper;

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
                Intent intent = new Intent(RTU_TeacherTestActivity.this, NewQuestionActivity.class);
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

    private void loadCategories(){
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }



//    Intent intent = new Intent(RTU_TeacherTestActivity.this, );
}

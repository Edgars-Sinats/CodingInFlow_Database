package eu.alfo.rtu_pit.rtu_teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import eu.alfo.rtu_pit.R;

public class RTU_TeacherMaterialActivity extends AppCompatActivity {

    private Button buttonAddMaterial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rtu_teacher_material_activity);

        buttonAddMaterial = findViewById(R.id.buttonAddMaterial);

        buttonAddMaterial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RTU_TeacherMaterialActivity.this, RTU_TeacherAddMaterialActivity.class);
                startActivity(intent);
            }
        });

    }
}

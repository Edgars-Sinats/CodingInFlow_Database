package eu.alfo.rtu_pit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import eu.alfo.rtu_pit.rtu_student.StudentActivity;
import eu.alfo.rtu_pit.rtu_teacher.GroupHeadActivity;
import eu.alfo.rtu_pit.rtu_teacher.RTU_TeacherActivity;

public class LoginActivity extends AppCompatActivity{

    private EditText editTextPassword;
    private EditText editTextUName;
    private Button buttonLogIn;
    private TextView textViewTest;
    private TextView textViewTes2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rtu_login);

        editTextUName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogIn = findViewById(R.id.buttonLogIn);
        textViewTest = findViewById(R.id.textViewTest);
        textViewTes2 = findViewById(R.id.textViewTest2);

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = getIntent();

                textViewTest.setText("Doing My Job :) ");
                logIn(intent3);
            }
        });
    }



    public void logIn(Intent intent){

        //case whit
        //      if(one == 1)
        //need to replace whit .equals()
        String username = editTextUName.getText().toString().toLowerCase();
        switch (username){
            case "s":
                if(editTextPassword.getText().toString().equals("s")){
                    textViewTest.setText("Student");
                    Intent intent1 = new Intent(this, StudentActivity.class);
                    intent1.putExtra("user", "student");
                    startActivityForResult(intent1,1);

//                    textViewTes2.setText(getText(Integer.parseInt(editTextUName.toString())));   NOT WORKING!? WHY??
                }else {
                    customDialog("Kļūda autentifikācijā","Ievadītā parole studentam ir nepareiza", "cancelMethod1","okMethod1");
                        }
                break;

            case "t":
                if(editTextPassword.getText().toString().equals("t")){
                    textViewTest.setText("Teacher");
                    Intent intent1 = new Intent(this, GroupHeadActivity.class);
                    intent1.putExtra("user", "teacher");
                    startActivityForResult(intent1,1);

                }else {
                    customDialog("Kļūda autentifikācijā","Ievadītā parole pasniedzējam ir nepareiza", "cancelMethod1","okMethod1");
                }
                break;

            case "admin":
                if(editTextPassword.getText().toString().equals("admin")){
                    textViewTest.setText("Admin");
                    Intent intent1 = new Intent(this, GroupHeadActivity.class);
                    intent1.putExtra("user", "admin");
                    startActivityForResult(intent1,1);
                    customDialog("Try Again :/","This is not meant for you :)", "cancelMethod1","okMethod1");
                }else {
                    customDialog("Dialog Box 1","This is Dialog Box 1", "cancelMethod1","okMethod1");

                }
                break;

            case "1":
                if(editTextPassword.getText().toString().equals("1")||editTextPassword.getText().toString().equals("9")||editTextPassword.getText().toString().equals("0")){
                    textViewTest.setText("Number ..?");
                }else {
                    customDialog("Crypto Kļūda autentifikācijā","Blokchain nestrādā ;/", "cancelMethod1","okMethod1");
                }
                break;

            case "a":
                if(editTextPassword.getText().toString().equals("A")){
                    textViewTest.setText("Big A");
                }else {
                    customDialog("Kļūda autentifikācijā","Ievadītā parole ir nepareiza", "cancelMethod1","okMethod1");
                }
                break;

            case "b":
                    textViewTest.setText("Small b");
                    customDialog("Mana autentifikācijā","Ievadītais lietotājvārds ir b", "cancelMethod1","okMethod1");
                break;

            case "q":
                Log.i("MyTag", "You did this");
                Intent intent1 = new Intent(this, QuizActivity.class);
                intent1.putExtra("ChoseZ", "Option_non");
                startActivityForResult(intent1,1);
//                this.startActivity(intent1);


//                buttonLogIn.setBackgroundColor(Color.parseColor("R.color.colorAccent"));
//                textViewTest.setText("Great Z");
                break;

            case "maris":
                if(editTextPassword.getText().toString().equals("kartons") ||
                        editTextPassword.getText().toString().equals("cepums")||
                        editTextPassword.getText().toString().equals("merija")||
                        editTextPassword.getText().toString().equals("teja")||
                        editTextPassword.getText().toString().equals("zalais")){
                    textViewTest.setText("Big Maris");
                    customDialog("Kļūda DIENAS Kārtībā","Plānojams jauns trips uz MEŽAPARKU!", "cancelMethod2","okMethod2");
                }else {
                    customDialog("Kļūda autentifikācijā","Ievadītā parole ir nepareiza", "cancelMethod2","okMethod2");
                }
                break;

            case "edgars":
                if(editTextPassword.getText().toString().equals("ALFO")){
                    textViewTest.setText("Cietais rieksts");
                }else {
                    customDialog("Visias privilēģijas atļautas","You own a master key!!!", "cancelMethod2","okMethod2");
                }
                break;

            default:
                customDialog("Kļūda autentifikācijā","Ievadītais lietotājvārds ir nepareiz pamēģiniet ievadīt \n citu kodu\n Ieteiktie kodi: t/s", "cancelMethod2","okMethod2");
                break;
        }
    }

    private void cancelMethod1(){
        Log.d("TAG", "cancelMethod1: Called.");
        toastMessage("Cancel Method1.");
    }
    private void cancelMethod2(){
        Log.d("TAG", "cancelMethod2: Called.");
        toastMessage("Cancel ULTRA BOOST.");
    }
    private void okMethod1(){
        Log.d("TAG", "okMethod1: Called.");
        toastMessage("OK Method1.");
    }
    private void okMethod2(){
        Log.d("TAG", "okMethod2: Called.");
        toastMessage("OK Method2 ULTRA.");
    }


    /**
     * Custom alert dialog that will execute method in the class
     * @param title
     * @param message
     * @param cancelMethod
     * @param okMethod
     */
    public void customDialog(String title, String message, final String cancelMethod, final String okMethod){
        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.ic_notification);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("TAG", "onClick: Cancel Called.");
                        if(cancelMethod.equals("cancelMethod1")){
                            cancelMethod1();
                        }
                        else if(cancelMethod.equals("cancelMethod2")){
                            cancelMethod2();
                        }

                    }
                });

        builderSingle.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("TAG", "onClick: OK Called.");

                        if(okMethod.equals("okMethod1")){
                            okMethod1();
                        }
                        else if(okMethod.equals("okMethod2")){
                            okMethod2();
                        }
                    }
                });


        builderSingle.show();
    }
    public void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }




}

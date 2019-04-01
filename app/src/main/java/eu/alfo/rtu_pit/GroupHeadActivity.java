package eu.alfo.rtu_pit;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class GroupHeadActivity extends AppCompatActivity{

    QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);


    //    private Spinner spinnerNumberOfQuestion;
    private Spinner spinnerAnswered;
    private Spinner spinnerCategory;

    private Button buttonMakeTest;
    private Button makeYourOwnQuest;

    private Button saveNewQuestion;

    //Question row block
    private ListView simpleList;
    int flags = R.drawable.add_circle;
    private String countryList= "Jautājuma teksts";
//    , "China", "australia", "Portugle", "America", "NewZealand"


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grop_head);

        makeYourOwnQuest = findViewById(R.id.buttonMakeQuestion);
        spinnerCategory = findViewById(R.id.spinner_category2);
        spinnerAnswered = findViewById(R.id.spinner_Answered);
        buttonMakeTest = findViewById(R.id.buttonMakeTest);


        //
        simpleList = (ListView) findViewById(R.id.listViewOneObject);

        loadCategories();
        loadAnsweredType();

        buttonMakeTest.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v){
              Intent intent = new Intent(GroupHeadActivity.this, StartingScreenActivity.class);
              startActivity(intent);
          }
        });

//

          ArrayList < String > theList = new ArrayList<>();
//        Cursor data = dbHelper.getQuestions(1);

        /*

        I need to build "getAllQuestions for scrollable listView"


         */
        //Dropdown


//        ListAdapter listAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item_1, theList)

//Flags??
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), countryList, flags);
        simpleList.setAdapter(customAdapter);



        //List for Scrolable ListView
    }
    private void loadCategories() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategories);
    }


    private void loadAnsweredType(){

        String[] mas = getResources().getStringArray(R.array.Answered);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, mas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnswered.setAdapter(adapter);
    }

    //    private void LoadMumberOfQuestions(){
//        String[] mas = getResources().getStringArray(R.array.numberOfQuestion);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, mas);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerNumberOfQuestion.setAdapter(adapter);
//    }

    public void showAccount(){
        AlertDialog.Builder signeNewQuestion = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View account_fill = inflater.inflate(R.layout.fragment_new_question, null);

        signeNewQuestion.setView(account_fill);
        signeNewQuestion.show();

        saveNewQuestion = findViewById(R.id.buttonCreateNewQuestion);

        saveNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction().remove(this).commit();
//                getSupportFragmentManager().beginTransaction().addToBackStack("A").add(R.id.fragment_container, NewQuestionFragment.class).commit();
            }
        });
    }

    public void showText(){

    }


}

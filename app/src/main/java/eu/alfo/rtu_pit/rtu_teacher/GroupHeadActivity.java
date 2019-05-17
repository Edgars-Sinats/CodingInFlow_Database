package eu.alfo.rtu_pit.rtu_teacher;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

import eu.alfo.rtu_pit.db.Category;
import eu.alfo.rtu_pit.db.CustomAdapter;
import eu.alfo.rtu_pit.db.QuizDbHelper;
import eu.alfo.rtu_pit.R;
import eu.alfo.rtu_pit.StartingScreenActivity;

public class GroupHeadActivity extends AppCompatActivity{

//    QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);


    //    private Spinner spinnerNumberOfQuestion;
    private Spinner spinnerAnswered;
    private Spinner spinnerCategory;
    private Spinner spinnerDocuments;
    private Spinner spinnerTest;

    private Button buttonMakeTest;

    private ImageButton imageSearch;
    private ImageButton imageWrite;
    private Button saveNewQuestion;
    private ViewSwitcher switcher;
    private EditText editTextSearchedQ;
    private Button buttonMakeQ;




    //Question row block
    private ListView simpleList;
    int flags = R.drawable.add_circle;
    private String countryList= "Jautājuma teksts";
//    , "China", "australia", "Portugle", "America", "NewZealand"


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grop_head);

        spinnerCategory = findViewById(R.id.spinner_category2);
        spinnerAnswered = findViewById(R.id.spinner_Answered);
        spinnerDocuments = findViewById(R.id.spinnerDocuments);
        spinnerTest = findViewById(R.id.spinnerTest);
        buttonMakeTest = findViewById(R.id.buttonMakeTest);
        simpleList = findViewById(R.id.listViewOneObject);
        switcher = findViewById(R.id.my_switcher);
        editTextSearchedQ = switcher.findViewById(R.id.editTextSearchedQuestion);
        buttonMakeQ = switcher.findViewById(R.id.buttonMakeQuestion);
        imageSearch = findViewById(R.id.imageSearch);
        imageWrite = findViewById(R.id.imageWrite);

        loadCategories();
        loadAnsweredType();

        buttonMakeTest.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v){
              Intent intent = new Intent(GroupHeadActivity.this, StartingScreenActivity.class);
              startActivity(intent);
          }
        });

        spinnerTest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinerTestItemID = (String.valueOf(spinnerTest.getSelectedItemId()));

                if(spinerTestItemID.equals("1"))
                    startActivity(new Intent(GroupHeadActivity.this, NewQuestionActivity.class));
                if(spinerTestItemID.equals("2"))
                    startActivity(new Intent(GroupHeadActivity.this, RTU_TeacherEditTestActivity.class));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerDocuments.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerDocumentItemID = (String.valueOf(spinnerDocuments.getSelectedItemId()));

                if(spinnerDocumentItemID.equals("1"))
                    startActivity(new Intent(GroupHeadActivity.this, RTU_TeacherAddMaterialActivity.class));
                if(spinnerDocumentItemID.equals("2"))
                    startActivity(new Intent(GroupHeadActivity.this, RTU_TeacherEditMaterialActivity.class));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        imageWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeImageClicked();
            }
        });

        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchImageClicked();
            }
        });

        ArrayList < String > theList = new ArrayList<>();
        /*
        I need to build "getAllQuestions for scrollable listView"
         */
        //Dropdown
//        ListAdapter listAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item_1, theList)

        //@Todo make new adapter
//        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), countryList, flags);
//        simpleList.setAdapter(customAdapter);
        //List for Scrollable ListView
    }


    public void searchImageClicked(){
        switcher.showNext();
    }

    public void writeImageClicked(){
        switcher.showNext();
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

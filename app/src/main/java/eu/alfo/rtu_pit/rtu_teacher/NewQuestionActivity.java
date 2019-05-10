package eu.alfo.rtu_pit.rtu_teacher;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import eu.alfo.rtu_pit.R;
import eu.alfo.rtu_pit.db.Category;
import eu.alfo.rtu_pit.db.Question;
import eu.alfo.rtu_pit.db.QuizDbHelper;

public class NewQuestionActivity extends AppCompatActivity {
    private EditText editTextinsertQuestion;
    private EditText editTextAnswer1;
    private EditText editTextAnswer2;
    private EditText editTextAnswer3;
    private EditText editTextAnswer4;
    private RadioButton radioB_AddQ1;
    private RadioButton radioB_AddQ2;
    private RadioButton radioB_AddQ3;
    private RadioButton radioB_AddQ4;
    private Spinner spinnerAddNewTopic;
    private TextView textViewSaveQList;
    private ImageView imageViewSaveQList;
    private Button buttonMake;

    private int ansNr;
    private int selectedCategoryId;
    private List<Category> categories;
    private RadioGroup rbGroup;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_question);

        editTextinsertQuestion= findViewById(R.id.editTextNewQuest);
        editTextAnswer1 = findViewById(R.id.editTextNewAns1);
        editTextAnswer2 = findViewById(R.id.editTextNewAns2);
        editTextAnswer3 = findViewById(R.id.editTextNewAns3);
        editTextAnswer4 = findViewById(R.id.editTextNewAns4);
        radioB_AddQ1 = findViewById(R.id.RadioB_AcceptAns1);
        radioB_AddQ2 = findViewById(R.id.RadioB_AcceptAns2);
        radioB_AddQ3 = findViewById(R.id.RadioB_AcceptAns3);
        radioB_AddQ4 = findViewById(R.id.RadioB_AcceptAns4);
//        textViewNewTopic = findViewById(R.id.textViewNewTopic);
        spinnerAddNewTopic = findViewById(R.id.spinnerNewTopic);
        textViewSaveQList = findViewById(R.id.textViewQ_AddToMyList);
        imageViewSaveQList = findViewById(R.id.imageViewQ_AddToMyList);
        buttonMake = findViewById(R.id.buttonCreateNewQuestion);
        rbGroup = findViewById(R.id.radio_group_Add);

        radioB_AddQ1.setEnabled(false);
        radioB_AddQ2.setEnabled(false);
        radioB_AddQ3.setEnabled(false);
        radioB_AddQ4.setEnabled(false);
        imageViewSaveQList.setImageResource(R.drawable.add_circle);


        loadCategories();
        loadListeners();

        //Fragment NewTopic
        // onClick_Fragment
        //*
        // Create fragment dialog where to create new Topic
        // - You add key so it can be seen public/private
        // -
        // *You can create only 3 new Topic (Regular Account // VIP Account infinitive(1000))
        // *//
//        imageViewSaveQList.setOnClickListener(new);

    }

    private void loadCategories() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);

        categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);

        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddNewTopic.setAdapter(adapterCategories);

        selectedCategoryId = ((Category) spinnerAddNewTopic.getSelectedItem()).getId();

    }

    private void checkQuestion(){
        String question = editTextinsertQuestion.getText().toString();
        String answrer_1 = editTextAnswer1.getText().toString();
        String answrer_2 = editTextAnswer2.getText().toString();
        String answrer_3 = editTextAnswer3.getText().toString();
        String answrer_4 = editTextAnswer4.getText().toString();
        int radiosChecked = (radioB_AddQ1.isChecked() ? 1 : 0) +  (radioB_AddQ2.isChecked() ? 1 : 0) + (radioB_AddQ3.isChecked() ? 1 : 0) +  (radioB_AddQ4.isChecked() ? 1 : 0);

        if(editTextinsertQuestion.getText().toString().isEmpty() ||
                answrer_1.isEmpty() || answrer_2.isEmpty()){
            customDialog("Error", "Please put Question and at least first two(2) answers");

        }else if((ansNr == 3 && answrer_3.isEmpty()) ||
                (ansNr == 4 && answrer_4.isEmpty())) {

            customDialog("Error", "If you put Answer number, don`t miss a answer field empty");

        }else if((answrer_3.isEmpty()) && !answrer_4.isEmpty()){
            customDialog("Error", "You have to put answer 3. before answer 4.");
        }else if(radiosChecked == 0){
            customDialog("Error", "At least one answer should be chosen.(Right side circle :)");
        }else{
            createQuestion(question, answrer_1, answrer_2, answrer_3, answrer_4);
            editTextinsertQuestion.setText("");
            editTextAnswer1.setText("");
            editTextAnswer2.setText("");
            editTextAnswer3.setText("");
            editTextAnswer4.setText("");
            radioB_AddQ1.setChecked(false);
            radioB_AddQ2.setChecked(false);
            radioB_AddQ3.setChecked(false);
            radioB_AddQ4.setChecked(false);

        }
    }



    private void loadListeners(){
        buttonMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
                ansNr = rbGroup.indexOfChild(rbSelected) +1;

                checkQuestion();
            }
        });

        spinnerAddNewTopic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long listID) {
                Category c = (Category) spinnerAddNewTopic.getSelectedItem();
                selectedCategoryId = c.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



        TextWatcher correctAnswerRadioWatcher = new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String answrer_1 = editTextAnswer1.getText().toString();
                String answrer_2 = editTextAnswer2.getText().toString();
                String answrer_3 = editTextAnswer3.getText().toString();
                String answrer_4 = editTextAnswer4.getText().toString();

                radioB_AddQ1.setEnabled(!answrer_1.isEmpty());
                radioB_AddQ2.setEnabled(!answrer_2.isEmpty());
                radioB_AddQ3.setEnabled(!answrer_3.isEmpty());
                radioB_AddQ4.setEnabled(!answrer_4.isEmpty());
                radioB_AddQ1.setClickable(true);
                radioB_AddQ2.setClickable(true);
                radioB_AddQ3.setClickable(true);
                radioB_AddQ4.setClickable(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };

        editTextAnswer1.addTextChangedListener(correctAnswerRadioWatcher);
        editTextAnswer2.addTextChangedListener(correctAnswerRadioWatcher);
        editTextAnswer3.addTextChangedListener(correctAnswerRadioWatcher);
        editTextAnswer4.addTextChangedListener(correctAnswerRadioWatcher);

    }


    private void createQuestion(String q, String  a1, String a2, String a3, String a4){
        Question q7 = new Question(q, a1, a2, a3, a4, ansNr, selectedCategoryId,0,0);

        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        dbHelper.addQuestion(q7);
        customDialog("Notification", "You are successfully added new question: \n" + q + "\n Answer number: " + ansNr);

    }

    private void customDialog(String title, String message){
        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.ic_notification);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.show();
    }


    //Add to questionLibrary?
//    public void showAccount(){
//        AlertDialog.Builder signeNewQuestion = new AlertDialog.Builder(getActivity());
//
//        signeNewQuestion.setTitle((CharSequence) signeNewQuestion.setTitle(R.string.app_name));
//
//        LayoutInflater inflater = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            inflater = this.getLayoutInflater();
//        }
//        View account_fill = inflater.inflate(R.layout.fragment_new_question, null);
//
//        signeNewQuestion.setView(account_fill);
//        signeNewQuestion.show();
//    }

}

package eu.alfo.rtu_pit.rtu_teacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
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
    private Switch switchAddQ1;
    private Switch switchAddQ2;
    private Switch switchAddQ3;
    private Switch switchAddQ4;
    private TextView textViewNewTopic;
    private Spinner spinnerNewTopic;
    private TextView textViewSaveQList;
    private ImageView imageViewSaveQList;
    private Button buttonMake;

    private String q;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String ansNr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_question);

        editTextinsertQuestion.findViewById(R.id.editTextQuest);
        editTextAnswer1.findViewById(R.id.editTextAns1);
        editTextAnswer2.findViewById(R.id.editTextAns2);
        editTextAnswer3.findViewById(R.id.editTextAns3);
        editTextAnswer4.findViewById(R.id.editTextAns4);
        switchAddQ1.findViewById(R.id.switchAcceptAns1);
        switchAddQ2.findViewById(R.id.switchAcceptAns2);
        switchAddQ3.findViewById(R.id.switchAcceptAns3);
        switchAddQ4.findViewById(R.id.switchAcceptAns4);
        textViewNewTopic.findViewById(R.id.textViewNewTopic);
        spinnerNewTopic.findViewById(R.id.spinnerNewTopic);
        textViewSaveQList.findViewById(R.id.textViewQ_AddToMyList);
        imageViewSaveQList.findViewById(R.id.imageViewQ_AddToMyList);
        buttonMake.findViewById(R.id.buttonCreateNewQuestion);

        q = String.valueOf(editTextinsertQuestion);
        a1 = String.valueOf(editTextAnswer1);
        a2 = String.valueOf(editTextAnswer2);
        a3 = String.valueOf(editTextAnswer3);
        a4 = String.valueOf(editTextAnswer4);
//        ansNr =

        loadCategories();
        checkQuestion();


    }

    private void loadCategories() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);

        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNewTopic.setAdapter(adapterCategories);
    }


    private void checkQuestion(){
        if(editTextinsertQuestion.toString().isEmpty() &&
                editTextAnswer1.toString().isEmpty() && editTextAnswer2.toString().isEmpty()){
            customDialog("Error", "Please put Question and at least two(2) answers");
        }else {


            createQuestion();
        }
    }

    private void greenSwitch(){
        switchAddQ1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switchAddQ2.setChecked(false);
                    switchAddQ3.setChecked(false);
                }else{

                }
            }
        });

        if (switchAddQ1.isChecked()){


        }
    }

    private void checkQ_AnswerButton(){

    }

    private void createQuestion(){

        Question q1 = new Question(q, a1, a2, a3, a4, 0, 0,0,0);

        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        dbHelper.addQuestion(q1);

    }

    private void customDialog(String title, String message){
        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(this);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);
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

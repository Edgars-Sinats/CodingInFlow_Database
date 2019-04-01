package eu.alfo.rtu_pit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class StartingScreenActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_NUMOFQUEST = "extraNumOfQuest";
//    public static final String EXTRA_DIFFICULTY = "extraDifficulty";
    public static final String EXTRA_TIMES = "extraTimes";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;
    private Spinner spinnerCategory;
    private Spinner spinnerNumberOfQuestion;
    private Spinner spinnerAnswered;

    private Button buttonGroupManager;
    private Button buttonLogIn;

    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        textViewHighscore = findViewById(R.id.text_view_highscore);
        spinnerCategory = findViewById(R.id.spinner_category2);
//        spinnerDifficulty = findViewById(R.id.spinner_category2);
        spinnerNumberOfQuestion = findViewById(R.id.spinner_NumberOfQuestion);
        buttonGroupManager = findViewById(R.id.buttonGroupHead);
        spinnerAnswered = findViewById(R.id.spinner_Answered);

        buttonLogIn = findViewById(R.id.buttonLogIn);

        loadCategories();
//        loadDifficultyLevels();
        loadHighscore();
        LoadMumberOfQuestions();
        loadAnsweredType();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        buttonGroupManager.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartingScreenActivity.this, GroupHeadActivity.class);
                startActivity(intent);
            }
        });

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartingScreenActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        //Dropdown


    }

    private void LoadMumberOfQuestions(){
        String[] mas = getResources().getStringArray(R.array.numberOfQuestion);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumberOfQuestion.setAdapter(adapter);
    }

    private void startQuiz() {
        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();

        int categoryID = selectedCategory.getId();
        String categoryName = selectedCategory.getName();
//        String difficulty = spinnerDifficulty.getSelectedItem().toString();

        Intent intent = new Intent(StartingScreenActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        intent.putExtra(EXTRA_NUMOFQUEST, Integer.valueOf(spinnerNumberOfQuestion.getSelectedItem().toString()));
//        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
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

//    Answered times(Atbildētās  reizes);
//    private void loadDifficultyLevels() {
//        String[] difficultyLevels = Question.getAllTopicLevels();
//
//        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, difficultyLevels);
//        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerDifficulty.setAdapter(adapterDifficulty);
//    }

    private void loadAnsweredTimes() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
//        int trueTimes = Question.getTrueTimes();
        String[] difficultyLevels = Question.getAllTopicLevels();

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerDifficulty.setAdapter(adapterDifficulty);
    }



    private void loadNumberOfQuestion(){
//        List<Integer> numberOfQuestion
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText(highscore + " OF " + "X" );
    }

    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText(highscore + " OF " + "X");

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}
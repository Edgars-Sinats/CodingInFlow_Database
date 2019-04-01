package eu.alfo.rtu_pit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import eu.alfo.rtu_pit.QuizContract.*;


import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HUNTTEST.db"; //Change name if you want to test
    private static final int DATABASE_VERSION = 7; //increase(aTimes-12)(7)

    private static QuizDbHelper instance;

    private SQLiteDatabase db ;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_TOPIC_ID + " INTEGER, " +
                QuestionsTable.COLUMN_ANSWERED_TIMES + " INTEGER, " +
                QuestionsTable.COLUMN_TRUE_TIMES + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_TOPIC_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }



    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        addCategory(c1);
        Category c2 = new Category("Geography");
        addCategory(c2);
        Category c3 = new Category("Math");
        addCategory(c3);
        Category c4 = new Category("M4");
        addCategory(c4);
        Category c5 = new Category("M5");
        addCategory(c5);
        Category c6 = new Category("M6");
        addCategory(c6);
        Category c7 = new Category("M7");
        addCategory(c7);
        Category c8 = new Category("M8. very long category for you to think about `cause this is problem i guess");
        addCategory(c8);
//        Category c9 = new Category("M9");
//        addCategory(c9);



    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
//        Question q1 = new Question("Programming, Easy: A is correct",
//                "A", "B", "C", 1,
//                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        Question q1 = new Question("Programming, Easy: A is correct",
                "A", "B", "C", "S", 1,
                Category.PROGRAMMING, 0,0);
        addQuestion(q1);
        Question q2 = new Question("Geography, Medium: B is correct",
                "A", "B", "C", "A",  2,
                Category.GEOGRAPHY, 0,0);
        addQuestion(q2);
        Question q3 = new Question("Math, Hard: C is correct",
                "A", "B", "C", "C",3,
                Category.MATH, 0,0);
        addQuestion(q3);
        Question q4 = new Question( "Math, Easy: A is correct",
                "A", "B", "C","B", 1,
                Category.MATH, 0,0);
        addQuestion(q4);
        Question q5 = new Question("Non existing, Easy: A is correct",
                "A", "B", "C","C", 1, 1,
                1, 0);
        addQuestion(q5);
        Question q6 = new Question( "Non existing, Medium: B is correct",
                "A", "B", "C","C", 2,2,
                2, 0);
        addQuestion(q6);
    }

    public void updateTrueTimes(ArrayList<Question> question_List ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        for (Question question: question_List) {
            String query = "UPDATE " + QuestionsTable.TABLE_NAME
                  + " SET " + QuestionsTable.COLUMN_TRUE_TIMES + " = '" + question.getTrueTimes() + "' , "
                    + QuestionsTable.COLUMN_ANSWERED_TIMES + " = '" + question.getAnsweredTimes() + "'"
                  + " WHERE " + QuestionsTable._ID + " = '" + question.getId() + "'" ;

            cv.put(QuestionsTable.COLUMN_TRUE_TIMES, question.getTrueTimes());
//        db.update(QuestionsTable.TABLE_NAME, cv, "True_Atbilde", null );
            Log.d(TAG, "updateTrueTime: query: " + query);
            Log.d(TAG, "updateTrueTimes: Setting True_question_Listes to " + question.getTrueTimes());
            db.execSQL(query);    
        }
        
    }



    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption11());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
//        cv.put(QuestionsTable.COLUMN_TOPIC, question.getTopic());
        cv.put(QuestionsTable.COLUMN_TOPIC_ID, question.getCategoryID());
        cv.put(QuestionsTable.COLUMN_TRUE_TIMES, question.getTrueTimes());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }




    //Iegust  jautajumus kas ir norādīti xml faila laukā(pēc daudzuma <=skaits)
    public int getAnsweredTimes(){
        db = getReadableDatabase();

        return 0;
    }


    public ArrayList<Question> getQuestions(int categoryID) {   //, int question_Listes
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_TOPIC_ID + " = ? "; //+
//                " AND " + QuestionsTable.COLUMN_TRUE_TIMES + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID)};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption11(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setAnsweredTimes(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWERED_TIMES)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TOPIC_ID)));
                question.setTrueTimes(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TRUE_TIMES)));

                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

}
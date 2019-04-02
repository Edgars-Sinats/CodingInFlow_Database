package eu.alfo.rtu_pit.db;

import android.provider.BaseColumns;

public final class QuizContract {

//    private QuizContract() {
//    }

    public static class CategoriesTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_categories";
        public static final String COLUMN_NAME = "name";
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "Hant";
        public static final String COLUMN_QUESTION = "Jautajums";
        public static final String COLUMN_OPTION1 = "Atbilde1";
        public static final String COLUMN_OPTION2 = "Atbilde2";
        public static final String COLUMN_OPTION3 = "Atbilde3";
        public static final String COLUMN_OPTION4 = "Atbilde4";
        public static final String COLUMN_ANSWER_NR = "True_Atbilde";
//        public static final String COLUMN_TOPIC = "topic";                  //easy/medium
        public static final String COLUMN_TOPIC_ID = "Topic_ID";         //programing/math /int
        public static final String COLUMN_ANSWERED_TIMES = "Answered_Skaits";
        public static final String COLUMN_TRUE_TIMES = "True_Skaits";
    }




}
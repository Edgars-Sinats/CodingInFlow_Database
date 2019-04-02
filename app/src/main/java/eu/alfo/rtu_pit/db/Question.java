package eu.alfo.rtu_pit.db;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
//    public static final String DIFFICULTY_EASY = "Easy";
//    public static final String DIFFICULTY_MEDIUM = "Medium";
//    public static final String DIFFICULTY_HARD = "Hard";

    private int id;             //Jautajums_ID
    private String question;
    private String option11;
    private String option2;
    private String option3;
    private String option4;
    private int answerNr;       //True_atbilde
//    private String topic;          //Topic_ID
    private int categoryID;
    private int answeredTimes;
    private int trueTimes;

    public Question() {
    }

    public Question(String question, String option1, String option2, String option3, String option4,
                    int answerNr,  int categoryID, int answeredTimes ,int trueTimes) {        //String topic,
        this.question = question;
        this.option11 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
//        this.topic = topic;
        this.categoryID = categoryID;
        this.answeredTimes = answeredTimes;
        this.trueTimes  = trueTimes;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        question = in.readString();
        option11 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        answerNr = in.readInt();
        categoryID = in.readInt();
        answeredTimes = in.readInt();
        trueTimes = in.readInt();
    }

//    public Question(String s, String a, String b, String c, int i, String difficultyEasy, int programming) {
//    }


//    public Question( String sentence, String option1, String a, String b, String c,  int answerNr, String topic, int programming) {
//    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(option11);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeInt(answerNr);
        dest.writeInt(categoryID);
        dest.writeInt(answeredTimes);
        dest.writeInt(trueTimes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption11() {
        return option11;
    }

    public void setOption11(String option11) {
        this.option11 = option11;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

//    public String getTopic() {
//        return topic;
//    }

//    public void setTopic(String topic) {
//        this.topic = topic;
//    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getTrueTimes() {
        return trueTimes;
    }

    public void setTrueTimes(int trueTimes) {
        this.trueTimes = trueTimes;
    }

    public void incTrueTimes(int inc){
        this.trueTimes += inc;
//        this.trueTimes = trueTimes + inc;
    }

    public void incTrueTimes(){
        this.trueTimes++;
//        incTrueTimes(1);
    }

    public void incAnsweredTimes(){
        this.answeredTimes++;
    }

    public int getAnsweredTimes() {
        return answeredTimes;
    }

    public void setAnsweredTimes(int answeredTimes) {
        this.answeredTimes = answeredTimes;
    }

    public static String[] getAllTopicLevels() {
        return new String[]{
//                DIFFICULTY_EASY,
//                DIFFICULTY_MEDIUM,
//                DIFFICULTY_HARD
        };
    }
}
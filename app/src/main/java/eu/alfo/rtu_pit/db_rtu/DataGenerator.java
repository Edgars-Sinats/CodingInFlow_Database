package eu.alfo.rtu_pit.db_rtu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Adapter;

import org.yaml.snakeyaml.external.com.google.gdata.util.common.base.Escaper;

import java.util.ArrayList;
import java.util.List;

import eu.alfo.rtu_pit.db.QuizContract;
import eu.alfo.rtu_pit.db.QuizDbHelper;
import io.bloco.faker.Faker;



public final class DataGenerator {

    private Faker faker;
    ContentValues cv = new ContentValues();

    private QuizDbHelper dbHelper ;
    public SQLiteDatabase db;

    public DataGenerator(QuizDbHelper dbHelper){
        this.faker = new Faker();
        this.db = dbHelper.db;
    }

    public static String[] getAviableTables() {

        String[] aviableTables = new String[]{
                RTU_Contract.TeachersTable.TABLE_NAME,
                RTU_Contract.NewsTable.TABLE_NAME,
                RTU_Contract.SubjectsTable.TABLE_NAME
        };
        return aviableTables;
    }

    /* Parametrs:
        *genTeacher
                    *Name
                    *Surname
        *genSubjects
                    * Name
        * genNews
                    *   Teacher_ID
                    *   User_content
                    *   Subject_ID
    */
    public void genTeachers(int teachersCount){

        List<Integer> subjectListID = getIds(RTU_Contract.SubjectsTable.TABLE_NAME, RTU_Contract.SubjectsTable.COLUMN_ID);

        this.db.beginTransaction();

        try{

            for(int i=0; i< teachersCount; i++) {

                String name = faker.name.firstName().replaceAll("\'", "");
                String surname = faker.name.lastName().replaceAll("\'", "");

                int randomSubjectNumber = faker.number.between(0, subjectListID.size());
                int subject_id = subjectListID.get(randomSubjectNumber);

                cv.put(RTU_Contract.TeachersTable.COLUMN_NAME, name);
                cv.put(RTU_Contract.TeachersTable.COLUMN_SURNAME, surname);
//                cv.put(RTU_Contract.TeachersTable.C);

                this.db.insert(RTU_Contract.TeachersTable.TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }

    }

    public void genSubjects(int subjectCount){
        this.db.beginTransaction();

        try{

            for(int i=0; i< subjectCount; i++) {
                String name = faker.lorem.word();

                cv.put(RTU_Contract.SubjectsTable.COLUMN_NAME, name);
                this.db.insert(RTU_Contract.SubjectsTable.TABLE_NAME, null, cv);

            }

            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();

        }


    }

    private List<Integer> getIds(String tableName, String colomName){
        // Get ColumIds table from DB  and create List for ItemsList


        //@Todo SQLOpenHelper need to call from DBHelper instead, that may be problem
        Cursor c = db.rawQuery("SELECT * FROM " + tableName, null);
        List<Integer> IdList = new ArrayList<>(c.getCount());

        //Go throw TeacherTable and create Id list of TeacherListId
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex(colomName));
            IdList.add(id);
        }
        c.close();

        return IdList;
    }


    //RTU_Contract.TeachersTable.TABLE_NAME
    //RTU_Contract.TeachersTable.COLUMN_ID

    public void genNews(int newsCount, int from, int to){

        List<Integer> teacherListID = getIds(RTU_Contract.TeachersTable.TABLE_NAME, RTU_Contract.TeachersTable.COLUMN_ID);
        List<Integer> subjectListID = getIds(RTU_Contract.SubjectsTable.TABLE_NAME, RTU_Contract.SubjectsTable.COLUMN_ID);

        this.db.beginTransaction();

        //Create list of Random paragraphs(Of Defined: from, to )
        try{
            for(int i=0; i<newsCount;i++){
                int randomNumberOfParagraph = faker.number.between(from, to);
                List<String> userContentList = faker.lorem.paragraphs(randomNumberOfParagraph);

                String userContent = "";
                for(int listIdx=0; listIdx < userContentList.size(); listIdx++){
                    userContent = userContent + userContentList.get(listIdx) + " ";
                }

                int randomTeacherNumber = faker.number.between(0 , teacherListID.size());
                int teacherID = teacherListID.get(randomTeacherNumber);

                int randomSubjectNumber = faker.number.between(0, subjectListID .size());
                int subjectID = subjectListID.get(randomSubjectNumber);


                cv.put(RTU_Contract.NewsTable.COLUMN_TEACHER_ID, teacherID);
                cv.put(RTU_Contract.NewsTable.COLUMN_USER_CONTENT, userContent);
                cv.put(RTU_Contract.NewsTable.COLUMN_USER_CONTENT, subjectID);

                this.db.insert(RTU_Contract.NewsTable.TABLE_NAME,null,cv);



//                String querry = "INSERT INTO News (Teacher_ID, User_content, Subject_ID) VALUES (" +
//                        "'" + teacherID + "', " +
//                        "'" + userContent + "', " +
//                        "'" + subjectID + "');" +
//                        "";

//                this.db.execSQL(querry);

            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }



        
    }


}

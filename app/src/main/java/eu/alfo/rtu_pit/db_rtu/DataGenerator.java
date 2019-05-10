package eu.alfo.rtu_pit.db_rtu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;



public final class DataGenerator {

    private static DataGenerator instance;

    private Faker faker;
    private SQLiteDatabase db ;


    public DataGenerator(SQLiteDatabase db){
        this.faker = new Faker();
        this.db = db;
    }

    public DataGenerator() {

    }

    public static DataGenerator getInstance() {
        return instance;
    }

    //    private static synchronized DataGenerator getInstance (Context contextDataGen){
//        if(instance == null){
//            instance = new DataGenerator(contextDataGen.getApplicationContext());
//        }
//        return instance;
//    }

    public static String[] getAviableTables() {

        String[] aviableTables = new String[]{
                RTU_Contract.TeachersTable.TABLE_NAME,
                RTU_Contract.NewsTable.TABLE_NAME,
                RTU_Contract.SubjectsTable.TABLE_NAME
        };

        return aviableTables;
    }

    public void genTeachers(int teachersCount){

            for(int i=0; i< teachersCount; i++) {
                String name = faker.name.firstName().replaceAll("\'", "");
                String surname = faker.name.lastName().replaceAll("\'", "");
                String querry = "INSERT INTO Teacher (Teacher_Name, Teacher_Surname) VALUES ('" + name + "', '" + surname + "');";

                this.db.execSQL(querry);
            }
    }

    public void genSubjects(int subjectCount){
        for(int i=0; i< subjectCount; i++) {
            String name = faker.lorem.word();

            String querry = "INSERT INTO Subject (Subject_name) VALUES ('" + name + "');";
            this.db.execSQL(querry);
        }

    }

    private List<Integer> getIds(String tableName, String colomName){
        // Get ColumIds table from DB  and create List for ItemsList
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

        //Create list of Random paragraphs(Of Defined: from, to )
        for(int i=0; i<newsCount;i++){
            int randomNumberOfParagraph = faker.number.between(from, to);
            List<String> userContentList = faker.lorem.paragraphs(randomNumberOfParagraph);
            
            String userContent = "";
            for(int listIdx=0; listIdx < userContentList.size(); listIdx++){
                userContent = userContent + userContentList.get(listIdx) + " ";
            }
            
            int randomTeacherNumber = faker.number.between(0 , teacherListID.size());
            int teacherID = teacherListID.get(randomTeacherNumber);


            int randomSubjectNumber = faker.number.between(0, subjectListID.size());
            int subjectID = subjectListID.get(randomSubjectNumber);


            String querry = "INSERT INTO News (Teacher_ID, User_content, Subject_ID) VALUES (" +
                    "'" + teacherID + "', " +
                    "'" + userContent + "', " +
                    "'" + subjectID + "');" +
                    "";

            this.db.execSQL(querry);

        }
        
        
    }


}

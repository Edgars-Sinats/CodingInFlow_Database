package eu.alfo.rtu_pit.db_rtu;

import android.provider.BaseColumns;

public final class RTU_Contract {

//    private QuizContract() {
//    }

    public static class GradesTable implements BaseColumns {
        public static final String TABLE_NAME = "Grades";
        public static final String COLUMN_ID = "Grade_ID";
        public static final String COLUMN_STUDENT_ID = "Student_ID";
        public static final String COLUMN_TEACHER_ID = "Teacher_ID";
        public static final String COLUMN_SUBJECT_ID = "Subject_ID";
    }

    public static class StudentsTable implements BaseColumns {
        public static final String TABLE_NAME = "Student";
        public static final String COLUMN_ID = "Student_ID";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_SURNAME = "Surname";
        public static final String COLUMN_AGE = "Age";
        public static final String COLUMN_COURSE = "Course";
    }

    public static class StudentTeacherRelation implements BaseColumns {
        public static final String TABLE_NAME = "StudentTeacherRelation";
        public static final String COLUMN_STUDENT_ID = "Student_ID";
        public static final String COLUMN_TEACHER_ID = "Teacher_ID";
    }

    public static class TeachersTable implements BaseColumns {
        public static final String TABLE_NAME = "Teacher";
        public static final String COLUMN_ID = "Teacher_ID";
        public static final String COLUMN_NAME = "Teacher_Name";
        public static final String COLUMN_SURNAME = "Teacher_Surname";
        public static final String COLUMN_AGE = "Subject_ID";
    }

    public static class SubjectsTable implements BaseColumns {
        public static final String TABLE_NAME = "Subject";
        public static final String COLUMN_ID = "Subject_ID";
        public static final String COLUMN_NAME = "Subject_name";
    }

    public static class TeacherSubjectRelation implements BaseColumns {
        public static final String TABLE_NAME = "TeacherSubjectRelation";
        public static final String COLUMN_TEACHER_ID = "Teacher_ID";
        public static final String COLUMN_SUBJECT_ID = "Subject_ID";
    }

    public static class NewsTable implements BaseColumns {
        public static final String TABLE_NAME = "News";
        public static final String COLUMN_ID = "News_ID";
        public static final String COLUMN_TEACHER_ID = "Teacher_ID";
        public static final String COLUMN_SUBJECT_ID = "Subject_ID";
        public static final String COLUMN_COURSE_ID = "Course_ID";
        public static final String COLUMN_USER_CONTENT = "User_content";
    }

    public static class CoursesTable implements BaseColumns{
        public static final String TABLE_NAME = "Course";
        public static final String COLUMN_ID = "Course_ID";
    }

    public static class CoursesNewsRelation implements BaseColumns {
        public static final String TABLE_NAME = "CourseNewsRelation";
        public static final String COLUMN_COURSES_ID = "Courses_ID";
        public static final String COLUMN_NEWS_ID = "News_ID";
    }


    private static String makeSqlRef(String column_id, String ref_table_name, String ref_colum_id){
        return "FOREIGN KEY(" + column_id + ") REFERENCES " + ref_table_name+ "(" + ref_colum_id + ")";
    }


    //Daudzskatli!!! Maris teica
    public static String[] getSQLQuerry(){
        String gradesTable = "CREATE TABLE " +
        GradesTable.TABLE_NAME + " ( " +
        GradesTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        GradesTable.COLUMN_STUDENT_ID + " INTEGER, " +
        GradesTable.COLUMN_TEACHER_ID + " INTEGER, " +
        GradesTable.COLUMN_SUBJECT_ID + " INTEGER, " +
        makeSqlRef(GradesTable.COLUMN_STUDENT_ID, StudentsTable.TABLE_NAME, StudentsTable.COLUMN_ID) + ", " +
        makeSqlRef(GradesTable.COLUMN_TEACHER_ID, TeachersTable.TABLE_NAME, TeachersTable.COLUMN_ID) + ", " +
        makeSqlRef(GradesTable.COLUMN_SUBJECT_ID, SubjectsTable.TABLE_NAME, SubjectsTable.COLUMN_ID) + ")";

        String studentsTable = "CREATE TABLE " +
        StudentsTable.TABLE_NAME + " ( " +
        StudentsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        StudentsTable.COLUMN_NAME +  " TEXT, " +
        StudentsTable.COLUMN_SURNAME +  " TEXT, " +
        StudentsTable.COLUMN_AGE +  " INTEGER, " +
        StudentsTable.COLUMN_COURSE +  " TEXT " + ")";

        String teacherTable = "CREATE TABLE " +
        TeachersTable.TABLE_NAME + " ( " +
        TeachersTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        TeachersTable.COLUMN_NAME +  " TEXT, " +
        TeachersTable.COLUMN_SURNAME +  " TEXT, " +
        TeachersTable.COLUMN_AGE +  " INTEGER " + ")";

        String studentTeacherRelationTable = "CREATE TABLE " +
        StudentTeacherRelation.TABLE_NAME + " ( " +
        StudentTeacherRelation.COLUMN_STUDENT_ID + " INTEGER, " +
        StudentTeacherRelation.COLUMN_TEACHER_ID + " INTEGER, " +
        makeSqlRef(StudentTeacherRelation.COLUMN_STUDENT_ID, StudentsTable.TABLE_NAME, StudentsTable.COLUMN_ID) + ", " +
        makeSqlRef(StudentTeacherRelation.COLUMN_TEACHER_ID, TeachersTable.TABLE_NAME, TeachersTable.COLUMN_ID) +  ")";

        String subjectTable = "CREATE TABLE " +
        SubjectsTable.TABLE_NAME + " ( " +
        SubjectsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        SubjectsTable.COLUMN_NAME +  " TEXT " + ")";

        String teacherSubjectRelationTable = "CREATE TABLE " +
        TeacherSubjectRelation.TABLE_NAME + " ( " +
        TeacherSubjectRelation.COLUMN_SUBJECT_ID + " INTEGER, " +
        TeacherSubjectRelation.COLUMN_TEACHER_ID + " INTEGER, " +
        makeSqlRef(TeacherSubjectRelation.COLUMN_SUBJECT_ID, SubjectsTable.TABLE_NAME, SubjectsTable.COLUMN_ID) + ", " +
        makeSqlRef(TeacherSubjectRelation.COLUMN_TEACHER_ID, TeachersTable.TABLE_NAME, TeachersTable.COLUMN_ID) +  ")";


        String courseTable = "CREATE TABLE " +
        CoursesTable.TABLE_NAME+ " ( " +
        CoursesTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + ")";

        String newsTable = "CREATE TABLE " +
                NewsTable.TABLE_NAME + " ( " +
                NewsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NewsTable.COLUMN_TEACHER_ID + " INTEGER, " +
                NewsTable.COLUMN_SUBJECT_ID + " INTEGER, " +
                NewsTable.COLUMN_COURSE_ID + " INTEGER, " +
                NewsTable.COLUMN_USER_CONTENT + " TEXT, " +
                makeSqlRef(NewsTable.COLUMN_TEACHER_ID, TeachersTable.TABLE_NAME, TeachersTable.COLUMN_ID) + ", " +
                makeSqlRef(NewsTable.COLUMN_SUBJECT_ID, SubjectsTable.TABLE_NAME, SubjectsTable.COLUMN_ID) + ", " +
                makeSqlRef(NewsTable.COLUMN_COURSE_ID, CoursesTable.TABLE_NAME, CoursesTable.COLUMN_ID)  + ")";

        String coursesNewsRelationTable = "CREATE TABLE " +
        CoursesNewsRelation.TABLE_NAME+ " ( " +
        CoursesNewsRelation.COLUMN_COURSES_ID + " INTEGER, " +
        CoursesNewsRelation.COLUMN_NEWS_ID + " INTEGER, " +
        makeSqlRef(CoursesNewsRelation.COLUMN_COURSES_ID, CoursesTable.TABLE_NAME, CoursesTable.COLUMN_ID)  + ", " +
        makeSqlRef(CoursesNewsRelation.COLUMN_NEWS_ID, NewsTable.TABLE_NAME, NewsTable.COLUMN_ID)   + ")";

        String s[] = {studentsTable,
                gradesTable,
                teacherTable,
                studentTeacherRelationTable,
                subjectTable,
                teacherSubjectRelationTable,
                courseTable,
                newsTable,
                coursesNewsRelationTable
        };
        return s;
    };

}
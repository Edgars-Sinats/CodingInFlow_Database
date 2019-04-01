package eu.alfo.rtu_pit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class NewsDbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "HUNTTEST.db"; //Change name if you want to test
    private static final int DATABASE_VERSION = 8; //increase(aTimes-12)(7)


    private static NewsDbHelper instance;
    private SQLiteDatabase db ;

    private NewsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized NewsDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new NewsDbHelper(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        String querries[] = NewsContract.getSQLQuerry();
        int a = 0;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

}

package eu.alfo.rtu_pit.debuging;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.alfo.rtu_pit.db.QuizDbHelper;
import eu.alfo.rtu_pit.db_rtu.RTU_Contract;

public class ViewDbHandler {

    private SQLiteDatabase db;
    String[] columnNames;

    //@ToDo null point
    public ViewDbHandler(QuizDbHelper dbHelper){
        this.db = dbHelper.db;
    }

    public List<ArrayList<String>> getOutputList(String tableName){
        List<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();


        String selectQuery = "SELECT  * FROM " + tableName;
        Cursor cursor = db.rawQuery(selectQuery, null);
        int columnCount = cursor.getColumnCount();
        this.columnNames = cursor.getColumnNames();


        if (cursor.moveToFirst()) {
            do {
                int pos = cursor.getPosition();
                ArrayList<String> rowData = new ArrayList<>();
                for(int i=0; i<columnCount; i++){
                    rowData.add(cursor.getString(i));
                }
                rows.add(pos, rowData);
            } while (cursor.moveToNext());
        }
        return rows;
    }
}
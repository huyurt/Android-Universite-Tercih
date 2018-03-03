package com.yurt.niversitetercih;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "universitesiralama.sqlite";
    public static final String DBLOCATION = "/data/data/com.yurt.niversitetercih/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public String[][] getListProduct(String bolum, String siralama, String bolge, String sehir, String fakulte, String tur, String yeni, String sirala) {
        openDatabase();
        Cursor cursor;
        String[] bolgeArray = bolge.split("\n\n");
        String[] sehirArray = sehir.split("\n\n");
        String[] fakulteArray = fakulte.split("\n\n");
        String[] turArray = tur.split("\n\n");
        boolean girdi = false;
        String sorgu = "SELECT * FROM y2017";
        if(!bolgeArray[0].equals("Tümü")){
            sorgu += " WHERE (";
            girdi = true;
            if(bolgeArray.length > 1) {
                for (int i = 0; i < bolgeArray.length; i++) {
                    sorgu += "bolge = '" + bolgeArray[i] + "'";
                    if(i != bolgeArray.length - 1)
                        sorgu += " OR ";
                }
            } else {
                sorgu += "bolge = '" + bolgeArray[0] + "'";
            }
            sorgu += ")";
        }
        if(!sehirArray[0].equals("Tümü")){
            if(!girdi) {
                sorgu += " WHERE (";
                girdi = true;
            } else
                sorgu += " AND (";
            if(sehirArray.length > 1) {
                for (int i = 0; i < sehirArray.length; i++) {
                    sorgu += "sehir = '" + sehirArray[i] + "'";
                    if(i != sehirArray.length - 1)
                        sorgu += " OR ";
                }
            } else {
                sorgu += "sehir = '" + sehirArray[0] + "'";
            }
            sorgu += ")";
        }
        if(!fakulteArray[0].equals("Tümü")){
            if(!girdi) {
                sorgu += " WHERE (";
                girdi = true;
            } else
                sorgu += " AND (";
            if(fakulteArray.length > 1) {
                for (int i = 0; i < fakulteArray.length; i++) {
                    sorgu += "fakulte = '" + fakulteArray[i] + "'";
                    if(i != fakulteArray.length - 1)
                        sorgu += " OR ";
                }
            } else {
                sorgu += "fakulte = '" + fakulteArray[0] + "'";
            }
            sorgu += ")";
        }
        if(!turArray[0].equals("Tümü")){
            if(!girdi) {
                sorgu += " WHERE (";
                girdi = true;
            } else
                sorgu += " AND (";
            if(turArray.length > 1) {
                for (int i = 0; i < turArray.length; i++) {
                    sorgu += "tur = '" + turArray[i] + "'";
                    if(i != turArray.length - 1)
                        sorgu += " OR ";
                }
            } else {
                sorgu += "tur = '" + turArray[0] + "'";
            }
            sorgu += ")";
        }
        if(bolum.equals("") && !siralama.equals("")){
            if(bolgeArray[0].equals("Tümü") && sehirArray[0].equals("Tümü") && fakulteArray[0].equals("Tümü") && turArray[0].equals("Tümü")){
                sorgu += " WHERE ";
            }
            if(girdi)
                sorgu += " AND ";
            if(yeni.equals("true"))
                sorgu += " (siralama >= '" + siralama + "' OR siralama = 0)";
            else
                sorgu += " siralama >= '" + siralama + "'";
        } else if(!bolum.equals("") && siralama.equals("")){
            if(bolgeArray[0].equals("Tümü") && sehirArray[0].equals("Tümü") && fakulteArray[0].equals("Tümü") && turArray[0].equals("Tümü")){
                sorgu += " WHERE ";
            }
            if(girdi)
                sorgu += " AND ";
            if(yeni.equals("true"))
                sorgu += " bolum LIKE '%" + bolum + "%'";
            else
                sorgu += " bolum LIKE '%" + bolum + "%' AND siralama != 0";
        } else if(bolum.equals("") && siralama.equals("")){
            if(!yeni.equals("true") && girdi)
                sorgu += " AND siralama != 0";
            else if(!yeni.equals("true") && !girdi)
                sorgu += " WHERE siralama != 0";
        } else {
            if(bolgeArray[0].equals("Tümü") && sehirArray[0].equals("Tümü") && fakulteArray[0].equals("Tümü") && turArray[0].equals("Tümü")){
                sorgu += " WHERE ";
            }
            if(girdi)
                sorgu += " AND ";
            if(yeni.equals("true"))
                sorgu += " bolum LIKE '%" + bolum + "%' AND (siralama >= '" + siralama + "' OR siralama = 0)";
            else
                sorgu += " bolum LIKE '%" + bolum + "%' AND siralama >= '" + siralama + "'";
        }
        if(sirala.equals("0"))
            sorgu += " ORDER BY siralama";
        else if(sirala.equals("1"))
            sorgu += " ORDER BY sehir";
        else if(sirala.equals("2"))
            sorgu += " ORDER BY ad";
        else if(sirala.equals("3"))
            sorgu += " ORDER BY fakulte";
        else if(sirala.equals("4"))
            sorgu += " ORDER BY bolum";
        else
            sorgu += " ORDER BY tur";
        cursor = mDatabase.rawQuery(sorgu, null);
        cursor.moveToFirst();
        String[][] sonuc = new String[6][cursor.getCount()];
        int i = 0;
        while (!cursor.isAfterLast()) {
            sonuc[0][i] = cursor.getString(2);
            sonuc[1][i] = cursor.getString(3);
            sonuc[2][i] = cursor.getString(4);
            sonuc[3][i] = harfBuyut(cursor.getString(5));
            sonuc[4][i] = cursor.getString(6);
            sonuc[5][i] = cursor.getString(7);
            cursor.moveToNext();
            i++;
        }
        cursor.close();
        closeDatabase();
        return sonuc;
    }

    private String harfBuyut(String string) {
        char[] harfler = string.toCharArray();
        boolean harfDegil = false;
        String sonuc = "";
        for (int i = 0; i < harfler.length; i++) {
            char ch = harfler[i];
            if ((ch >= 'a' && ch <= 'z') || ch == 'ı' || ch == 'ü' || ch == 'ş' || ch == 'ö' || ch == 'ç' || ch == 'ğ') {
                if (!harfDegil) {
                    sonuc += String.valueOf(harfler[i]).toUpperCase();
                    harfDegil = true;
                } else {
                    sonuc += String.valueOf(harfler[i]);
                }
            } else {
                sonuc += harfler[i];
                harfDegil = false;
            }
        }
        return sonuc.trim();
    }
}
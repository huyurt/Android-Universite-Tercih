package com.yurt.niversitetercih;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private static int ListViewActivitySonucKodu = 0;
    private static int SettingSonucKodu = 0;
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        activityDuzeni(editText1, editText2);

        mDBHelper = new DatabaseHelper(this);
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (!database.exists()) {
            mDBHelper.getReadableDatabase();
        }
    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[2048];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivityForResult(intent, SettingSonucKodu);
    }

    public void onButtonClickAra(View view) {
        mDBHelper = new DatabaseHelper(this);
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (database.exists()) {
            mDBHelper.getReadableDatabase();
            if (!copyDatabase(this)) {
                return;
            }
        }
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        ara(editText1, editText2);
    }

    @Override
    protected void onActivityResult(int istekKodu, int sonucKodu, Intent intent) {
        super.onActivityResult(istekKodu, sonucKodu, intent);
        if (istekKodu == ListViewActivitySonucKodu) {
            if (sonucKodu == RESULT_OK) {
                intent.getStringExtra("bolgeler");
                intent.getStringExtra("sehirler");
                intent.getStringExtra("fakulte");
                intent.getStringExtra("tur");
            }
        }
    }

    private void activityDuzeni(EditText editText1, EditText editText2) {
        editText1.setHint("Bölüm gir...");
        editText2.setHint("Sıralama gir...");
    }

    private void editTextDuzenle(EditText editText) {
        editText.setEms(10);
        editText.setBackgroundColor(Color.TRANSPARENT);
        editText.setFocusable(false);
        editText.setClickable(false);
    }

    private void ara(EditText editText1, EditText editText2) {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        String secilenler1 = sharedPreferences.getString("SEÇİLENLER1", "Tümü");
        String secilenler2 = sharedPreferences.getString("SEÇİLENLER2", "Tümü");
        String secilenler3 = sharedPreferences.getString("SEÇİLENLER3", "Tümü");
        String secilenler4 = sharedPreferences.getString("SEÇİLENLER4", "Tümü");
        String secilenler5 = sharedPreferences.getString("SEÇİLENLER5", "true");
        String secilenler6 = sharedPreferences.getString("SEÇİLENLER6", "0");
        String[][] sonuc;
        sonuc = mDBHelper.getListProduct(editText1.getText().toString().toLowerCase(), editText2.getText().toString(), secilenler1, secilenler2, secilenler3, secilenler4, secilenler5, secilenler6);
        TableLayout tableLayout1 = (TableLayout) findViewById(R.id.tableLayout1);
        for (int i = 1; i < tableLayout1.getChildCount(); i++) {
            View child = tableLayout1.getChildAt(i);
            if (child instanceof TableRow)
                ((ViewGroup) child).removeAllViews();
        }
        for (int i = 0; i < sonuc[0].length; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            EditText editText3 = new EditText(this);
            EditText editText4 = new EditText(this);
            EditText editText5 = new EditText(this);
            EditText editText6 = new EditText(this);
            EditText editText7 = new EditText(this);
            EditText editText8 = new EditText(this);
            editText3.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 80));
            editText4.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 60));
            editText5.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 60));
            editText6.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 60));
            editText7.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 100));
            editText8.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 90));
            editText3.setPadding(10, 0, 0, 0);
            editTextDuzenle(editText3);
            editTextDuzenle(editText4);
            editTextDuzenle(editText5);
            editTextDuzenle(editText6);
            editTextDuzenle(editText7);
            editTextDuzenle(editText8);
            editText3.setText(sonuc[0][i]);
            editText4.setText(sonuc[1][i]);
            editText5.setText(sonuc[2][i]);
            editText6.setText(sonuc[3][i]);
            editText7.setText(sonuc[4][i]);
            editText8.setText(sonuc[5][i]);
            tableRow.addView(editText3);
            tableRow.addView(editText4);
            tableRow.addView(editText5);
            tableRow.addView(editText6);
            tableRow.addView(editText7);
            tableRow.addView(editText8);
            tableLayout1.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
        Toast.makeText(this, sonuc[0].length + " sonuç bulundu.", Toast.LENGTH_LONG).show();
    }
}
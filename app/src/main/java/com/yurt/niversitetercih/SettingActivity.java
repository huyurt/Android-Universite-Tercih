package com.yurt.niversitetercih;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class SettingActivity extends AppCompatActivity {
    private static int ListViewActivitySonucKodu = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        String secilenler1 = sharedPreferences.getString("SEÇİLENLER1", "Tümü");
        String secilenler2 = sharedPreferences.getString("SEÇİLENLER2", "Tümü");
        String secilenler3 = sharedPreferences.getString("SEÇİLENLER3", "Tümü");
        String secilenler4 = sharedPreferences.getString("SEÇİLENLER4", "Tümü");
        final String secilenler5 = sharedPreferences.getString("SEÇİLENLER5", "true");
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        EditText editText4 = (EditText) findViewById(R.id.editText4);
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        editText1.setText(secilenler1);
        editText2.setText(secilenler2);
        editText3.setText(secilenler3);
        editText4.setText(secilenler4);
        if (secilenler5.equals("true"))
            checkBox1.setChecked(true);
        else
            checkBox1.setChecked(false);

        Intent intent = new Intent();
        intent.putExtra("bolgeler", secilenler1);
        intent.putExtra("sehirler", secilenler2);
        intent.putExtra("fakulte", secilenler3);
        intent.putExtra("tur", secilenler4);
        intent.putExtra("yeni", secilenler5);
        setResult(RESULT_OK, intent);

        final String[] spinnerArray = {"Sıralama", "İl", "Üniversite", "Fakülte", "Bölüm", "Tür"};
        final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        spinner.setAdapter(arrayAdapter);
        String secilenler6 = sharedPreferences.getString("SEÇİLENLER6", "0");
        spinner.setSelection(Integer.parseInt(secilenler6));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("SEÇİLENLER6", String.valueOf(position));
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    public void onButtonClickSetting(View view) {
        Button button = (Button) view;
        Intent intentHangisi = new Intent();
        intentHangisi.putExtra("istek", button.getText().toString());
        setResult(RESULT_OK, intentHangisi);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("İSTEK", button.getText().toString());
        editor.commit();

        Intent intent = new Intent(this, ListViewActivity.class);
        startActivityForResult(intent, ListViewActivitySonucKodu);
    }

    public void onCheckBoxClicked(View view){
        CheckBox checkBox = (CheckBox) view;
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(checkBox.isChecked())
            editor.putString("SEÇİLENLER5",  "true");
        else
            editor.putString("SEÇİLENLER5",  "false");
        editor.commit();
    }

    @Override
    protected void onActivityResult(int istekKodu, int sonucKodu, Intent intent) {
        super.onActivityResult(istekKodu, sonucKodu, intent);
        if (istekKodu == ListViewActivitySonucKodu) {
            if (sonucKodu == RESULT_OK) {
                String returnString = intent.getStringExtra("secilenler");
                SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
                String istek = sharedPreferences.getString("İSTEK", "");
                EditText editText;
                if (istek.equals("bölge")) {
                    editText = (EditText) findViewById(R.id.editText1);
                    editText.setText(returnString);
                    intent.putExtra("bolgeler", returnString);
                } else if (istek.equals("şehir")) {
                    editText = (EditText) findViewById(R.id.editText2);
                    editText.setText(returnString);
                    intent.putExtra("sehirler", returnString);
                } else if (istek.equals("fakülte")) {
                    editText = (EditText) findViewById(R.id.editText3);
                    editText.setText(returnString);
                    intent.putExtra("fakulte", returnString);
                } else {
                    editText = (EditText) findViewById(R.id.editText4);
                    editText.setText(returnString);
                    intent.putExtra("tur", returnString);
                }
                setResult(RESULT_OK, intent);
            }
        }
    }

    public void onClickGeri(View v) {
        finish();
    }
}
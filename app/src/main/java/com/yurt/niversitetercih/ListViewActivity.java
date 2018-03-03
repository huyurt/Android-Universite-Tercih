package com.yurt.niversitetercih;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ListViewActivity extends ListActivity {
    private static String[] bolgeler = {"Tümü", "Marmara", "İç Anadolu", "Ege", "Akdeniz", "Güneydoğu Anadolu", "Karadeniz", "Doğu Anadolu"};
    private static String[] sehirler = {"Tümü", "Adana", "Adıyaman", "Afyon", "Ağrı", "Aksaray", "Amasya", "Ankara", "Antalya", "Ardahan", "Artvin", "Aydın", "Balıkesir", "Bartın", "Batman", "Bayburt", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Iğdır", "Isparta", "İstanbul", "İzmir", "Kahramanmaraş", "Karabük", "Karaman", "Kars", "Kastamonu", "Kayseri", "Kırıkkale", "Kırklareli", "Kırşehir", "Kilis", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Mardin", "Mersin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Osmaniye", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Şanlıurfa", "Şırnak", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Uşak", "Van", "Yalova", "Yozgat", "Zonguldak", "Yurt Dışı"};
    private static String[] fakulte = {"Tümü", "Açık ve Uzaktan Eğitim Fakültesi", "Açıköğretim Fakültesi", "Bankacılık ve Sigortacılık Yüksekokulu", "Beden Eğitimi ve Spor Yüksekokulu", "Beşeri Bilimler Fakültesi", "Beşeri ve Sosyal Bilimler Fakültesi", "Bilgisayar ve Bilişim Bilimleri Fakültesi", "Bilgisayar ve Bilişim Fakültesi", "Bilgisayar ve Teknoloji Yüksekokulu", "Deniz Bilimleri Fakültesi", "Deniz Bilimleri ve Teknolojisi Fakültesi", "Deniz İşletmeciliği ve Yönetimi Fakültesi", "Denizcilik Fakültesi", "Denizcilik ve Ulaştırma Yüksekokulu", "Denizcilik Yüksekokulu", "Dil ve Tarih Coğrafya Fakültesi", "Dini İlimler Fakültesi", "Diş Hekimliği Fakültesi", "Doğa Bilimleri, Mimarlık ve Mühendislik Fakültesi", "Eczacılık Fakültesi", "Edebiyat Fakültesi", "Eğitim Bilimleri Fakültesi", "Eğitim Fakültesi", "Elektrik-Elektronik Fakültesi", "Fen Fakültesi", "Fen ve Edebiyat Fakültesi", "Fizik Tedavi ve Rehabilitasyon Yüksekokulu", "Gemi İnşaatı ve Deniz Bilimleri Fakültesi", "Gemi İnşaatı ve Denizcilik Fakültesi", "Gülhane Diş Hekimliği Fakültesi", "Gülhane Hemşirelik Yüksekokulu", "Gülhane Tıp Fakültesi", "Güzel Sanatlar Fakültesi", "Güzel Sanatlar Tasarım ve Mimarlık Fakültesi", "Güzel Sanatlar ve Mimarlık Fakültesi", "Güzel Sanatlar ve Tasarım Fakültesi", "Güzel Sanatlar, Tasarım ve Mimarlık Fakültesi", "Hasan Ali Yücel Eğitim Fakültesi", "Havacılık ve Uzay Bilimleri Fakültesi", "Havacılık Yüksekokulu", "Hayvansal Üretim Yüksekokulu", "Hemşirelik Fakültesi", "Hemşirelik Yüksekokulu", "Hukuk Fakültesi", "İktisadi ve İdari Bilimler Fakültesi", "İktisadi, İdari ve Sosyal Bilimler Fakültesi", "İktisat Fakültesi", "İlahiyat Fakültesi", "İletişim Bilimleri Fakültesi", "İletişim Fakültesi", "İnsan ve Toplum Bilimleri Fakültesi", "İnsani Bilimler Fakültesi", "İnsani Bilimler ve Edebiyat Fakültesi", "İnsani ve Sosyal Bilimler Fakültesi", "İnşaat Fakültesi", "İslami İlimler Fakültesi", "İşletme Fakültesi", "İşletme ve Ekonomi Fakültesi", "İşletme ve Finans Yüksekokulu", "İşletme ve Yönetim Bilimleri Fakültesi", "İTÜ-KKTC Eğitim Araştırma Yerleşkesi (Gazimağusa)", "Kimya-Metalurji Fakültesi", "Kültür ve Sosyal Bilimler Fakültesi", "Maden Fakültesi", "Makine Fakültesi", "Mimarlık Fakültesi", "Mimarlık ve Güzel Sanatlar Fakültesi", "Mimarlık ve Tasarım Fakültesi", "Mimarlık, Tasarım ve Güzel Sanatlar Fakültesi", "Mühendislik Fakültesi", "Mühendislik ve Doğa Bilimleri Fakültesi", "Mühendislik ve Mimarlık Fakültesi", "Mühendislik ve Teknoloji Fakültesi", "ODTÜ Kuzey Kıbrıs Kampüsü (KKTC-Güzelyurt)", "Orman Fakültesi", "Sağlık Bilimleri Fakültesi", "Sağlık Bilimleri Yüksekokulu", "Sağlık Yüksekokulu", "Sanat ve Sosyal Bilimler Fakültesi", "Sanat ve Tasarım Fakültesi", "Sanat, Tasarım ve Mimarlık Fakültesi", "Sivil Havacılık Yüksekokulu", "Siyasal Bilgiler Fakültesi", "Siyasal Bilimler Fakültesi", "Sosyal Bilimler Fakültesi", "Sosyal ve Beşeri Bilimler Fakültesi", "Spor Bilimleri Fakültesi", "Spor Yüksekokulu", "Su Ürünleri Fakültesi", "Tapu Kadastro Yüksekokulu", "Tarım Bilimleri ve Teknolojileri Fakültesi", "Tarım ve Doğa Bilimleri Fakültesi", "Teknoloji Fakültesi", "Tekstil Teknolojileri ve Tasarımı Fakültesi", "Temel Bilimler Fakültesi", "Tıp Fakültesi", "Ticari Bilimler Fakültesi", "Turizm Fakültesi", "Turizm İşletmeciliği ve Otelcilik Yüksekokulu", "Turizm ve Otel İşletmeciliği Yüksekokulu", "Turizm ve Otelcilik Yüksekokulu", "Tütün Eksperliği Yüksekokulu", "Uçak ve Uzay Bilimleri Fakültesi", "Ulaştırma ve Lojistik Fakültesi", "Uluslararası İslam ve Din Bilimleri Fakültesi", "Uluslararası Tıp Fakültesi", "Uygulamalı Bilimler Fakültesi", "Uygulamalı Bilimler Yüksekokulu", "Uygulamalı Sosyal Bilimler Yüksekokulu", "Uygulamalı Teknoloji ve İşletmecilik Yüksekokulu", "Veteriner Fakültesi", "Yabancı Diller Fakültesi", "Yabancı Diller Yüksekokulu", "Yaşam ve Doğa Bilimleri Fakültesi", "Yönetim Bilimleri Fakültesi", "Ziraat Fakültesi", "Ziraat ve Doğa Bilimleri Fakültesi"};
    private static String[] tur = {"Tümü", "DİL-1", "DİL-2", "DİL-3", "MF-1", "MF-2", "MF-3", "MF-4", "TM-1", "TM-2", "TM-3", "TS-1", "TS-2", "YGS-1", "YGS-2", "YGS-3", "YGS-4", "YGS-5", "YGS-6"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        String sonuc = sharedPreferences.getString("İSTEK", "-1");

        if (sonuc.equals("bölge")) {
            setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, bolgeler));
        } else if (sonuc.equals("şehir")) {
            setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, sehirler));
        } else if (sonuc.equals("fakülte")) {
            setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, fakulte));
        } else {
            setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, tur));
        }
        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        checkBoxDuzenle(listView, sharedPreferences);
    }

    public void onClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        ListView listView = getListView();
        String duzenSecili = "";
        String secilenler = "";
        for (int i = 0; i < listView.getCount(); i++) {
            if (listView.isItemChecked(i)) {
                secilenler += listView.getItemAtPosition(i) + "\n\n";
                duzenSecili += i + " ";
            }
        }
        Intent intent = new Intent();
        intent.putExtra("secilenler", secilenler.trim());
        setResult(RESULT_OK, intent);

        String sonuc = sharedPreferences.getString("İSTEK", "-1");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sonuc.equals("bölge")) {
            editor.putString("DUZENSECILI1", duzenSecili);
            editor.putString("SEÇİLENLER1", secilenler.trim());
        } else if (sonuc.equals("şehir")) {
            editor.putString("DUZENSECILI2", duzenSecili);
            editor.putString("SEÇİLENLER2", secilenler.trim());
        } else if (sonuc.equals("fakülte")) {
            editor.putString("DUZENSECILI3", duzenSecili);
            editor.putString("SEÇİLENLER3", secilenler.trim());
        } else {
            editor.putString("DUZENSECILI4", duzenSecili);
            editor.putString("SEÇİLENLER4", secilenler.trim());
        }
        editor.commit();
        finish();
    }

    public void onClickGeri(View v) {
        finish();
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        int elemanSayisi = fakulte.length;
        if (position == 0 && parent.isItemChecked(position)) {
            for (int i = 1; i < elemanSayisi; i++)
                parent.setItemChecked(i, false);
        } else if (position != 0 && parent.isItemChecked(position)) {
            parent.setItemChecked(0, false);
        } else if (position != 0 && !parent.isItemChecked(0)) {
            int isaretli = 0;
            for (int i = 1; i < elemanSayisi; i++) {
                if (parent.isItemChecked(i)) {
                    isaretli++;
                    break;
                }
            }
            if (isaretli == 0) {
                parent.setItemChecked(0, true);
            }
        }
    }

    private void checkBoxDuzenle(ListView listView, SharedPreferences sharedPreferences) {
        String sonuc = sharedPreferences.getString("İSTEK", "-1");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String checkBoxDuzeni;
        if (sonuc.equals("bölge")) {
            checkBoxDuzeni = sharedPreferences.getString("DUZENSECILI1", "-1");
        } else if (sonuc.equals("şehir")) {
            checkBoxDuzeni = sharedPreferences.getString("DUZENSECILI2", "-1");
        } else if (sonuc.equals("fakülte")) {
            checkBoxDuzeni = sharedPreferences.getString("DUZENSECILI3", "-1");
        } else {
            checkBoxDuzeni = sharedPreferences.getString("DUZENSECILI4", "-1");
        }
        if (checkBoxDuzeni.equals("-1")) {
            listView.setItemChecked(0, true);
        } else {
            String[] checkBoxDuzeniArray = checkBoxDuzeni.split(" ");
            for (int i = 0; i < checkBoxDuzeniArray.length; i++) {
                listView.setItemChecked(Integer.parseInt(checkBoxDuzeniArray[i]), true);
            }
        }
    }

    public void onButtonClickTemizle(View view){
        ListView listView = getListView();
        listView.clearChoices();
        listView.setItemChecked(0, true);
    }
}
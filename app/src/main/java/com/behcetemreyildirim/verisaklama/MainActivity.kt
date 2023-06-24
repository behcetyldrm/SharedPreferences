package com.behcetemreyildirim.verisaklama

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.behcetemreyildirim.verisaklama.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var sharedPreferences : SharedPreferences //lateinit -> değeri sonradan atanacak anlamı verir
    var alinanKullaniciAdi : String? = null //string nullability istendiği için böyle verdik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //SharedPreferences

        sharedPreferences = this.getSharedPreferences("com.behcetemreyildirim.verisaklama", Context.MODE_PRIVATE)

        alinanKullaniciAdi = sharedPreferences.getString("kullanici", "test") //kaydedilen veriyi aldık

        if (alinanKullaniciAdi != null){
            binding.textView.text = "Kaydedilen Kullanıcı Adı: ${alinanKullaniciAdi}" //uygulama kapanıp açıldığında yazılan yazı gözükmeye devam eder
        }
    }

    fun kaydet(view: View){

        var kullaniciAdi = binding.editText.text.toString()

        if (kullaniciAdi == ""){

            Toast.makeText(this, "Kullanıcı adını boş bırakmayın", Toast.LENGTH_LONG).show()
        }
        else {
            sharedPreferences.edit().putString("kullanici", kullaniciAdi).apply() //veriyi kaydettik

            binding.textView.text = "Kaydedilen Kullanıcı Adı ${kullaniciAdi}"
        }
    }

    fun sil(view: View){

        alinanKullaniciAdi = sharedPreferences.getString("kullanici", "")

        if (alinanKullaniciAdi != null){
            binding.textView.text = "Kaydedilen Kullanıcı Adı: "
            sharedPreferences.edit().remove("kullanici").apply() //veriyi siler
        }
    }
}
package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_yapilacaklar_kayit.*

class YapilacaklarKayitActivity : AppCompatActivity() {

    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yapilacaklar_kayit)


        toolbarKayitActivity.title = "Yapılacak İş Kayıt"
        setSupportActionBar(toolbarKayitActivity)

        vt = VeritabaniYardimcisi(this@YapilacaklarKayitActivity)

        buttonKaydet.setOnClickListener {

            val yapilacak_is = EditTextKayit.text.toString()
            isKayit(yapilacak_is)
        }
    }

    fun isKayit(yapilacak_is:String){

        Yapilacaklardao().isEkle(vt, yapilacak_is)

        startActivity(Intent(this@YapilacaklarKayitActivity, MainActivity::class.java))
    }
}
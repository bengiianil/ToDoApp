package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_yapilacaklar_detay.*

class YapilacaklarDetayActivity : AppCompatActivity() {

    private lateinit var vt: VeritabaniYardimcisi
    private lateinit var yapilacak: Yapilacaklar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yapilacaklar_detay)


        vt = VeritabaniYardimcisi(this@YapilacaklarDetayActivity)

        // kisi = intent.getSerializableExtra("nesne") as Kisiler

        yapilacak = intent.getSerializableExtra("nesne") as Yapilacaklar

        //        editTextKisiAd.setText(kisi.kisi_ad)
        //        editTextKisiTel.setText(kisi.kisi_tel)

        EditTextDetay.setText(yapilacak.yapilacak_is)

        toolbarDetayActivity.title = "Yapılacak İş Detay"
        setSupportActionBar(toolbarDetayActivity)


        buttonGuncelle.setOnClickListener {

            val yapilacak_is = EditTextDetay.text.toString()

            isDetay(yapilacak.yapilacak_id, yapilacak_is)

        }
    }

    fun isDetay(yapilacak_id:Int, yapilacak_is:String){

        Yapilacaklardao().isGuncelle(vt, yapilacak_id, yapilacak_is)

        startActivity(Intent(this@YapilacaklarDetayActivity, MainActivity::class.java))
    }
}
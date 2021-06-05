package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{

    private lateinit var YapilacaklarListe: ArrayList<Yapilacaklar>
    private lateinit var adapter: YapilacaklarAdapter
    private lateinit var vt: VeritabaniYardimcisi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        veritabaniKopyala()

        vt = VeritabaniYardimcisi(this@MainActivity)


        toolbarMainActivity.title = "Yapılacaklar"
        setSupportActionBar(toolbarMainActivity)


        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this@MainActivity)

        tumYapilacaklar()


        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, YapilacaklarKayitActivity::class.java))
        }

    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_search_menu,menu)
        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this@MainActivity)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        isArama(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        isArama(newText)
        return true
    }


    fun veritabaniKopyala(){
        val copyHelper = DatabaseCopyHelper(this@MainActivity)

        try{
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }
        catch(e: IOException){
            e.printStackTrace()
        }
    }

    fun tumYapilacaklar(){

        YapilacaklarListe = Yapilacaklardao().tumIsler(vt)

        adapter = YapilacaklarAdapter(this,YapilacaklarListe, vt)
        rv.adapter = adapter

    }

    fun isArama(aranacakKelime:String){

        YapilacaklarListe = Yapilacaklardao().isAra(vt, aranacakKelime)

        adapter = YapilacaklarAdapter(this, YapilacaklarListe, vt)
        rv.adapter = adapter
    }



}
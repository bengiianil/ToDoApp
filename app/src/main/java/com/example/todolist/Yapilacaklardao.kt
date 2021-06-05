package com.example.todolist

import android.content.ContentValues

class Yapilacaklardao {

    fun tumIsler(vt:VeritabaniYardimcisi):ArrayList<Yapilacaklar>{

        val db = vt.writableDatabase

        var YapilacaklarListe = ArrayList<Yapilacaklar>()

        val cursor = db.rawQuery("SELECT * FROM todolist", null)

        while(cursor.moveToNext()){

            val yapilacakIs = Yapilacaklar(cursor.getInt(cursor.getColumnIndex("yapilacak_id")),
                cursor.getString(cursor.getColumnIndex("yapilacak_is")))

            YapilacaklarListe.add(yapilacakIs)
        }
        return YapilacaklarListe
    }

    fun isAra(vt:VeritabaniYardimcisi, aranacakKelime:String):ArrayList<Yapilacaklar>{

        val db = vt.writableDatabase

        var YapilacaklarListe = ArrayList<Yapilacaklar>()

        var cursor = db.rawQuery("SELECT * FROM todolist WHERE yapilacak_is like '%$aranacakKelime%'", null)

        while(cursor.moveToNext()){

            val yapilacakIs = Yapilacaklar(cursor.getInt(cursor.getColumnIndex("yapilacak_id")),
                cursor.getString(cursor.getColumnIndex("yapilacak_is")))

            YapilacaklarListe.add(yapilacakIs)
        }
        return YapilacaklarListe
    }

    fun isEkle(vt: VeritabaniYardimcisi, yapilacak_is: String){

        val db = vt.writableDatabase

        val eklenen = ContentValues()

        eklenen.put("yapilacak_is", yapilacak_is)

        db.insertOrThrow("todolist", null, eklenen)

        db.close()
    }

    fun isGuncelle(vt: VeritabaniYardimcisi, yapilacak_id:Int, yapilacak_is:String){

        val db = vt.writableDatabase

        val guncel = ContentValues()

        guncel.put("yapilacak_is", yapilacak_is)

        db.update("todolist", guncel, "yapilacak_id=?", arrayOf(yapilacak_id.toString()))

        db.close()
    }

    fun isSil(vt: VeritabaniYardimcisi, yapilacak_id:Int){

        val db = vt.writableDatabase

        db.delete("todolist", "yapilacak_id=?", arrayOf(yapilacak_id.toString()))

        db.close()

    }
}
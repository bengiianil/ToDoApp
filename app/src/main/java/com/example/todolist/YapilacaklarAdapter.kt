package com.example.todolist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class YapilacaklarAdapter(var mContext: Context, var YapilacaklarListe: ArrayList<Yapilacaklar>, var vt: VeritabaniYardimcisi)
    :RecyclerView.Adapter<YapilacaklarAdapter.CardTasarimTutucu>(){

    inner class CardTasarimTutucu(view:View): RecyclerView.ViewHolder(view){

        var isAdi: TextView
        var satirIcon: ImageView
        var satirCardView: CardView


        init{

            isAdi = view.findViewById(R.id.isAdi)
            satirIcon = view.findViewById(R.id.satirIcon)
            satirCardView = view.findViewById(R.id.satirCardView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {

        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.satir_tasarim, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        var yapilacak = YapilacaklarListe.get(position)

        holder.isAdi.text = "${yapilacak.yapilacak_is}"

        holder.satirIcon.setOnClickListener {

            Toast.makeText(mContext, "${yapilacak.yapilacak_is} silindi", Toast.LENGTH_SHORT).show()

            // silme işlemini gerçekleştirebilmek için;

            Yapilacaklardao().isSil(vt, yapilacak.yapilacak_id)
            YapilacaklarListe = Yapilacaklardao().tumIsler(vt)
            notifyDataSetChanged()
        }

        holder.satirCardView.setOnClickListener {

            var intent = Intent(mContext, YapilacaklarDetayActivity::class.java)
            intent.putExtra("nesne", yapilacak)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return YapilacaklarListe.size
    }

}

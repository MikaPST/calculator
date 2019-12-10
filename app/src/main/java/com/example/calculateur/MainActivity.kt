package com.example.calculateur

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


/* METHODE 1

    Créer un listener:
        Une class qui implémente l'interface On.... Listener
        Ecrire la(les) méthode(s) exigée(s) par l'interface (ALT + ENTREE)

    Associer ce listener à l'objet (au composant)
        Exemple : rbEUR.setOnLongClickListener(new classe)

 --------------------------------------------------------------------------------
   METHODE 2

   Faire que l'activité implémente l'interface On.... Listener
   Ecrire la(les) méthode(s) exigée(s) par l'interface (ALT + ENTREE)
   Associer à l'objet :
        rbEur.setOnLongclickListener(this)

 --------------------------------------------------------------------------------
 METHODE 3

    Associer à l'objet un objet anonyme (créé à la volée)
        rbEUR.setOnLongClickListener(new OnLongClickListener() {
            Ecrire la(les) méthode(s) exigée(s) par l'interface (ALT + ENTREE)
        }
 */

class MainActivity : AppCompatActivity(){

    val TAUX_EUROS: Double = 1.0
    val TAUX_DOLLARS: Double = 1.185
    val TAUX_LIVRES: Double = 0.88
    val TAUX_BITCOINS: Double = 0.0001
    var ancienneMonnaie: String = "EUR"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvScreen.text = "0"
        rbEUR.setOnLongClickListener(View.OnLongClickListener {convertirEUR(); false})
        rbUSD.setOnLongClickListener(View.OnLongClickListener {convertirUSD(); false})
    }

    fun touche(view: View) {
        var tvValue: String = tvScreen.text.toString()
        if(tvValue == "0") {
            tvScreen.text = null
            var b: Button = view as Button
            tvScreen.setText(tvScreen.text.toString() + b.text.toString())
            bip()
        }else{
            var b: Button = view as Button
            tvScreen.setText(tvScreen.text.toString() + b.text.toString())
            bip()
        }
    }

    fun delete(view: View) {
        tvScreen.text=("").toString()
        bip()
    }

    fun convertisseurDevise(view: View) {
        var valeur: Double = tvScreen.text.toString().toDouble()
        var euros: Double = 0.0

        if(ancienneMonnaie == "EUR") {euros = valeur / TAUX_EUROS}
        if(ancienneMonnaie == "USD") {euros = valeur / TAUX_DOLLARS}
        if(ancienneMonnaie == "LIV") {euros = valeur / TAUX_LIVRES}
        if(ancienneMonnaie == "BTC") {euros = valeur / TAUX_BITCOINS}

        if(view.id == R.id.rbEUR) {
            valeur = euros * TAUX_EUROS
            ancienneMonnaie = "EUR"
        }
        if(view.id == R.id.rbUSD) {
            valeur = euros * TAUX_DOLLARS
            ancienneMonnaie = "USD"
        }
        if(view.id == R.id.rbLIV) {
            valeur = euros * TAUX_LIVRES
            ancienneMonnaie = "LIV"
        }
        if(view.id == R.id.rbBTC) {
            valeur = euros * TAUX_BITCOINS
            ancienneMonnaie = "BTC"
        }
        tvScreen.setText(valeur.toString())
        bip()
    }


    fun convertirEUR() {
        var valeur: Double = tvScreen.text.toString().toDouble()
        tvScreen.text = ("").toString()
        var euros: Double = valeur * TAUX_EUROS
        tvScreen.setText(String.format("%.2f", euros))
    }


    fun convertirUSD() {
        var valeur: Double = tvScreen.text.toString().toDouble()
        tvScreen.text = ("").toString()
        var dollars: Double = valeur * TAUX_DOLLARS
        tvScreen.setText(String.format("%.2f", dollars))
    }


    fun convertirLIV() {
        var valeur: Double = tvScreen.text.toString().toDouble()
        tvScreen.text = ("").toString()
        var livres: Double = valeur * TAUX_LIVRES
        tvScreen.setText(String.format("%.2f", livres))
    }

    fun bip(){
        val tg = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)
        tg.startTone(ToneGenerator.TONE_PROP_BEEP)
    }

    fun result(view: View) {


    }

}


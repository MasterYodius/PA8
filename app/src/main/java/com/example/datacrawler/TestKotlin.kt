package com.example.datacrawler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class TestKotlin : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_kotlin)
        val result = findViewById(R.id.result) as TextView
        val qrCode = findViewById(R.id.qrCode) as TextView
        val getButton = findViewById(R.id.getButton) as Button
        getButton.setOnClickListener { getWebsite() }

        qrCode.setOnClickListener { startActivity(Intent(applicationContext, ScanQrCode::class.java)) }


    }

    private fun getWebsite() {
        val doc: Document? = null

        Thread(Runnable {
            val builder = StringBuilder()
            try {
                val doc = Jsoup.connect("http://base-donnees-publique.medicaments.gouv.fr/affichageDoc.php?specid=60234100&typedoc=R").get()
                val title = doc.title()
                val links = doc.select("a[href]")
                val content = doc.body().text()
                builder.append(title).append("\n")
                builder.append("-------------------------")
                builder.append(content)
                /*for(Element link:links){
                builder.append("\n").append("Link : ").append(link.attr("href"))
                        .append("\n").append("Text : ").append(link.text());
            }*/
            } catch (e: IOException) {
                builder.append("Error : ").append(e.message).append("\n")
            }

            runOnUiThread { result.text = builder.toString() }
        }).start()

    }
}

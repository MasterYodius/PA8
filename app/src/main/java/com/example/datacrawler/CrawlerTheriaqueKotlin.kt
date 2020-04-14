package com.example.datacrawler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.net.MalformedURLException


class CrawlerTheriaqueKotlin : AppCompatActivity() {

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
                val doc = Jsoup.connect("http://www.theriaque.org/apps/interaction/itr_ordonnance.php?id_page=10&rd_profil=1&list_tranche_age=K&rd_sexe=XY&list_age_enfant=0&list_poids_enfant=0&list_age_nourisson=0&list_poids_nourisson=0&list_age_nouveau_ne=0&list_poids_nouveau_ne=0&list_femme=0&list_allergie=&list_patho=&pathologie%5B%5D=A9&list_alimentation=&txt_valeur=&flags%5B%5D=0_34984&specialite%5B%5D=15130&id_page=10").get()
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


    @Throws(FailingHttpStatusCodeException::class, MalformedURLException::class, IOException::class)
    fun autoLogin(loginUrl: String, login: String, password: String): WebClient {
        val client = WebClient()
        client.getOptions().setCssEnabled(false)
        client.getOptions().setJavaScriptEnabled(false)

        var page = client.getPage(loginUrl)

        val inputPassword = page.getFirstByXPath("//input[@type='password']")
        //The first preceding input that is not hidden
        val inputLogin = inputPassword.getFirstByXPath(".//preceding::input[not(@type='hidden')]")

        inputLogin.setValueAttribute(login)
        inputPassword.setValueAttribute(password)

        //get the enclosing form
        val loginForm = inputPassword.getEnclosingForm()

        //submit the form
        page = client.getPage(loginForm.getWebRequest(null))

        //returns the cookie filled client :)
        return client
    }




}

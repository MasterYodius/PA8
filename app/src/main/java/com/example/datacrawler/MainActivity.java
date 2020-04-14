package com.example.datacrawler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static TextView qrCode;
    private Button getButton;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        qrCode = (TextView) findViewById(R.id.qrCode);
        getButton = (Button) findViewById(R.id.getButton);
        qrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),ScanQrCode.class));
                startActivity(new Intent(getApplicationContext(),CrawlerTheriaqueKotlin.class));
            }
        });



    }

}

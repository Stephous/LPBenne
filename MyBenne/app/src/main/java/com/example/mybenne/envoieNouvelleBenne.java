package com.example.mybenne;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class envoieNouvelleBenne extends AppCompatActivity {

    Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoie_nouvelle_benne);

        retour = findViewById(R.id.retour);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retour();
            }
        });
    }

    protected void retour()
    {
        Intent intent1 = new Intent();
        intent1.setClass(this, MainActivity.class);

        Intent intent2 = getIntent();
        if (intent2!= null)
        {
            Bundle extras2 = intent2.getExtras();
            if (extras2 != null)
            {
                intent1.putExtra("Nom", extras2.getString("Nom"));
            }
            startActivity(intent1);
        }
    }
}

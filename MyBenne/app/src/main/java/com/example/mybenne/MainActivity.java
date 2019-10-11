package com.example.mybenne;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    String test;
    String WAZAAA;
    String plop;

    Button modifierInfoUtilisateur;
    Button modifierInfoDestinataire;
    Button envoieNouvelleBenne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modifierInfoUtilisateur = findViewById(R.id.modifierInfoUtilisateur);
        modifierInfoDestinataire = findViewById(R.id.modifierInfoDestinataire);
        envoieNouvelleBenne = findViewById(R.id.envoieNouvelleBenne);

        modifierInfoUtilisateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameClasse = "modifierInfoUtilisateur";
                changementVue(nameClasse);
            }
        });
        modifierInfoDestinataire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameClasse = "modifierInfoDestinataire";
                changementVue(nameClasse);
            }
        });
        envoieNouvelleBenne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameClasse = "envoieNouvelleBenne";
                changementVue(nameClasse);
            }
        });
    }

    protected void changementVue(String nameClasse)
    {
        Intent intent1 = new Intent();
        intent1.setClass(this, System.out.println(nameClasse).class);

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

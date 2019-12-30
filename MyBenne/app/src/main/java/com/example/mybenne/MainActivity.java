package com.example.mybenne;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button afficher;
    Button modifierInfoUtilisateur;
    Button modifierInfoDestinataire;
    Button envoieNouvelleBenne;
    Button MAP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modifierInfoUtilisateur = findViewById(R.id.modifierInfoUtilisateur);
        modifierInfoDestinataire = findViewById(R.id.modifierInfoDestinataire);
        envoieNouvelleBenne = findViewById(R.id.envoieNouvelleBenne);
        MAP = findViewById(R.id.MAP);
        afficher = findViewById(R.id.afficher);

        modifierInfoUtilisateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifierInfoUtilisateur();
            }
        });
        modifierInfoDestinataire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifierInfoDestinataire();
            }
        });

        envoieNouvelleBenne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoieNouvelleBenne();
            }
        });
        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                affichage();
            }
        });
        MAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Geolocalisation.class));
            }
        });
    }

    protected void modifierInfoUtilisateur()
    {
        Intent intent1 = new Intent();
        intent1.setClass(this, modifierInfoUtilisateur.class);

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

    protected void modifierInfoDestinataire()
    {
        Intent intent1 = new Intent();
        intent1.setClass(this, modifierInfoDestinataire.class);

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

    protected void envoieNouvelleBenne()
    {
        Intent intent1 = new Intent();
        intent1.setClass(this, envoieNouvelleBenne.class);

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
    protected void affichage()
    {
        Intent intent1 = new Intent();
        intent1.setClass(this, affichagedonnees.class);

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

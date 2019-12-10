package com.example.mybenne;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class affichagedonnees extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichagedonnees);
        DatabaseManager db;
        db = new DatabaseManager(this);

        Cursor data_destinataire=db.getAllData("destinataire");
               if(data_destinataire.getCount()==0) {
                   showMessage("Error", "Aucune donnée");
                   return ;
                }
        StringBuffer buffer_destinataire = new StringBuffer();
        data_destinataire.moveToLast();
        buffer_destinataire.append("Nom destinataire : " + data_destinataire.getString(0)+"\n");
        buffer_destinataire.append("Prenom destinataire : " + data_destinataire.getString(1)+"\n");
        buffer_destinataire.append("Telephone destinataire : " + data_destinataire.getString(2)+"\n");
        //showMessage("Data", buffer.toString());

    Cursor data_utilisateur=db.getAllData("utilisateur");
               if(data_destinataire.getCount()==0) {
        showMessage("Error", "Aucune donnée");
        return ;
    }
    StringBuffer buffer_utilisateur = new StringBuffer();
        data_destinataire.moveToLast();
        buffer_utilisateur.append("Nom utilisateur : " + data_utilisateur.getString(0)+"\n");
        buffer_utilisateur.append("Prenom utilisateur : " + data_utilisateur.getString(1)+"\n");
        buffer_utilisateur.append("Telephone utilisateur : " + data_utilisateur.getString(2)+"\n");
    //showMessage("Data", buffer.toString());
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    };
}

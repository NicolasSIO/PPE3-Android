package fr.gsb.rv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.R.*;

import java.util.ArrayList;
import java.util.Calendar;

public class RechercheRvActivity extends Activity{

    private static final String [] lesMois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
    Spinner spMois;
    Spinner spAnnee;
    Button bListe;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_rv_activity);
        spMois = (Spinner) findViewById(R.id.spMois);
        spAnnee = (Spinner) findViewById(R.id.spAnnee);
        bListe = (Button) findViewById(R.id.bListe);

        ArrayAdapter<String> unMois = new ArrayAdapter<>(this, layout.simple_spinner_dropdown_item, lesMois);
        spMois.setAdapter(unMois);

        ArrayList<String> annee = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);

        for(int i = 2010; i<= thisYear; i++){
            annee.add(Integer.toString(i));
        }

        ArrayAdapter<String> uneAnnee = new ArrayAdapter<>(this, layout.simple_spinner_dropdown_item, annee);
        spAnnee.setAdapter((SpinnerAdapter) uneAnnee);
    }

    public void Lister(View vue){
        Bundle paquet = new Bundle();
        paquet.putString("dateMois", spMois.getSelectedItem().toString());
        paquet.putString("dateAnnee", spAnnee.getSelectedItem().toString());

        Intent intentionLister = new Intent(this, ListerRvActivity.class);
        intentionLister.putExtras(paquet);

        startActivity(intentionLister);
    }
}

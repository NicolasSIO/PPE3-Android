package fr.gsb.rv;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SaisirRvActivity  extends AppCompatActivity {

    TextView tvDateCommande;
    TextView tv_date_commande;
    TextView tvDateSelectionner;
    EditText etBilan;
    Button bValider;
    Button bAnnuler;
    Button bSelectionnerDate;
    Spinner spPraticien;
    Spinner spMotif;
    Spinner spCoeffConfiance;
    GregorianCalendar laDateCommande;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saisir_rv_activity);
        tvDateCommande = (TextView) findViewById(R.id.tvDateCommande);
        tv_date_commande = (TextView) findViewById(R.id.tv_date_commande);
        etBilan = (EditText) findViewById(R.id.etBilan);
        bValider = (Button) findViewById(R.id.bValider);
        bAnnuler = (Button) findViewById(R.id.bAnnuler);
        bSelectionnerDate = (Button) findViewById(R.id.bSelectionnerDate);
        spPraticien = (Spinner) findViewById(R.id.spPraticien);
        spMotif = (Spinner) findViewById(R.id.spMotif);
        spCoeffConfiance = (Spinner) findViewById(R.id.spCoeffConfiance);

    }

    DatePickerDialog.OnDateSetListener ecouteurDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String dateCommande = String.format("%02d/%02d/%02d", dayOfMonth, monthOfYear +1, year);
            tvDateSelectionner.setText( dateCommande);
            laDateCommande = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        }
    };

    public void selectionnerDateCommande(View view) {
        GregorianCalendar aujourdhui = new GregorianCalendar();

        int jour = aujourdhui.get(Calendar.DAY_OF_MONTH);
        int mois = aujourdhui.get(Calendar.MONTH);
        int annee = aujourdhui.get(Calendar.YEAR);

        new DatePickerDialog(this, ecouteurDate, annee, mois, jour).show();
    }

    public void saisir(View vue){

        Intent intentionValider = new Intent(this, MenuRvActivity.class );

        startActivity(intentionValider);
    }

    public void annuler(View vue){
        Intent intentionAnnuler = new Intent(this, MenuRvActivity.class );

        startActivity(intentionAnnuler);
    }
}

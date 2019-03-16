package fr.gsb.rv;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ListerRvActivity extends Activity{

    TextView tvListeDateMois;
    TextView tvListeDateAnnee;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_rv_activity);
        tvListeDateMois = (TextView) findViewById(R.id.tvListeDateMois);
        tvListeDateAnnee = (TextView) findViewById(R.id.tvListeDateAnnee);
        tvListeDateMois.setText(getIntent().getExtras().getString("dateMois"));
        tvListeDateAnnee.setText(getIntent().getExtras().getString("dateAnnee"));

    }



}

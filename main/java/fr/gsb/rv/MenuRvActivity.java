package fr.gsb.rv;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

import fr.gsb.rv.entites.Visiteur;
import fr.gsb.rv.technique.Session;

public class MenuRvActivity extends Activity {

    private final String Tag="GSB_MAIN_ACTIVITY";
    TextView tvNomSession;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_rv_activity);
        tvNomSession = (TextView) findViewById(R.id.tvNomSession);

        Bundle paquet = this.getIntent().getExtras();
        String matricule = paquet.getString("matricule");
        String mdp = paquet.getString("mdp");

        Toast.makeText(MenuRvActivity.this, matricule, Toast.LENGTH_LONG).show();

        String url = String.format("http://192.168.104.208:5000/visiteurs/%s/%s",matricule ,mdp);
        Response.Listener<JSONObject> ecouteurReponse = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Visiteur unVisiteur = new Visiteur();
                    unVisiteur.setPrenom(response.getString("vis_prenom"));
                    unVisiteur.setNom(response.getString("vis_nom"));

                    if(unVisiteur != null){
                        tvNomSession.setText(unVisiteur.getPrenom().concat(" ").concat(unVisiteur.getNom()));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener ecouteurErreur = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(Tag, "Erreur HTTP : " + error.getMessage());
            }
        };

        JsonObjectRequest requete = new JsonObjectRequest(Request.Method.GET, url, null, ecouteurReponse, ecouteurErreur);

        RequestQueue fileRequetes = Volley.newRequestQueue(this);
        fileRequetes.add(requete);

    }

    public void Consulter(View vue){
        Intent intentionConsulter = new Intent(this, RechercheRvActivity.class );

        startActivity(intentionConsulter);
    }

    public void Saisir(View vue){
        Intent intentionSaisir = new Intent(this, SaisirRvActivity.class);

        startActivity(intentionSaisir);

    }

}

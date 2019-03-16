package fr.gsb.rv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    private final String Tag="GSB_MAIN_ACTIVITY";
    TextView tvErreur;
    TextView textValide;
    EditText etMatricule;
    EditText etMdp;
    Button bValider;
    Button bAnnuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvErreur = (TextView) findViewById(R.id.tvErreur);
        textValide = (TextView) findViewById(R.id.textValide);
        etMatricule = (EditText) findViewById(R.id.etMatricule);
        etMdp = (EditText) findViewById(R.id.etMdp);
        bValider = (Button) findViewById(R.id.bValider);
        bAnnuler = (Button) findViewById(R.id.bAnnuler);
        Log.i(Tag,"Création de l'activité principale");
    }

    public void seConnecter(View vue) {

        String matricule = URLEncoder.encode(etMatricule.getText().toString());
        String mdp = URLEncoder.encode(etMdp.getText().toString());

        String url = String.format("http://192.168.104.208:5000/visiteurs/%s/%s",matricule ,mdp);
        Response.Listener<JSONObject> ecouteurReponse = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Visiteur unVisiteur = new Visiteur();
                    unVisiteur.setMatricule(response.getString("vis_matricule"));
                    unVisiteur.setMdp(response.getString("vis_mdp"));
                    Session.ouvrir(unVisiteur);

                    if(unVisiteur != null) {
                        Toast.makeText(MainActivity.this, "Connecté", Toast.LENGTH_LONG).show();

                        Bundle paquet = new Bundle();
                        paquet.putString("matricule", String.valueOf(unVisiteur.getMatricule()));
                        paquet.putString("mdp", String.valueOf(unVisiteur.getMdp()));

                        Intent intentionSeConnecter = new Intent(MainActivity.this, MenuRvActivity.class );
                        intentionSeConnecter.putExtras(paquet);

                        startActivity(intentionSeConnecter);
                    }else{
                        tvErreur.setText("Echec à la connexion. Recommencez...");
                    }

                    Log.i(Tag, String.valueOf(unVisiteur));
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
        /*
        Visiteur v = ModeleGsb.getInstance().seConnecter(etMatricule.getText().toString(), etMdp.getText().toString());

        Session.ouvrir(v);

        if(v != null) {
            Toast.makeText(this, "Connecté", Toast.LENGTH_LONG).show();
            Log.i(Tag,"Connexion Ok".concat(v.getNom().concat(v.getPrenom())));
            Intent intentionSeConnecter = new Intent(this, MenuRvActivity.class );
            intentionSeConnecter.putExtras(paquet);
            startActivity(intentionSeConnecter);
        }else{
            textValide.setText("");
            tvErreur.setText("Echec à la connexion. Recommencez...");
            Log.i(Tag,"Connexion Nok");
        }
    }*/

    public void annuler(View vue){
        Log.i(Tag,"Initialisation des champs");
        etMatricule.setText("");
        etMdp.setText("");
        tvErreur.setText("");
        textValide.setText("");
    }
}

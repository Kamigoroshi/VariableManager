package piette.dylan.examenandroidpiette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Dylan on 16/12/2017.
 */
//Nous sommes sur la dashboard de l'utilisateur//


public class Dashboard extends AppCompatActivity {

    Button btn_administration;
    Button btn_liste;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        Log.d("Info","On arrive à la dashboard");

        //Activer la vue Administration
        btn_administration = (Button)findViewById(R.id.btn_administration);
        btn_administration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                administration();
            }
        });

        //activer vue liste
        btn_liste = (Button)findViewById(R.id.btn_listeVariable);
        btn_liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                affichliste();
            }
        });

    }

    private void affichliste() {
        Intent intent = new Intent(Dashboard.this,ListActivity.class);
        startActivity(intent);
    }


    //Activer la vue Administration
    private void administration() {
        Intent intent = new Intent(Dashboard.this,Administration.class);
        startActivity(intent);
    }

}

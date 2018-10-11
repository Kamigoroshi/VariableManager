package piette.dylan.examenandroidpiette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan on 16/12/2017.
 */

public class ListActivity extends AppCompatActivity{

    ListView lv_listeVariable;
    private DatabaseManager databaseManager;
    Button btn_dashboard;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listvariable);
        databaseManager = new DatabaseManager(this);
        lv_listeVariable= (ListView)findViewById(R.id.lvVariable);
        afficherVariables();

        //Afficher la Dashboard
        btn_dashboard = (Button)findViewById(R.id.btn_dashboard);
        btn_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                affichDashboard();
            }
        });


        databaseManager.close();
    }

    //Afficher la dashboard
    private void affichDashboard() {
        Intent intent = new Intent(this,Dashboard.class);
        startActivity(intent);
    }

    private List<VariableData> genererVariable(){
        List<VariableData> variables = new ArrayList<VariableData>();
        variables = databaseManager.readAllVariable();
        return variables;
    }

    private void afficherVariables(){
        VariableAdapter adapter = new VariableAdapter(this, genererVariable());
        lv_listeVariable.setAdapter(adapter);
        Log.d("Info","J'affiche la liste dans la classe liste");
    }

}

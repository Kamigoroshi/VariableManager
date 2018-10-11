package piette.dylan.examenandroidpiette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dylan on 16/12/2017.
 */

public class Administration extends AppCompatActivity {

    Button btn_ajoutVariable;
    Button btn_suppVariable;
    Button btn_Dashboard;
    EditText et_nomVariable;
    EditText et_valeurVariable;
    ListView lv_administration;
    ArrayAdapter<String> arrayAdapter;
    //Déclaration base de donnés
    private DatabaseManager databaseManager;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminisration);
        //Déclarations objets visuels//
        databaseManager = new DatabaseManager(this);

        et_nomVariable = (EditText) findViewById(R.id.et_nomVariable);
        et_valeurVariable = (EditText) findViewById(R.id.et_valeurVariable);
        lv_administration = (ListView) findViewById(R.id.lv_administration);


        //Ajouter Variable//
        btn_ajoutVariable = (Button) findViewById(R.id.btn_ajoutAdminVariable);
        btn_ajoutVariable.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ajoutVariable();
            }
        });

        //Supprimer Variable//
        btn_suppVariable = (Button) findViewById(R.id.btn_suppAdminVariable);
        btn_suppVariable.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                suppVariable();
            }
        });

        //Aller sur la Dashboard//
        btn_Dashboard = (Button)findViewById(R.id.btn_admiDashboard);
        btn_Dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dashBoard();
            }
        });


        //Quand la vue s'affiche on affiche toutes les variables
        afficherVariables();

    }




    //Fonctions//

    private List<VariableData> genererVariable() {
        List<VariableData> variables = new ArrayList<VariableData>();
        variables = databaseManager.readAllVariable();
        return variables;
    }

    //Aller à la dashboard//
    private void dashBoard() {
        Intent intent = new Intent(Administration.this,Dashboard.class);
        startActivity(intent);
    }

    //Fonctions d'affichage des variables, peut servir de refresh
    private void afficherVariables() {
        //On récupère une liste de la base de données et on la passe à la classe VariableAdapter
        VariableAdapter adapter = new VariableAdapter(this, genererVariable());
        lv_administration.setAdapter(adapter);
    }

    //////Ajout d'une variable/////

    private void ajoutVariable() {

        boolean verif;
        verif = false;
        String nomVariable = et_nomVariable.getText().toString(); //On met ce qu'on a dans les champs dans des variables//
        String valeurVariable = et_valeurVariable.getText().toString();
        String nom;

        //verification si les champs sont complétés//
        if (nomVariable.equals("") || valeurVariable.equals("")) {
            et_nomVariable.setText("");
            et_valeurVariable.setText("");
            Log.d("Info", "Les champs variables ne sont pas remplis");
        } else {
            //Vérification si la variable existe déjà
            List<VariableData> variables = new ArrayList<VariableData>();
            variables = databaseManager.readAllVariable();
            for(VariableData variable : variables){
                nom = variable.getNomVariable();
                if(nom.equals(nomVariable)){
                    verif = true;
                    Log.d("Info","La variable existe déjà");
                }
            }
            if(!verif){
                databaseManager.insertVariable(nomVariable,valeurVariable);
                Log.d("Info","On a inséré la variable");
            }
        }
        et_valeurVariable.setText("");
        et_nomVariable.setText("");
        //Après avoir ajouté ou pas la variable on réaffiche la liste
        afficherVariables();

        databaseManager.close();
    }

    //////Suppression d'une variable/////////

    private void suppVariable(){

        String nomVariable;
        CheckBox cb_variable = lv_administration.findViewById(R.id.cb_variable);

        if(cb_variable.isChecked()){
            nomVariable = (String)cb_variable.getText();
        }else{
            nomVariable = "";
        }
        databaseManager.suppVariable(this,nomVariable);
        databaseManager.close();

        afficherVariables();
        et_nomVariable.setText("");
        et_valeurVariable.setText("");

    }




}

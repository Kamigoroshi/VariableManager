package piette.dylan.examenandroidpiette;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.List;

import piette.dylan.examenandroidpiette.DatabaseManager;
import piette.dylan.examenandroidpiette.R;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Déclarations des éléments visules
    Button btn_senregistrer;
    Button btn_login;
    EditText et_password;
    EditText et_username;
    TextView tvBienvenue;

    //Déclaration base de donnés
    private DatabaseManager databaseManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseManager = new DatabaseManager(this);

        //Déclaration des EditText pour pouvoir les effacer/////
        et_password = (EditText)findViewById(R.id.et_password);
        et_username = (EditText)findViewById(R.id.et_username);
        tvBienvenue = (TextView)findViewById(R.id.tvBienvenue);

        //Déclaration boutons et listener et fonctions/////////
        btn_senregistrer = (Button)findViewById(R.id.btn_senregistrer);
        btn_senregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creerUtilisateur();
            }
        });

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });



    }




    //Fonctions

    ///////////Création d'utilisateur///////////
    private void creerUtilisateur(){

        //Si les champs sont vides refus d'insérer le user
        if(et_username.getText().toString().equals("") || et_password.getText().toString().equals("")){
            tvBienvenue.setTextColor(getResources().getColor(R.color.rouge));
            tvBienvenue.setText("Rempli correctement les champs demandés");
        }else{
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();
            String nom;

            boolean verif;
            verif = false;


            //Vérification si le user existe déjà
            //On lit tous les users et on regarde si son nom est déjà présent
            List<UserData> users = databaseManager.readUsers();
            for (UserData user : users){
                nom = user.getName();
                if(username.equals(nom)){
                    verif = true;

                }
            }

            if(verif!=true){
                //L'utilisateur n'existait pas on le crée
                databaseManager.insertUser(username,password);
                Log.d("Création utilisateur", "L'utilisateur n'existait pas");
                tvBienvenue.setTextColor(getResources().getColor(R.color.vert));
                tvBienvenue.setText("Votre compte a bien été créé, bienvenue "+username+" !");
                et_password.setText("");
                et_username.setText("");
            }else{
                //L'utilisateur existe déjà
                tvBienvenue.setTextColor(getResources().getColor(R.color.rouge));
                tvBienvenue.setText("L'utilisateur existe déjà");
                et_password.setText("");
                et_username.setText("");
            }


            databaseManager.close();
        }


    }

    ///////Vérification de l'utilisateur et connexion///////////
    private void login(){

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        String nom;
        String pass;
        boolean verif;
        verif = false;

        //On lit tous les users//
        List<UserData> users = databaseManager.readUsers();
            for (UserData user : users){
                nom = user.getName();
                pass = user.getPassword();
                //On regarde si les noms et mots de passe correspondent
                if(username.equals(nom) && password.equals(pass)){
                    //Si c'est bon on passe un boolean à true
                    verif = true;
                }else{
                    verif = false;
                }

            }

            if(verif == false){
                et_username.setText("");
                et_password.setText("");
                tvBienvenue.setTextColor(getResources().getColor(R.color.rouge));
                tvBienvenue.setText("Tu t'es trompé d'identifiants ! Réessaye !");
            }else{
                Log.d("User","On arrive à se connecter");
                tvBienvenue.setTextColor(getResources().getColor(R.color.vert));
                tvBienvenue.setText("Bienvenue !");
                databaseManager.close();
                Intent intent = new Intent(MainActivity.this,Dashboard.class);
                startActivity(intent);

            }
        databaseManager.close();
    }


    @Override
    public void onClick(View view) {

    }


}

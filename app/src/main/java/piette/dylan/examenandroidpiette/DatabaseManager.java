package piette.dylan.examenandroidpiette;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan on 15/12/2017.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "User.db";
    private static final int DATABASE_VERSION = 4;
    private static final String VARIABLE_NAME = "nomVariable";
    private static final String VARIABLE_TABLE = "T_Variables";
    private DatabaseManager helper;
    private SQLiteDatabase db;


    public DatabaseManager(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Création de la DB au lancement de l'application
        String creaSQL = "create table T_Users ("
                +" idUser integer primary key autoincrement,"
                +" username text not null,"
                +" password text not null"
                +")";
        String crea2SQL = "create table T_Variables ("
                        +" idVariable integer primary key autoincrement,"
                        +" nomVariable text,"
                        +" valeurVariable text"
                        +")";
        db.execSQL(creaSQL);
        db.execSQL(crea2SQL);

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Fonction qui va vérifier si la base de données est toujours à jour, si elle ne l'est pas
        //Elle va effacer la table et la reconstruire
        String suppSQL = "drop table T_Users";
        String supp2SQL = "drop table T_Variables";
        db.execSQL(suppSQL);
        db.execSQL(supp2SQL);
        this.onCreate(db);
    }

    //insertion d'un user
    public void insertUser(String username, String password){
        username = username.replace("'","''");
        password = password.replace("'","''");

        String insertSQL = "insert into T_Users (username, password) values ('"
                + username + "', '" + password + "')";
        this.getWritableDatabase().execSQL(insertSQL);
        Log.d("USER", "Création utilisateur");
    }

    //Insertion d'une variable dans la table//
    public void insertVariable(String nomVariable, String valeurVariable){
        nomVariable = nomVariable.replace("'","''");
        valeurVariable = valeurVariable.replace("'","''");
        String insertSQL = "insert into T_Variables (nomVariable, valeurVariable) values('"
                + nomVariable + "', '" + valeurVariable + "')";
                this.getWritableDatabase().execSQL(insertSQL);
                Log.d("Variable", "Création de variable");
    }

    //lecture de tous les users//
    public List<UserData> readUsers(){
        List<UserData> users = new ArrayList<>();
        String strSQL = "select * from T_users";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSQL, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            UserData user = new UserData(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }


    //Lecture de toutes les variables//
    public List<VariableData> readAllVariable(){
        List<VariableData> variables = new ArrayList<>();
        String strSQL = "select * from T_Variables";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSQL, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            VariableData variable = new VariableData(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            variables.add(variable);
            cursor.moveToNext();
        }
        cursor.close();

        return variables;
    }


    //Supprimer une variable
    public void suppVariable(Context context, String nomVariableSupp) {
        helper = new DatabaseManager(context);
        db = helper.getWritableDatabase();
        db.delete(VARIABLE_TABLE, VARIABLE_NAME+"=?" ,new String[]{nomVariableSupp});
    }
}

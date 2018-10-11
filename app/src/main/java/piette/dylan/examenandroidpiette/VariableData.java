package piette.dylan.examenandroidpiette;

/**
 * Created by Dylan on 16/12/2017.
 */

public class VariableData {


    private int idVariable;
    private String nomVariable;
    private String valeurVariable;


    public VariableData(int idVariable, String nomVariable, String valeurVariable){
        this.idVariable = idVariable;
        this.nomVariable = nomVariable;
        this.valeurVariable = valeurVariable;
    }

    public int getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(int idVariable) {
        this.idVariable = idVariable;
    }

    public String getNomVariable() {
        return nomVariable;
    }

    public void setNomVariable(String nomVariable) {
        this.nomVariable = nomVariable;
    }

    public String getValeurVariable() {
        return valeurVariable;
    }

    public void setValeurVariable(String valeurVariable) {
        this.valeurVariable = valeurVariable;
    }



}

package piette.dylan.examenandroidpiette;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dylan on 20/12/2017.
 */

public class VariableAdapter extends ArrayAdapter<VariableData> {

    public VariableAdapter(Context context, List<VariableData> variables) {
        super(context, 0, variables);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.affichage_variable, parent, false);
        }

        VariableViewHolder viewHolder = (VariableViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new VariableViewHolder();
            viewHolder.nom = (TextView) convertView.findViewById(R.id.affich_nomVariable);
            viewHolder.valeur = (TextView) convertView.findViewById(R.id.affich_valeurVariable);
            viewHolder.cb_variable = (CheckBox) convertView.findViewById(R.id.cb_variable);
            convertView.setTag(viewHolder);
        }


        //getItem(position) va récupérer l'item [position] de la List<VariableData> variables
        VariableData variable = getItem(position);
        viewHolder.nom.setText(variable.getNomVariable());
        viewHolder.valeur.setText(variable.getValeurVariable());
        viewHolder.cb_variable.setText(variable.getNomVariable());

        return convertView;
    }




    private class VariableViewHolder {
        public TextView nom;
        public TextView valeur;
        public ImageView avatar;
        public CheckBox cb_variable;
    }




}

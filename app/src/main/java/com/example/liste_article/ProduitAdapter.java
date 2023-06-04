package com.example.liste_article;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.content.Context;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.TextView;


public class ProduitAdapter extends ArrayAdapter<Produit> {

    private Context context;
    private List<Produit> produits;

    public ProduitAdapter(Context context, List<Produit> produits) {
        super(context, 0, produits);
        this.context = context;
        this.produits = produits;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_produit, parent, false);
        }

        Produit produit = produits.get(position);

        TextView libelleTextView = view.findViewById(R.id.libelleTextView);
        TextView puTextView = view.findViewById(R.id.puTextView);

        libelleTextView.setText(produit.getLibelle());
        puTextView.setText(produit.getPu());

        return view;
    }
}

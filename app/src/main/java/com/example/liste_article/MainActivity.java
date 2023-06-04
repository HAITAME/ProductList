package com.example.liste_article;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText libelleEditText, puEditText;
    private Button addButton;
    private ListView listView;
    private DatabaseHelper databaseHelper;
    private ProduitAdapter produitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        libelleEditText = findViewById(R.id.libelle);
        puEditText = findViewById(R.id.pu);
        addButton = findViewById(R.id.button);
        listView = findViewById(R.id.listView);

        databaseHelper = new DatabaseHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String libelle = libelleEditText.getText().toString().trim();
                String pu = puEditText.getText().toString().trim();

                if (!TextUtils.isEmpty(libelle) && !TextUtils.isEmpty(pu)) {
                    Produit produit = new Produit(libelle, pu);
                    long rowId = databaseHelper.insertProduit(produit);
                    if (rowId != -1) {
                        Toast.makeText(MainActivity.this, "Produit ajouté avec succès", Toast.LENGTH_SHORT).show();
                        libelleEditText.setText("");
                        puEditText.setText("");
                        updateProduitList();
                    } else {
                        Toast.makeText(MainActivity.this, "Erreur lors de l'ajout du produit", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateProduitList();
    }

    private void updateProduitList() {
        List<Produit> produits = databaseHelper.getAllProduits();
        produitAdapter = new ProduitAdapter(this, produits);
        listView.setAdapter(produitAdapter);
    }
}

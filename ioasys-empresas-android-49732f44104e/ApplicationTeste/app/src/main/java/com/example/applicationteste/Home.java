package com.example.applicationteste;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationteste.domain.LoginCredenciado;
import com.example.applicationteste.help.Preferencia;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class Home extends AppCompatActivity {
    private SearchView searchView;
    private MenuItem menuItemSearch;
    private RecyclerView mRecyclerView;

    private ArrayAdapter<String> adapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Pesquisar");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    String [] list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("");

        listView = (ListView) findViewById(R.id.listViewCategorias);

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);

        listView.setAdapter(arrayAdapter);

    }

//    public void setAdapterBuscarListView(List<String> vitrineList, Context context){
//        adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, vitrineList) {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//                TextView text = (TextView) view.findViewById(android.R.id.text1);
//                text.setTextColor(Color.BLACK);
//                return view;
//            }
//        };
//
//        lv.setAdapter(adapter);
////        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
////        lv.setLayoutParams(layoutParams);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent,
//                                    View view, int position, long id) {
//                TextView texto = (TextView)view;
//                menuItemSearch.collapseActionView();
//                //snack(view, texto.getText().toString());
////                Intent intent = new Intent(getContext(), FiltroDePrecosActivity.class);
////                intent.putExtra("categoria",  texto.getText().toString());
////                VitrinePrecoDB db = new VitrinePrecoDB(getContext());
////                intent.putExtra("descricaoCategoriaMaster",  db.obterCategoriaMasterDistinta(texto.getText().toString()));
////                startActivity(intent);
//
//            }
//        });
//    }

}

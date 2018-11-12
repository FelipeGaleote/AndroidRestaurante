package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.Mesa;
import com.perrest.restaurante.sincpedidos.presentation.adapter.TablesAdapter;
import com.perrest.restaurante.sincpedidos.repository.TableRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseTableActivity extends AppCompatActivity implements TableRepository.TableListener {

    @BindView(R.id.table_recycler_view)
    RecyclerView recyclerView;

    private TablesAdapter adapter;
    private TableRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_table);

        ButterKnife.bind(this);

        repository = new TableRepository(this, this);

        getSupportActionBar().setTitle(getString(R.string.choose_your_table));

        configRecyclerView();
        loadTables();
    }

    private void configRecyclerView(){
        adapter = new TablesAdapter(getApplicationContext(), id -> repository.takeTable(id));
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void loadTables(){
        repository.loadAllTables();
    }

    @Override
    public void onLoadSuccess(List<Mesa> tables) {
        adapter.setTable(tables);
    }

    @Override
    public void onLoadFailed() {

    }

    @Override
    public void onTableTakeSuccess(long tableId) {
        Toast.makeText(this,"Mesa " + tableId + " selecionada", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ChooseTableActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onTableTakeFailed() {

    }
}

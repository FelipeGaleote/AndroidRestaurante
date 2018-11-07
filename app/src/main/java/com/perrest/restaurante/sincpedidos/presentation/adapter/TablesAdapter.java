package com.perrest.restaurante.sincpedidos.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.Mesa;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.ViewHolder> {

    private List<Mesa> tables;
    private Context context;

    public TablesAdapter(Context context) {
        this.context = context;
        tables = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mesa table = tables.get(position);
        holder.number.setText(String.valueOf(table.getId()));
        if (table.getStatus().equals("Ocupada")) {
            holder.number.setBackgroundColor(context.getResources().getColor(R.color.color_md_red_900));
        } else {
            holder.number.setBackgroundColor(context.getResources().getColor(R.color.color_md_green_900));
        }

    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public void setTable(List<Mesa> tables) {
        this.tables = tables;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.table_number)
        TextView number;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

package com.example.aula_27_09;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<Calculo> historico;

    public HistoryAdapter(List<Calculo> historico) {
        this.historico = historico;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        Calculo calculo = historico.get(position);
        holder.textViewOperacao.setText(calculo.getOperacao());
        holder.textViewResultado.setText(calculo.getResultado());
    }

    @Override
    public int getItemCount() {
        return historico.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewOperacao;
        public TextView textViewResultado;

        public ViewHolder(View v) {
            super(v);
            textViewOperacao = v.findViewById(android.R.id.text1);
            textViewResultado = v.findViewById(android.R.id.text2);
        }
    }
}

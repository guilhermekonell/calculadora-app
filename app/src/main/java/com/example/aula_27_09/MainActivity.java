package com.example.aula_27_09;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber1, editTextNumber2;
    TextView textViewResult;
    Button buttonAdd, buttonSubtract, buttonMultiply, buttonDivide;

    private RecyclerView recyclerViewHistorico;
    private HistoryAdapter adapter;
    private List<Calculo> historico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber1 = findViewById(R.id.first_number);
        editTextNumber2 = findViewById(R.id.second_number);
        textViewResult = findViewById(R.id.result);

        buttonAdd = findViewById(R.id.somar);
        buttonSubtract = findViewById(R.id.subtrair);
        buttonMultiply = findViewById(R.id.multiplicar);
        buttonDivide = findViewById(R.id.dividir);

        recyclerViewHistorico = findViewById(R.id.historico);
        historico = new ArrayList<>();
        adapter = new HistoryAdapter(historico);
        recyclerViewHistorico.setAdapter(adapter);
        recyclerViewHistorico.setLayoutManager(new LinearLayoutManager(this));

        buttonAdd.setOnClickListener(v -> calcular('+'));

        buttonSubtract.setOnClickListener(v -> calcular('-'));

        buttonMultiply.setOnClickListener(v -> calcular('*'));

        buttonDivide.setOnClickListener(v -> calcular('/'));
    }

    private void calcular(char operador) {
        String num1String = editTextNumber1.getText().toString();
        String num2String = editTextNumber2.getText().toString();

        if (!num1String.isEmpty() && !num2String.isEmpty()) {
            double num1 = Double.parseDouble(num1String);
            double num2 = Double.parseDouble(num2String);
            double resultado = 0;

            switch (operador) {
                case '+':
                    resultado = num1 + num2;
                    break;
                case '-':
                    resultado = num1 - num2;
                    break;
                case '*':
                    resultado = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        textViewResult.setText("Erro: Divisão por zero");
                        Toast.makeText(this, "Erro: Divisão por zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
            }

            textViewResult.setText(String.valueOf(resultado));

            String resultadoString = String.valueOf(resultado);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Resultado");
            builder.setMessage("O resultado é: " + resultadoString);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            String operacao = num1 + " " + operador + " " + num2 + " = " + resultado;
            historico.add(new Calculo(operacao, String.valueOf(resultado)));

            adapter.notifyDataSetChanged();
        } else {
            textViewResult.setText("Por favor, insira dois números");
            Toast.makeText(this, "Por favor, insira dois números", Toast.LENGTH_LONG).show();
        }
    }
}
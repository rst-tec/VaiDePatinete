package br.com.raphaelsilva.vaidepatinete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText preco;
    private TextInputEditText quantKM;
    private TextInputEditText litrosKM;
    private ImageView idFoto;
    private TextView textMediaKM;
    private TextView textLitros;
    private TextView textEconomia;
    private Button btCalcular;
    private Button btLimpar;
    private Button btCombustao;
    private Button btEletricos;
    private Button btAmbiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idFoto = findViewById(R.id.idFoto);
        preco = findViewById(R.id.preco);
        quantKM = findViewById(R.id.quantKM);
        litrosKM = findViewById(R.id.litrosKM);
        textMediaKM = findViewById(R.id.textMediaKM);
        textEconomia = findViewById(R.id.textEconomia);
        textLitros = findViewById(R.id.textLitros);
        btCalcular = findViewById(R.id.btCalcular);
        btLimpar = findViewById(R.id.btLimpar);

        btCombustao = findViewById(R.id.btCombustao);
        btEletricos = findViewById(R.id.btEletricos);
        btAmbiente = findViewById(R.id.btAmbiente);

        btCombustao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CombustaoActivity.class);
                startActivity(intent);
            }
        });

        btEletricos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EletricosActivity.class);
                startActivity(intent);
            }
        });

        btAmbiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AmbienteActivity.class);
                startActivity(intent);
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preco.setText("");
                quantKM.setText("");
                litrosKM.setText("");
                textMediaKM.setText(" ");
                textLitros.setText(" ");
                textEconomia.setText(" ");
                idFoto.setBackgroundResource(R.drawable.veiculo);
            }
        });
    }

    public void calcular(View view){
        String precoGasolina    = preco.getText().toString();
        String quantidadeKM     = quantKM.getText().toString();
        String litrosPorKM      = litrosKM.getText().toString();

        Boolean camposValidados = validarCampos(precoGasolina, quantidadeKM, litrosPorKM);
        if(camposValidados){
            Double valorGasolina = Double.parseDouble(precoGasolina);
            Double kmQuantidade = Double.parseDouble(quantidadeKM);
            Double kmLitros = Double.parseDouble(litrosPorKM);
            Double economiaTotal    = (kmQuantidade/kmLitros) * valorGasolina;

            String km = String.format("%.0f", kmLitros);
            String litros = String.format("%.2f", kmQuantidade/kmLitros);
            String economia = String.format("%.2f", economiaTotal);

            textMediaKM.setText("Seu veículo faz em média " + km + " KM por litro");
            textLitros.setText("Você precisara abastecer " + litros + " litros");
            textEconomia.setText("Vá de patinente e economize R$: " + economia);

            idFoto.setBackgroundResource(R.drawable.patinete);

        }else{
            Toast.makeText(getApplicationContext(),"Preencha os campos primeiro",Toast.LENGTH_LONG).show();
        }
    }

    public Boolean validarCampos(String pPreco, String pQuantKM, String pLitrosKM){
        Boolean camposValidados = true;
        if(pPreco == null || pPreco.equals("")){
            camposValidados = false;
        }else if (pQuantKM == null || pQuantKM.equals("")) {
            camposValidados = false;
        }else if (pLitrosKM == null || pLitrosKM.equals("")) {
            camposValidados = false;
        }

        return camposValidados;
    }

}

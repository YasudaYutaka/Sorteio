package com.yutaka.sorteio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
    }

    public void sortearNum(View view) {

        TextView quantidadeNum1 = findViewById(R.id.editTextMenorNum);
        TextView quantidadeNum2 = findViewById(R.id.editTextMaiorNum);
        String verificador1 = quantidadeNum1.getText().toString();
        String verificador2 = quantidadeNum2.getText().toString();
        ImageView imagemCirculo = findViewById(R.id.imageCircle);

        TextView texto1 = findViewById(R.id.txtMsg);
        TextView texto2 = findViewById(R.id.editTextRes);
        imagemCirculo.setVisibility(View.GONE);
        texto2.setVisibility(View.GONE);


        quantidadeNum2.onEditorAction(EditorInfo.IME_ACTION_DONE); // esconde teclado ao clicar no botão do programa

        if (verificador1.equals("") || verificador2.equals("")){
            texto1.setText("Digite um número!");
        } else{
            int numeroUsuario1 = Integer.parseInt(quantidadeNum1.getText().toString());
            int numeroUsuario2 = Integer.parseInt(quantidadeNum2.getText().toString());
            if(numeroUsuario1 > numeroUsuario2){
                texto1.setText("Digite um intervalo válido");
            }else {
                Random random = new Random();
                int numeroSorteado = random.nextInt((numeroUsuario2 - numeroUsuario1)+1)+numeroUsuario1;
                texto1.setText("O número sorteado é:");
                texto2.setText("" + numeroSorteado);
                texto2.setVisibility(View.VISIBLE);
                imagemCirculo.setVisibility(View.VISIBLE);
            }
        }
    }
}

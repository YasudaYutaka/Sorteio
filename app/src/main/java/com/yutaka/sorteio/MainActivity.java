package com.yutaka.sorteio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private Switch repetirSwitch;
    private TextView quantidadeRepet;
    DialogoResultado dialogoResultado = new DialogoResultado(); // Chamando Classe DialogoResultado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        repetirSwitch = findViewById(R.id.switchRep);
        quantidadeRepet = findViewById(R.id.editTextNumRepet);

        adicionarListener(); // Listener do Switch
    }

    public void openDialog(){  // METODO QUE MOSTRA MENSAGEM
        dialogoResultado.show(getSupportFragmentManager(), "teste");
    }



    public void adicionarListener(){  // LISTENER DO SWITCH
        repetirSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    quantidadeRepet.setVisibility(View.VISIBLE);
                } else {
                    quantidadeRepet.setVisibility(View.GONE);
                }
            }
        });
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
       texto1.setVisibility(View.VISIBLE);
       texto2.setVisibility(View.GONE);

       String verificador3 = quantidadeRepet.getText().toString();

       quantidadeNum2.onEditorAction(EditorInfo.IME_ACTION_DONE); // esconde teclado ao clicar no botão do programa
       Boolean switchState = repetirSwitch.isChecked();

        if (verificador1.equals("") || verificador2.equals("")){  // verifica se o usuário digitou número
            texto1.setText("Digite um número!");
        } else{
            int numeroUsuario1 = Integer.parseInt(quantidadeNum1.getText().toString());
            int numeroUsuario2 = Integer.parseInt(quantidadeNum2.getText().toString());
            int numeroUsuarioRep = Integer.parseInt(quantidadeRepet.getText().toString());
            if(numeroUsuario1 > numeroUsuario2){          // verifica se o meanor número é menor do que o maior número
                texto1.setText("Digite um intervalo válido");
            }else {
                if (switchState == true) {
                    if (verificador3.equals("") || numeroUsuarioRep == 0){
                        texto1.setText("Digite a quantidade de repetições");
                    } else {
                        dialogoResultado.resultadoTeste = "";
                        int contador = 0;
                        int numeroUsuario3 = Integer.parseInt(quantidadeRepet.getText().toString());
                        int[] numSorteados = new int[numeroUsuario3];
                        texto1.setVisibility(View.GONE);
                        Random random = new Random();
                        do {
                            int numeroSorteado = random.nextInt((numeroUsuario2 - numeroUsuario1) + 1) + numeroUsuario1;  // gera número aleatório de acordo com o Intervado
                            numSorteados[contador] = numeroSorteado;
                            contador++;
                            dialogoResultado.resultadoTeste += contador +"º resultado é: "+numeroSorteado+"\n"; // DEFINE ESCRITA MENSAGEM
                            // FALTA GUARDAR
                        } while (contador < numeroUsuario3);
                        openDialog(); // ABRE ABA MENSAGEM
                    }

                } else {
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
}

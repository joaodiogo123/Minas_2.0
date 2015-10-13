package com.joaopires.minas20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    protected int[] id_botoes;
    protected Button[] bArray;
    protected int[] minasPosicoes;
    protected int[] aux;
    protected TextView t;
    protected EditText w;

    public void carregarBotao(View view){

        Button b = (Button)view;
        int i;
        int a = b.getId();
        //Procurar o id do botão que foi carregado no vetor de id dos botoes de forma a decobrir em que posição do array de botões o botão está para conpara-la com o array de posições de minas
        for(i = 0; i < id_botoes.length; i++){

            if(id_botoes[i] == a){
                break; //Quando for encontrado saimos do ciclo
            }

        }

        if(minasPosicoes[i] == 1){ //Se a posição do vetor de minas conter um 1 significa que existe uma mina nesta posição, ou seja, o botão correspondente tem uma mina
            b.setText("M"); //Neste caso é posto a letra M no botão
        }else
            b.setText(Integer.toString(0)); //Se não, é um 0


        t.setText(Integer.toString(a));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = (TextView)findViewById(R.id.t);
        w = (EditText)findViewById(R.id.w);

        id_botoes = new int[] {R.id.b01, R.id.b02, R.id.b03, R.id.b04, R.id.b05, R.id.b06, R.id.b07,
                R.id.b11, R.id.b12, R.id.b13, R.id.b14, R.id.b15, R.id.b16, R.id.b17,
                R.id.b21, R.id.b22, R.id.b23, R.id.b24, R.id.b25, R.id.b26, R.id.b27,
                R.id.b31, R.id.b32, R.id.b33, R.id.b34, R.id.b35, R.id.b36, R.id.b37,
                R.id.b41, R.id.b42, R.id.b43, R.id.b44, R.id.b45, R.id.b46, R.id.b47,
                R.id.b51, R.id.b52, R.id.b53, R.id.b54, R.id.b55, R.id.b56, R.id.b57,
                R.id.b61, R.id.b62, R.id.b63, R.id.b64, R.id.b65, R.id.b66, R.id.b67,
                R.id.b71, R.id.b72, R.id.b73, R.id.b74, R.id.b75, R.id.b76, R.id.b77};


        bArray = new Button[56];

        /// /Criar botões no vetor e criar o listeners
        for(int i=0; i < id_botoes.length; i++) {

            bArray[i] = (Button) findViewById(id_botoes[i]);

            bArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    carregarBotao(v);
                }
            });
        }

        minasPosicoes = new int[56]; //Array que irá servir para verificar quais botões têm minas
        int numDeMinas = 10; //Número de minas que queremos que exista no tabuleiro
        aux = new int[56]; //Array auxiliar para a colocação aleatória das minas
        int auxTam = aux.length; //Tamanho inicial do array auxiliar
        Random rand = new Random();
        //Preencher o vetor auxiliar com os números de 0 até ao número de botões
        for(int i = 0; i < aux.length; i++){
            aux[i] = i;
            minasPosicoes[i] = 0;
        }
        //Colocar as minas
        for (int i = 0; i <= numDeMinas; i++){

            //int aleatorio = (int)((56 - 0 + 1) * rand + 0);
            int aleatorio = rand.nextInt(auxTam-1); //Número aleatório entre 0 e o índice máximo do vetro auxiliar

            minasPosicoes[aux[aleatorio]] = 1; //Inserir a mina na posição
            aux[aleatorio] = aux[auxTam - 1]; //Passa o último número do array auxiliar para a posição que saiu
            auxTam--; //Diminui o tamanho da variável máxima de índice do vetor auxiliar para que não calhe um número que esteja fora do array
        }

    }
}
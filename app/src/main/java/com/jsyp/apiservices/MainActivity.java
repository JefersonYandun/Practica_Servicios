package com.jsyp.apiservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText edNum1, edNum2;
    Button btProcesar;
    TextView tvResultado;



    String respuesta="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNum1 = findViewById(R.id.txtNum1);
        edNum2 = findViewById(R.id.txtNum2);
        btProcesar = findViewById(R.id.btnGuardar);
        tvResultado = findViewById(R.id.lblResultado);


        // Agrega el listener al botón
        btProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsumirAPI();
            }
        });
    }


    public void ConsumirAPI() {
        String promtuario = "https://ejemplo2apimovil20240128220859.azurewebsites.net/api/Operaciones?a=" + edNum1.getText() + "&b=" + edNum2.getText();

        OkHttpClient cliente = new OkHttpClient();

        Request get = new Request.Builder().url(promtuario).build();

        cliente.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Manejar la falla aquí
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try{
                    ResponseBody responseBody=response.body();
                    if(!response.isSuccessful()){
                        throw new IOException("Respuesta inseperada"+response);
                    }
                    respuesta=responseBody.string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResultado.setText(respuesta);
                        }
                    });
                }catch(Exception e){

                    e.printStackTrace();

                }


            }
        });
    }
}


 /*
 if (response.isSuccessful()) {
final String resultado = response.body().string();

        // Actualizar la interfaz de usuario desde el hilo principal
        runOnUiThread(new Runnable() {
@Override
public void run() {
        tvResultado.setText(resultado);
        }
        });
        }


  */
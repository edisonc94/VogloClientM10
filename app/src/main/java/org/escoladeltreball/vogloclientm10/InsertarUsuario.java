package org.escoladeltreball.vogloclientm10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.User;

public class InsertarUsuario extends AppCompatActivity {
    EditText id, nombre, type,money;
    Button buscar;
    TextView resultado;
    User usu = new User();
    String resultadoStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_usuario);
        id = (EditText) findViewById(R.id.idUser);
        nombre = (EditText) findViewById(R.id.nombre);
        type = (EditText) findViewById(R.id.type);
        money = (EditText) findViewById(R.id.money);
        buscar = (Button) findViewById(R.id.busca);
        resultado = (TextView) findViewById(R.id.resultado);

        buscar.setOnClickListener(view -> {
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    DefaultApi apiInstance = new DefaultApi();
                    apiInstance.setApiClient(new ApiClient().setBasePath("http://10.0.2.2:8084/v1"));
                    try {
                        usu.setId(Integer.valueOf(id.getText().toString()));
                        usu.setName(nombre.getText().toString());
                        usu.setType(type.getText().toString());
                        usu.setMoney(BigDecimal.valueOf(Double.valueOf(money.getText().toString())));

                        apiInstance.afegirUsuari(usu);
                    } catch (ApiException e) {
                        resultadoStr = e.getMessage().toString();
                    }
                }
            };

            t1.start();
            try {
                t1.join();
                if (!"".equals(id.getText().toString())){
                    resultadoStr = "";
                    resultado.setText("Usuario añadido: \n"+usu.toString());
                } else {
                    resultado.setText(resultadoStr);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });

    }
}

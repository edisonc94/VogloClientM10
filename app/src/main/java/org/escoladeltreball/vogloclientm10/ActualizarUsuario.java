package org.escoladeltreball.vogloclientm10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.User;

public class ActualizarUsuario extends AppCompatActivity {
    EditText oldId, id, nombre, type,money;
    Button buscar, buscar2;
    TextView resultado;
    Integer idInt;
    User usu = new User();
    String resultadoStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_usuario);

        oldId = (EditText) findViewById(R.id.idUserOld);
        id = (EditText) findViewById(R.id.idUser);
        nombre = (EditText) findViewById(R.id.nombre);
        type = (EditText) findViewById(R.id.type);
        money = (EditText) findViewById(R.id.money);
        buscar = (Button) findViewById(R.id.busca);
        buscar2 = (Button) findViewById(R.id.busca2);
        resultado = (TextView) findViewById(R.id.resultado);

        buscar.setOnClickListener(view -> {
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    DefaultApi apiInstance = new DefaultApi();
                    apiInstance.setApiClient(new ApiClient().setBasePath("http://10.0.2.2:8084/v1"));
                    try {
                        if (!"".equals(oldId.getText().toString())) {
                            idInt = Integer.valueOf(oldId.getText().toString());
                        }
                        usu = apiInstance.obtenirUsuariPerId(idInt);

                    } catch (ApiException e) {
                        resultadoStr = e.getMessage().toString();
                    }
                }
            };

            t1.start();
            try {
                t1.join();

                id.setVisibility(View.VISIBLE);
                id.setText(usu.getId().toString());
                nombre.setText(usu.getName().toString());
                nombre.setVisibility(View.VISIBLE);
                type.setText(usu.getType().toString());
                type.setVisibility(View.VISIBLE);
                money.setText(usu.getMoney().toString());
                money.setVisibility(View.VISIBLE);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buscar2.setVisibility(View.VISIBLE);
        });

            buscar2.setOnClickListener(view1 -> {
                Thread t2 = new Thread() {
                    @Override
                    public void run() {
                        DefaultApi apiInstance = new DefaultApi();
                        apiInstance.setApiClient(new ApiClient().setBasePath("http://10.0.2.2:8084/v1"));
                        try {
                            usu.setId(Integer.valueOf(id.getText().toString()));
                            usu.setName(nombre.getText().toString());
                            usu.setType(type.getText().toString());
                            usu.setMoney(BigDecimal.valueOf(Long.valueOf(money.getText().toString())));

                            apiInstance.actualitzarUsuari(idInt,usu);
                        } catch (ApiException e) {
                            resultadoStr = e.getMessage().toString();
                        }
                    }
                };

                t2.start();
                try {
                    t2.join();
                    if (!"".equals(id.getText().toString())){
                        resultadoStr = "";
                        resultado.setText("Usuario actualizado: \n"+usu.toString());
                    } else {
                        resultado.setText(resultadoStr);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


    }
}

package org.escoladeltreball.vogloclientm10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.User;

public class EliminarUsuario extends AppCompatActivity {
    EditText id;
    Button buscar;
    TextView resultado;
    Integer idInt;
    User usu = new User();
    String resultadoStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario);

        id = (EditText) findViewById(R.id.idUser);
        buscar = (Button) findViewById(R.id.busca);
        resultado = (TextView) findViewById(R.id.resultado);

        buscar.setOnClickListener(view -> {
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    DefaultApi apiInstance = new DefaultApi();
                    apiInstance.setApiClient(new ApiClient().setBasePath("http://10.0.2.2:8084/v1"));
                    try {
                        if (!"".equals(id.getText().toString())) {
                            idInt = Integer.valueOf(id.getText().toString());
                        }
                        usu = apiInstance.eliminarUsuari(idInt);
                    } catch (ApiException e) {
                        resultadoStr = e.getMessage().toString();
                    }
                }
            };

            t1.start();
            try {
                t1.join();
                if (!"".equals(id.getText().toString())){
                    resultado.setText("Usuario eliminado: "+usu);
                } else {
                    resultado.setText(resultadoStr);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}

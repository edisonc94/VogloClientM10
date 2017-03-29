package org.escoladeltreball.vogloclientm10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.User;

public class BuscarUsuarios extends AppCompatActivity {
    EditText limit;
    Button buscar;
    TextView resultado;
    List<User> list = new ArrayList<User>();
    int limitInt = Integer.MAX_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuarios);

        limit = (EditText) findViewById(R.id.limit);
        buscar = (Button) findViewById(R.id.busca);
        resultado = (TextView) findViewById(R.id.resultado);

        buscar.setOnClickListener(view -> {
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    DefaultApi apiInstance = new DefaultApi();
                    apiInstance.setApiClient(new ApiClient().setBasePath("http://10.0.2.2:8084/v1"));
                    try {
                        if (!"".equals(limit.getText().toString())){
                            limitInt = Integer.valueOf(limit.getText().toString());
                        }
                        list = apiInstance.obtenirUsuaris(limitInt);
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
                }
            };

            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String resultadoStr = "";
            for (int i = 0; i < list.size(); i++) {
                resultadoStr += list.get(i).getId()+" ";
                resultadoStr += list.get(i).getName()+" ";
                resultadoStr += list.get(i).getType()+" ";
                resultadoStr += new Double(list.get(i).getMoney().toString());
                resultadoStr += "\n";
            }

            resultado.setText("ID NAME TYPE MONEY\n"+resultadoStr);
            if ("".equals(resultado.getText().toString())){
                resultado.setText("No hay resultados");
            }
        });
    }
}

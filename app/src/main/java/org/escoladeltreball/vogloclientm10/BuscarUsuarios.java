package org.escoladeltreball.vogloclientm10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
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
    List<User> list = new ArrayList<User>();
    int limitInt = Integer.MAX_VALUE;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuarios);

        limit = (EditText) findViewById(R.id.limit);
        buscar = (Button) findViewById(R.id.busca);
        table = (TableLayout) findViewById(R.id.table);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        DefaultApi apiInstance = new DefaultApi();
                        apiInstance.setApiClient(new ApiClient().setBasePath("http://10.0.2.2:8084/v1"));
                        try {
                            if (!"".equals(limit.getText().toString())) {
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
                if (!list.isEmpty()) {
                    table.setVisibility(View.VISIBLE);
                }
                for (int i = 0; i < list.size(); i++) {

                    TableRow tableRow = new TableRow(BuscarUsuarios.this);

                    TextView textView = new TextView(BuscarUsuarios.this);
                    textView.setText(list.get(i).getId().toString());
                    textView.setTextSize(30f);

                    TextView textView2 = new TextView(BuscarUsuarios.this);
                    textView2.setText(list.get(i).getName());
                    textView2.setTextSize(30f);

                    TextView textView3 = new TextView(BuscarUsuarios.this);
                    textView3.setText(list.get(i).getType());
                    textView3.setTextSize(30f);

                    TextView textView4 = new TextView(BuscarUsuarios.this);
                    textView4.setText(list.get(i).getMoney().toString());
                    textView4.setTextSize(30f);

                    tableRow.addView(textView);
                    tableRow.addView(textView2);
                    tableRow.addView(textView3);
                    tableRow.addView(textView4);
                    table.addView(tableRow);

//                resultadoStr += list.get(i).getId()+" ";
//                resultadoStr += list.get(i).getName()+" ";
//                resultadoStr += list.get(i).getType()+" ";
//                resultadoStr += new Double(list.get(i).getMoney().toString());
//                resultadoStr += "\n";
                }

            }
        });
    }
}

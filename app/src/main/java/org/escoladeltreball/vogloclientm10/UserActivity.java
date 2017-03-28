package org.escoladeltreball.vogloclientm10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class UserActivity extends AppCompatActivity {
    Button buscarUsuarios ;
    Button buscarUsuarioPorId ;
    Button insertarUsuario ;
    Button actualizarUsuario;
    Button eliminarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        buscarUsuarios = (Button) findViewById(R.id.buscaUsuarios);
//        buscarUsuarioPorId = (Button) findViewById(R.id.buscaUsuarioPorId);
//        insertarUsuario = (Button) findViewById(R.id.insertaUsuario);
//        actualizarUsuario = (Button) findViewById(R.id.actualizaUsuario);
//        eliminarUsuario = (Button) findViewById(R.id.eliminaUsuario);

        buscarUsuarios.setOnClickListener(v -> {
            Intent intent = new Intent(this, BuscarUsuarios.class);
            startActivity(intent);
        });
//        buscarUsuarioPorId.setOnClickListener(v -> {
//            lanzaActivity(BuscarUsuarioPorId.class);
//        });
//        insertarUsuario.setOnClickListener(v -> {
//            lanzaActivity(InsertarUsuario.class);
//        });
//        actualizarUsuario.setOnClickListener(v -> {
//            lanzaActivity(ActualizarUsuario.class);
//        });
//        eliminarUsuario.setOnClickListener(v -> {
//            lanzaActivity(EliminarUsuario.class);
//        });

    }


}

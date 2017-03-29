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
        buscarUsuarioPorId = (Button) findViewById(R.id.buscaUsuarioPorId);
        insertarUsuario = (Button) findViewById(R.id.insertaUsuario);
        actualizarUsuario = (Button) findViewById(R.id.actualizaUsuario);
        eliminarUsuario = (Button) findViewById(R.id.eliminaUsuario);

        buscarUsuarios.setOnClickListener(v -> {
            Intent intent = new Intent(this, BuscarUsuarios.class);
            startActivity(intent);
        });
        buscarUsuarioPorId.setOnClickListener(v -> {
            Intent intent = new Intent(this, BuscarUsuarioPorId.class);
            startActivity(intent);
        });
        insertarUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(this, InsertarUsuario.class);
            startActivity(intent);
        });
        actualizarUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActualizarUsuario.class);
            startActivity(intent);
        });
        eliminarUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(this, EliminarUsuario.class);
            startActivity(intent);
        });

    }


}

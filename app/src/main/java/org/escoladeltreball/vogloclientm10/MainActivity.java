package org.escoladeltreball.vogloclientm10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import io.swagger.client.model.Service;

public class MainActivity extends AppCompatActivity {
    Service service = new Service();

    Button buttonUsers;
    Button buttonServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonUsers = (Button) findViewById(R.id.buttonUsers);
        buttonServices = (Button) findViewById(R.id.buttonServices);

        buttonUsers.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        });

        buttonServices.setOnClickListener(v -> {
            Intent intent = new Intent(this, ServiceActivity.class);
            startActivity(intent);
        });

    }
}

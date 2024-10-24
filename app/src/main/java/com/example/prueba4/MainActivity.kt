/*TODO:*/

package com.example.prueba4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button butlim, butder, butauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butlim = findViewById(R.id.buttonlim);
        butder = findViewById(R.id.buttonder);
        butauth = findViewById(R.id.buttonauth);

        butlim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TemasActivity.class);
                intent.putExtra("categoria", 1);
                startActivity(intent);
                finish();
            }
        });

        butder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TemasActivity.class);
                intent.putExtra("categoria", 2);
                startActivity(intent);
                finish();
            }
        });

        butauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
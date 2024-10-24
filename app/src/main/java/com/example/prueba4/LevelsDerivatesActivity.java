package com.example.prueba4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.prueba4.tutoriales_derivadas.TutDer1Activity;
import com.example.prueba4.tutoriales_derivadas.TutDer2Activity;
import com.example.prueba4.tutoriales_derivadas.TutDer3Activity;
import com.example.prueba4.tutoriales_derivadas.TutDer4Activity;
import com.example.prueba4.tutoriales_derivadas.TutDer5Activity;
import com.example.prueba4.tutoriales_derivadas.TutDer6Activity;
import com.example.prueba4.tutoriales_derivadas.TutDer7Activity;

import java.util.ArrayList;
import java.util.List;

public class LevelsDerivatesActivity extends AppCompatActivity {

    ConstraintLayout oContainer;
    int level = 1;
    int porciento = 0;
    TextView titulo, pc1, pc2, pc3, pc4, pc5, pc6, pc7, punt;
    List<String> porcentajes = null;
    String namefile = "";
    String puntuacion = "";
    int globalI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_derivates);

        titulo = findViewById(R.id.tvTitulo);

        porcentajes = new ArrayList<>();

        oContainer = findViewById(R.id.optionsContainer);

        titulo.setText("DERIVADAS");

        for (int i = 1; i < 8; i++) {
            int finalI = i;
            oContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(view, finalI);
                }
            });
        }

    }

    private void showPopupMenu(View view, int index) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            level = index;
            int id = item.getItemId();
            if (id == R.id.item1) {
                siguienteJuego();
                return true;
            }
            if (id == R.id.item2) {
                siguienteTutorial();
                return true;
            }
            return true;
        });

        popupMenu.show();
    }

    private void siguienteJuego() {
        Intent intent = new Intent(LevelsDerivatesActivity.this, QuestionActivity.class);
        intent.putExtra("level", level);
        intent.putExtra("derMode", true);
        startActivity(intent);
        finish();
    }

    private void siguienteTutorial() {
        Intent intent = null;

        switch (level) {
            case 1: intent = new Intent(LevelsDerivatesActivity.this, TutDer1Activity.class); break;
            case 2: intent = new Intent(LevelsDerivatesActivity.this, TutDer2Activity.class); break;
            case 3: intent = new Intent(LevelsDerivatesActivity.this, TutDer3Activity.class); break;
            case 4: intent = new Intent(LevelsDerivatesActivity.this, TutDer4Activity.class); break;
            case 5: intent = new Intent(LevelsDerivatesActivity.this, TutDer5Activity.class); break;
            case 6: intent = new Intent(LevelsDerivatesActivity.this, TutDer6Activity.class); break;
            case 7: intent = new Intent(LevelsDerivatesActivity.this, TutDer7Activity.class); break;
        }

//        intent.putExtra("level", level);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LevelsDerivatesActivity.this, MainActivity.class));
        finish();
    }

}
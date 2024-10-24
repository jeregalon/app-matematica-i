package com.example.prueba4;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.prueba4.tutoriales_limites.TutLim1Activity;
import com.example.prueba4.tutoriales_limites.TutLim2Activity;
import com.example.prueba4.tutoriales_limites.TutLim3Activity;
import com.example.prueba4.tutoriales_limites.TutLim4Activity;
import com.example.prueba4.tutoriales_limites.TutLim5Activity;

import java.util.ArrayList;
import java.util.List;

public class LevelsLimitsActivity extends AppCompatActivity {

    ConstraintLayout oContainer;
    int level = 1;
    int porciento = 0;
    TextView titulo;
    List<String> porcentajes = null;
    String namefile = "";
    String puntuacion = "";
    int globalI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_limits);

        titulo = findViewById(R.id.tvTitulo);

        porcentajes = new ArrayList<>();

        oContainer = findViewById(R.id.optionsContainer);

        titulo.setText("LIMITES");

        for (int i = 1; i < 5; i++) {
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
        Intent intent = new Intent(LevelsLimitsActivity.this, QuestionActivity.class);
        intent.putExtra("level", level);
        intent.putExtra("derMode", false);
        startActivity(intent);
        finish();
    }

    private void siguienteTutorial() {
        Intent intent = null;

        switch (level) {
            case 1: intent = new Intent(LevelsLimitsActivity.this, TutLim1Activity.class); break;
            case 2: intent = new Intent(LevelsLimitsActivity.this, TutLim2Activity.class); break;
            case 3: intent = new Intent(LevelsLimitsActivity.this, TutLim4Activity.class); break;
            case 4: intent = new Intent(LevelsLimitsActivity.this, TutLim5Activity.class); break;
        }

//        intent.putExtra("level", level);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LevelsLimitsActivity.this, MainActivity.class));
        finish();
    }

}
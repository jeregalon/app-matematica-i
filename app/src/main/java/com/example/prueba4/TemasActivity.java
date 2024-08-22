package com.example.prueba4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.prueba4.Clases.QuestionsItem;
import com.example.prueba4.Tutoriales_Derivadas.TutDer1Activity;
import com.example.prueba4.Tutoriales_Derivadas.TutDer2Activity;
import com.example.prueba4.Tutoriales_Derivadas.TutDer3Activity;
import com.example.prueba4.Tutoriales_Derivadas.TutDer4Activity;
import com.example.prueba4.Tutoriales_Derivadas.TutDer5Activity;
import com.example.prueba4.Tutoriales_Derivadas.TutDer7Activity;
import com.example.prueba4.Tutoriales_Derivadas.TutDer6Activity;
import com.example.prueba4.Tutoriales_Derivadas.TutDer8Activity;
import com.example.prueba4.Tutoriales_Limites.TutLim1Activity;
import com.example.prueba4.Tutoriales_Limites.TutLim2Activity;
import com.example.prueba4.Tutoriales_Limites.TutLim3Activity;
import com.example.prueba4.Tutoriales_Limites.TutLim4Activity;
import com.example.prueba4.Tutoriales_Limites.TutLim5Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TemasActivity extends AppCompatActivity {

    ConstraintLayout oContainer;
    int categoria = 1;
    int tema = 1;
    int porciento = 0;
    TextView titulo, pc1, pc2, pc3, pc4, pc5, pc6, pc7, punt;
    List<String> porcentajes = null;
    String namefile = "";
    String puntuacion = "";
    int globalI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas);

        titulo = findViewById(R.id.tvTitulo);
        pc1 = findViewById(R.id.textView);
        pc2 = findViewById(R.id.textView2);
        pc3 = findViewById(R.id.textView3);
        pc4 = findViewById(R.id.textView4);
        pc5 = findViewById(R.id.textView5);
        pc6 = findViewById(R.id.textView6);
        pc7 = findViewById(R.id.textView7);
        punt = findViewById(R.id.puntuation);

        porcentajes = new ArrayList<>();

        oContainer = findViewById(R.id.optionsContainer);

        categoria = getIntent().getIntExtra("categoria", 1);

        String title = "";

        switch (categoria) {
            case 1:
                title = "LÍMITES";
                oContainer.getChildAt(6).setVisibility(View.GONE);
                oContainer.getChildAt(7).setVisibility(View.GONE);
                pc6.setVisibility(View.GONE);
                pc7.setVisibility(View.GONE);
                porcentajes.add(pc1.getText().toString());
                porcentajes.add(pc2.getText().toString());
                porcentajes.add(pc3.getText().toString());
                porcentajes.add(pc4.getText().toString());
                porcentajes.add(pc5.getText().toString());
                globalI = 6;
                break;
            case 2:
                title = "DERIVADAS";
                oContainer.getChildAt(6).setVisibility(View.VISIBLE);
                oContainer.getChildAt(7).setVisibility(View.VISIBLE);
                pc6.setVisibility(View.VISIBLE);
                pc7.setVisibility(View.VISIBLE);
                porcentajes.add(pc1.getText().toString());
                porcentajes.add(pc2.getText().toString());
                porcentajes.add(pc3.getText().toString());
                porcentajes.add(pc4.getText().toString());
                porcentajes.add(pc5.getText().toString());
                porcentajes.add(pc6.getText().toString());
                porcentajes.add(pc7.getText().toString());
                globalI = 8;
                break;
            default: break;
        }

        leerDatos();
        leerPuntuacion();

        titulo.setText(title);

        for (int i = 1; i < globalI; i++) {
            int finalI = i;
            oContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(view, finalI);
                }
            });
        }

    }

    private void guardarDatos() {
        File ruta = getApplicationContext().getFilesDir();
        if (categoria == 1) namefile = "porc_lim.tpo";
        else if (categoria == 2) namefile = "porc_der.tpo";
        try {
            FileOutputStream writefile = new FileOutputStream(new File(ruta, namefile));
            ObjectOutputStream streamfile = new ObjectOutputStream(writefile);
            streamfile.writeObject(porcentajes);
            streamfile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void leerDatos() {
        File ruta = getApplicationContext().getFilesDir();
        if (categoria == 1) namefile = "porc_lim.tpo";
        else if (categoria == 2) namefile = "porc_der.tpo";
        File file = new File(ruta, namefile);
        if (!file.exists()) {
            guardarDatos();
        } else {
            try {
                FileInputStream readfile = new FileInputStream(file);
                ObjectInputStream streamfile = new ObjectInputStream(readfile);
                porcentajes = (ArrayList<String>) streamfile.readObject();
                streamfile.close();
                pc1.setText(porcentajes.get(0));
                pc2.setText(porcentajes.get(1));
                pc3.setText(porcentajes.get(2));
                pc4.setText(porcentajes.get(3));
                pc5.setText(porcentajes.get(4));
                if (categoria == 2) {
                    pc6.setText(porcentajes.get(5));
                    pc7.setText(porcentajes.get(6));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void guardarPuntuacion() {
        File ruta = getApplicationContext().getFilesDir();
        namefile = "puntuacion.tpo";
        try {
            FileOutputStream writefile = new FileOutputStream(new File(ruta, namefile));
            ObjectOutputStream streamfile = new ObjectOutputStream(writefile);
            puntuacion = punt.getText().toString();
            streamfile.writeObject(puntuacion);
            streamfile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void leerPuntuacion() {
        File ruta = getApplicationContext().getFilesDir();
        namefile = "puntuacion.tpo";
        File file = new File(ruta, namefile);
        if (!file.exists()) {
            guardarPuntuacion();
        } else {
            try {
                FileInputStream readfile = new FileInputStream(file);
                ObjectInputStream streamfile = new ObjectInputStream(readfile);
                puntuacion = streamfile.readObject().toString();
                streamfile.close();
                punt.setText(puntuacion);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showPopupMenu(View view, int index) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            tema = index;
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
        Intent intent = new Intent(TemasActivity.this, QuestionActivity.class);
        intent.putExtra("tema", tema);
        intent.putExtra("categoria", categoria);
        startActivity(intent);
        finish();
    }

    private void siguienteTutorial() {
        Intent intent = null;

        if (categoria == 1) {
            switch (tema) {
                case 1: intent = new Intent(TemasActivity.this, TutLim1Activity.class); break;
                case 2: intent = new Intent(TemasActivity.this, TutLim2Activity.class); break;
                case 3: intent = new Intent(TemasActivity.this, TutLim3Activity.class); break;
                case 4: intent = new Intent(TemasActivity.this, TutLim4Activity.class); break;
                case 5: intent = new Intent(TemasActivity.this, TutLim5Activity.class); break;
            }
        } else if (categoria == 2) {
            switch (tema) {
                case 1: intent = new Intent(TemasActivity.this, TutDer1Activity.class); break;
                case 2: intent = new Intent(TemasActivity.this, TutDer2Activity.class); break;
                case 3: intent = new Intent(TemasActivity.this, TutDer3Activity.class); break;
                case 4: intent = new Intent(TemasActivity.this, TutDer4Activity.class); break;
                case 5: intent = new Intent(TemasActivity.this, TutDer5Activity.class); break;
                case 6: intent = new Intent(TemasActivity.this, TutDer6Activity.class); break;
                case 7: intent = new Intent(TemasActivity.this, TutDer7Activity.class); break;
            }
        }


        intent.putExtra("tema", tema);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TemasActivity.this, MainActivity.class));
        finish();
    }

}
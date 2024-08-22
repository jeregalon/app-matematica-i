/*TODO
*  Poner un log in*/

package com.example.prueba4;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.prueba4.Clases.Drawable;
import com.example.prueba4.Clases.QuestionsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.noties.jlatexmath.JLatexMathView;

// Actividad del quiz de preguntas y respuestas
public class QuestionActivity extends AppCompatActivity {
    private JLatexMathView latex1, latex2, latex3, latex4, latex5;
    // Textos, en LaTex, de la pregunta y las opciones de respuesta
    List<QuestionsItem> listaDePreguntas = null;
    int packDePreguntas = 1;
    int currentQuestion = 0;
    int currentPackQuestion = 0;
    int currentPack = 0;
    int totalPreguntas = 9;
    int preguntasxPack = 3;
    int correct = 0, wrong = 0;
    // Conteo de preguntas correctas e incorrectas
    int numVidas = 3;
    int categoria = 1;
    // Contenido que se va a ejercitar (límites '0', derivadas '1' o integrales '2')
    int tema = 1;
    int countAnimation = 0;
    boolean loadingQuestions = false;
    Button nextqu;
    // Botón de Siguiente Pregunta (Next Question)
    ProgressBar pb;
    // Barra de progreso del juego
    TextView _timer, _puntuation;
    int puntuacion = 0;
    ConstraintLayout oContainer;
    LinearLayout vContainer;
    ImageView vidaextra;
    boolean hayvidaextra = false;
    String namefile = "";
    String namefile_punt = "puntuacion.tpo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        latex1 = findViewById(R.id.obj_latex);
        latex2 = findViewById(R.id.obj_latex_a);
        latex3 = findViewById(R.id.obj_latex_b);
        latex4 = findViewById(R.id.obj_latex_c);
        latex5 = findViewById(R.id.obj_latex_d);

        oContainer = findViewById(R.id.optionsContainer);

        vContainer = findViewById(R.id.vidasContainer);

        nextqu = findViewById(R.id.btnNext);

        nextqu.setEnabled(false);

        pb = findViewById(R.id.progressBar);

        categoria = getIntent().getIntExtra("categoria", 1);

        tema = getIntent().getIntExtra("tema", 1);

        _timer = findViewById(R.id.timerz);

        _puntuation = findViewById(R.id.puntuation);

        vidaextra = findViewById(R.id.vida6);
        vidaextra.setVisibility(View.INVISIBLE);

        _puntuation.setText(puntuacion + "");

        actualizarPB();

        mostrarMensajeTransparente();

        for (int i=0; i<4; i++){
            oContainer.getChildAt(i).setBackgroundResource(R.drawable.question_enable);
        }

        for (int i=0; i<4; i++){
            int finalI = i;

            // Cuando se oprime una de las opciones:
            oContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    nextqu.setEnabled(true); // Habilita el botón de siguiente pregunta
                    nextqu.setBackgroundResource(R.drawable.btn_next);

                    for (int j = 0; j < 4; j++) {
                        oContainer.getChildAt(j).setClickable(false);
                    }

                    // Si la opción es la correcta
                    int el_correcto = listaDePreguntas.get(currentQuestion).getCorrect();
                    if (el_correcto == finalI) {
                        correct++; // Se incrementa el contador de preguntas correctas
                        if (currentPack == 0) puntuacion += 5;
                        else if (currentPack == 1) puntuacion += 10;
                        else puntuacion += 20;
                        _puntuation.setText(puntuacion + "");
                        if (hayvidaextra) {
                            playHeartAnimation();
                        }
                        oContainer.getChildAt(finalI).setBackgroundResource(R.drawable.right_answr); // La opción se pone en verde

                        // Si no es la correcta
                    } else {
                        wrong++; // Se incrementa el contador de preguntas incorrectas
                        if (numVidas > 0) numVidas-- ;
                        actualizarVidas();
                        oContainer.getChildAt(finalI).setBackgroundResource(R.drawable.wrong_answr);
                        oContainer.getChildAt(el_correcto).setBackgroundResource(R.drawable.right_answr);
                    }
                }
            });
        }

        // Cuando se oprime el botón de siguiente pregunta
        nextqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingQuestions = true;

                // Si no se ha llegado al final de las preguntas
                if (currentQuestion < totalPreguntas - 1) {

                    currentQuestion++;
                    actualizarPB();
                    countAnimation = 0;
                    playAnimation(oContainer.getChildAt(0), 0);

                    currentPackQuestion++;

                    if (currentPackQuestion >= preguntasxPack) {
                        currentPackQuestion = 0;
                        currentPack++;
                        mostrarMensajeTransparente();
                    }
                } else {
                    // llegó al final
                    currentPack++;
                    currentQuestion++;
                    pb.setProgress(100);
                    guardarDatos();
                    mostrarMensajeTransparente();
                }

            }
        });

    }

    private void playAnimation(View view, int value) {
        view.animate()
                .alpha(value)
                .scaleX(value)
                .scaleY(value)
                .setDuration(500)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {
                        if (value == 0) {

                            nextqu.setEnabled(false);
                            nextqu.setBackgroundResource(R.drawable.btn_disable);

                            switch (countAnimation) {
                                case 0: playAnimation(latex1, 0); break;
                                case 1: playAnimation(oContainer.getChildAt(1), 0); break;
                                case 2: playAnimation(oContainer.getChildAt(2), 0); break;
                                case 3: playAnimation(oContainer.getChildAt(3), 0); break;
                                default: break;
                            }
                            countAnimation++;
                        }
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {

                        if (value == 0) {

                            playAnimation(view, 1);

                            if (loadingQuestions == true) {

                                mostrarPreguntaEnPantalla(); // Se muestra la siguiente pregunta en pantalla

                                // Se habilitan las cuatro opciones
                                for (int i=0; i<4; i++){
                                    oContainer.getChildAt(i).setClickable(true);
                                    oContainer.getChildAt(i).setBackgroundResource(R.drawable.question_enable);
                                }

                                loadingQuestions = false;

                            }
                        }

                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                });
    }

    private void mostrarMensaje(int titulo, int mensaje, boolean negButton, boolean backPressed, boolean cancelable) {

        AlertDialog.Builder AlertDialogBuilder = new AlertDialog.Builder(QuestionActivity.this);
        AlertDialogBuilder.setTitle(titulo);
        AlertDialogBuilder.setMessage(mensaje);
        if (!cancelable) AlertDialogBuilder.setCancelable(false);
        if (negButton) {
            AlertDialogBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }
        AlertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (backPressed) {
                    Intent intent = new Intent(QuestionActivity.this, TemasActivity.class);
                    intent.putExtra("categoria", categoria);
                    startActivity(intent);
                    finish();
                } else {


                }
            }
        });
        AlertDialogBuilder.show();

    }

    private void mostrarMensajeTransparente() {
        AlertDialog.Builder abuilder = new AlertDialog.Builder(QuestionActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        int layout = 0;
        switch (currentPack) {
            case 0: layout = R.layout.custom_dialog_pack1; break;
            case 1: layout = R.layout.custom_dialog_pack2; break;
            case 2: layout = R.layout.custom_dialog_pack3; break;
            default: layout = R.layout.custom_dialog_pack4; break;
        }
        View adview = inflater.inflate(layout, null);
        abuilder.setView(adview);

        AlertDialog dialog = abuilder.create();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        try {
            dialog.show();

            Handler handler = new Handler();
            Runnable runnable =()-> {
                if(dialog.isShowing()) {
                    dialog.dismiss();

                    if (currentPack == 0) cargarPreguntas();
                    if (currentPack == 3) {
                        Intent intent = new Intent(QuestionActivity.this, TemasActivity.class);
                        intent.putExtra("categoria", categoria);
                        startActivity(intent);
                        finish();
                    }
                }
            };

            handler.postDelayed(runnable, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarPreguntas() {

        listaDePreguntas = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String archivojson = "";

            switch (i) {
                case 0:
                    if (categoria == 1) {
                        if (tema == 1) archivojson = "limites_sustitucion_directa_facil.json";
                        else if (tema == 2) archivojson = "limites_factorizables_facil.json";
                        else if (tema == 3) archivojson = "limites_laterales_facil.json";
                        else if (tema == 4) archivojson = "limites_infinitos_facil.json";
                        else if (tema == 5) archivojson = "limites_al_infinito_facil.json";
                    } else if (categoria == 2) {
                        if (tema == 1) archivojson = "derivadas_de_polinomios_facil.json";
                        else if (tema == 2) archivojson = "derivadas_de_inversas_facil.json";
                        else if (tema == 3) archivojson = "derivadas_de_radicales_facil.json";
                        else if (tema == 4) archivojson = "derivadas_de_exponenciales_facil.json";
                        else if (tema == 5) archivojson = "derivadas_de_trigonometricas_facil.json";
                        else if (tema == 6) archivojson = "derivadas_mult_y_div_facil.json";
                        else if (tema == 7) archivojson = "derivadas_regla_cadena_facil.json";
                    }
                    break;
                case 1:
                    if (categoria == 1) {
                        if (tema == 1) archivojson = "limites_sustitucion_directa_media.json";
                        else if (tema == 2) archivojson = "limites_factorizables_media.json";
                        else if (tema == 3) archivojson = "limites_laterales_media.json";
                        else if (tema == 4) archivojson = "limites_infinitos_media.json";
                        else if (tema == 5) archivojson = "limites_al_infinito_media.json";
                    } else if (categoria == 2) {
                        if (tema == 1) archivojson = "derivadas_de_polinomios_media.json";
                        else if (tema == 2) archivojson = "derivadas_de_inversas_media.json";
                        else if (tema == 3) archivojson = "derivadas_de_radicales_media.json";
                        else if (tema == 4) archivojson = "derivadas_de_exponenciales_media.json";
                        else if (tema == 5) archivojson = "derivadas_de_trigonometricas_media.json";
                        else if (tema == 6) archivojson = "derivadas_mult_y_div_media.json";
                        else if (tema == 7) archivojson = "derivadas_regla_cadena_media.json";
                    }
                    break;
                case 2:
                    if (categoria == 1) {
                        if (tema == 1) archivojson = "limites_sustitucion_directa_media.json";
                        else if (tema == 2) archivojson = "limites_factorizables_dificil.json";
                        else if (tema == 3) archivojson = "limites_laterales_dificil.json";
                        else if (tema == 4) archivojson = "limites_infinitos_dificil.json";
                        else if (tema == 5) archivojson = "limites_al_infinito_dificil.json";
                    } else if (categoria == 2) {
                        if (tema == 1) archivojson = "derivadas_de_polinomios_dificil.json";
                        else if (tema == 2) archivojson = "derivadas_de_inversas_dificil.json";
                        else if (tema == 3) archivojson = "derivadas_de_radicales_dificil.json";
                        else if (tema == 4) archivojson = "derivadas_de_exponenciales_dificil.json";
                        else if (tema == 5) archivojson = "derivadas_de_trigonometricas_dificil.json";
                        else if (tema == 6) archivojson = "derivadas_mult_y_div_dificil.json";
                        else if (tema == 7) archivojson = "derivadas_regla_cadena_facil.json";
                    }
                    break;
                default:
                    break;
            }

            cargarPack(archivojson);
        }

        mostrarPreguntaEnPantalla();

    }

    private void cargarPack(String filename) {

        String jsonquiz = loadJsonFromAsset(filename);
        List<QuestionsItem> listaPack = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonquiz);
            String arrayname = filename.substring(0, filename.length() - 5);
            JSONArray questions = jsonObject.getJSONArray(arrayname);

            for (int i = 0; i < questions.length(); i++) {

                JSONObject question = questions.getJSONObject(i);

                String[] labels = {question.getString("question"), question.getString("answer1"), question.getString("answer2"), question.getString("answer3"), question.getString("answer4")};
                int correcto = Integer.valueOf(question.getString("correct"));

                listaPack.add(new QuestionsItem(labels, correcto));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Collections.shuffle(listaPack);

        for (int i = 0; i < preguntasxPack; i++) {
            listaDePreguntas.add(listaPack.get(i));
        }


    }

    private String loadJsonFromAsset(String s) {
        String json = "";
        try {
            InputStream inputStream = getAssets().open(s);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private void mostrarPreguntaEnPantalla() {

        JLatexMathView latexLabels[] = {latex1, latex2, latex3, latex4, latex5};

        for (int i=0; i<5; i++){
            Drawable drw_answr = new Drawable();
            drw_answr.setFormula(listaDePreguntas.get(currentQuestion).getLabels()[i], 0xFFFFFFFF);
            latexLabels[i].setLatexDrawable(drw_answr.getDrawable());
        }

        int numAleatorio = 0;
        numAleatorio = (int) Math.floor(Math.random() * 10);

        if (numAleatorio == 7 && numVidas < 5) {
            vidaextra.setVisibility(View.VISIBLE);
            hayvidaextra = true;
        } else {
            vidaextra.setVisibility(View.INVISIBLE);
            hayvidaextra = false;
        }
    }


    private void playHeartAnimation() {
        vidaextra.animate()
                .translationX(vContainer.getX() - vidaextra.getX())
                .translationY(vContainer.getY() - vidaextra.getY())
                .alpha(0)
                .setDuration(500)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {
                        vidaextra.setVisibility(View.INVISIBLE);
                        vidaextra.animate()
                                .translationX(vidaextra.getX() - vContainer.getX())
                                .translationY(vidaextra.getY() - vContainer.getY())
                                .alpha(1)
                                .setDuration(500)
                                .setStartDelay(100)
                                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(@NonNull Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationEnd(@NonNull Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationCancel(@NonNull Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(@NonNull Animator animator) {

                                    }
                                });
                        numVidas++;
                        actualizarVidas();
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                });
    }
    private void actualizarVidas() {
        for (int i = 0; i < 5; i++) {
            if (i + 1 <= numVidas) vContainer.getChildAt(i).setVisibility(View.VISIBLE);
            else vContainer.getChildAt(i).setVisibility(View.GONE);
        }
        if (numVidas == 0) {
            Intent intent = new Intent(QuestionActivity.this, TemasActivity.class);
            guardarDatos();
            intent.putExtra("categoria", categoria);
            startActivity(intent);
            finish();
        }
    }

    private void actualizarPB() {
        float incremento = (float) currentQuestion / (float) totalPreguntas * 100;
        pb.setProgress((int) incremento);
    }

    private void guardarDatos() {
        File ruta = getApplicationContext().getFilesDir();
        if (categoria == 1) namefile = "porc_lim.tpo";
        else if (categoria == 2) namefile = "porc_der.tpo";
        File file = new File(ruta, namefile);
        if (file.exists()) {
            try {
                // leer archivo
                FileInputStream readfile = new FileInputStream(file);
                ObjectInputStream streamfile = new ObjectInputStream(readfile);
                List<String> porcentajes;
                porcentajes = (ArrayList<String>) streamfile.readObject();
                streamfile.close();
                float f_prc = (float) currentQuestion / (float) totalPreguntas * 100;
                int i_prc = (int) f_prc;
                int valor_anterior = parseInt(porcentajes.get(tema-1).substring(0, porcentajes.get(tema-1).length()-1));
                if (i_prc > valor_anterior) porcentajes.set(tema-1, i_prc + "%");

                // escribir archivo
                FileOutputStream writefile = new FileOutputStream(new File(ruta, namefile));
                ObjectOutputStream streamfile2 = new ObjectOutputStream(writefile);
                streamfile2.writeObject(porcentajes);
                streamfile2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // guardar la puntuacion
            File file2 = new File(ruta, namefile_punt);
            if (file2.exists()) {
                try {
                    // leer archivo
                    FileInputStream readfile = new FileInputStream(file2);
                    ObjectInputStream streamfile = new ObjectInputStream(readfile);
                    int punt_total = parseInt(streamfile.readObject().toString()) + puntuacion;
                    streamfile.close();

                    // escribir archivo
                    FileOutputStream writefile = new FileOutputStream(new File(ruta, namefile_punt));
                    ObjectOutputStream streamfile2 = new ObjectOutputStream(writefile);
                    streamfile2.writeObject(punt_total + "");
                    streamfile2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int msg = R.string.msg_back;
        int title = R.string.app_name;
        mostrarMensaje(title, msg, true, true, true);
    }
}
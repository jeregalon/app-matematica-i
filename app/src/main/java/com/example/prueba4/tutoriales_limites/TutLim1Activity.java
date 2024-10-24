package com.example.prueba4.tutoriales_limites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.QuestionActivity;
import com.example.prueba4.LevelsLimitsActivity;
import com.example.prueba4.classes.Drawable;
import com.example.prueba4.R;

import java.util.ArrayList;

import ru.noties.jlatexmath.JLatexMathView;

public class TutLim1Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex2, latex3, latex4, latex5, latex6, latex7, latex8;
    private String[] formulas = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_lim1);

        latex1 = findViewById(R.id.obj_latex1);
        latex2 = findViewById(R.id.obj_latex2);
        latex3 = findViewById(R.id.obj_latex3);
        latex4 = findViewById(R.id.obj_latex4);
        latex5 = findViewById(R.id.obj_latex5);
        latex6 = findViewById(R.id.obj_latex6);
        latex7 = findViewById(R.id.obj_latex7);

        Drawable drawable1 = new Drawable();
        drawable1.setFormula("f(x)=x^2", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\lim_{x \\to 2} f(x) = 4", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("\\lim_{x \\to 2} (x^2) = 2^2 = 4", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

        Drawable drawable4 = new Drawable();
        drawable4.setFormula("\\lim_{x \\to 3} (x^3+3x-4) \\\\=3^3+3*3-4\\\\=32", 0xFF000000);
        latex4.setLatexDrawable(drawable4.getDrawable());

        Drawable drawable5 = new Drawable();
        drawable5.setFormula("\\lim_{x \\to 2} \\left (\\frac{x-1}{x+3} \\right ) \\\\=\\frac{2-1}{2+3} \\\\=\\frac{1}{5}", 0xFF000000);
        latex5.setLatexDrawable(drawable5.getDrawable());

        Drawable drawable6 = new Drawable();
        drawable6.setFormula("\\lim_{x \\to 10} \\left (\\frac{1}{x} \\right ) \\\\=\\frac{1}{10}", 0xFF000000);
        latex6.setLatexDrawable(drawable6.getDrawable());

        Drawable drawable7 = new Drawable();
        drawable7.setFormula("\\lim_{x \\to 4} e^{x} \\\\=e^{4} \\\\=54.598150...", 0xFF000000);
        latex7.setLatexDrawable(drawable7.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutLim1Activity.this, LevelsLimitsActivity.class);
        intent.putExtra("categoria", 1);
        startActivity(intent);
        finish();
    }

}
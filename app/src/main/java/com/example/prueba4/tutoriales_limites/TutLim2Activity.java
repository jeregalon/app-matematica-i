package com.example.prueba4.tutoriales_limites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.LevelsLimitsActivity;
import com.example.prueba4.classes.Drawable;
import com.example.prueba4.R;

import ru.noties.jlatexmath.JLatexMathView;

public class TutLim2Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex2, latex3, latex4, latex5, latex6, latex7;
    private String[] formulas = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_lim2);

        latex1 = findViewById(R.id.obj_latex1);
        latex2 = findViewById(R.id.obj_latex2);
        latex3 = findViewById(R.id.obj_latex3);
        latex4 = findViewById(R.id.obj_latex4);
        latex5 = findViewById(R.id.obj_latex5);

        Drawable drawable1 = new Drawable();
        drawable1.setFormula("\\lim_{x \\to 3} (x^2) = 3^2 = 9", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("f(x)=\\frac{x-1}{x^2-1}", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("\\lim_{x \\to 1} \\left( \\frac{x-1}{x^2-1} \\right ) \\\\= \\frac{1-1}{1^2-1}=\\frac{0}{0}", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

        Drawable drawable4 = new Drawable();
        drawable4.setFormula("\\lim_{x \\to 1} \\left( \\frac{x-1}{x^2-1} \\right) \\\\= \\lim_{x \\to 1} \\left( \\frac{x-1}{(x+1)(x-1)} \\right) \\\\= \\lim_{x \\to 1} \\left( \\frac{1}{x+1} \\right)", 0xFF000000);
        latex4.setLatexDrawable(drawable4.getDrawable());

        Drawable drawable5 = new Drawable();
        drawable5.setFormula("\\frac{1}{1+1}=\\frac{1}{2}=0.5", 0xFF000000);
        latex5.setLatexDrawable(drawable5.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutLim2Activity.this, LevelsLimitsActivity.class);
        intent.putExtra("categoria", 1);
        startActivity(intent);
        finish();
    }

}
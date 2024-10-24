package com.example.prueba4.tutoriales_derivadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.classes.Drawable;
import com.example.prueba4.R;
import com.example.prueba4.LevelsDerivatesActivity;

import ru.noties.jlatexmath.JLatexMathView;

public class TutDer2Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex2, latex3, latex4, latex5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_der2);

        latex1 = findViewById(R.id.obj_latex1);
        latex2 = findViewById(R.id.obj_latex2);
        latex3 = findViewById(R.id.obj_latex3);
        latex4 = findViewById(R.id.obj_latex4);

        Drawable drawable1 = new Drawable();
        drawable1.setFormula("f(x)=\\frac{a}{bx}\\\\\\text{a,b}\\in$\\mathbb{R}$", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\frac{d}{dx} \\left( cx^n \\right) = n*c*x^{n-1}", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("\\frac{1}{x^{n}}=x^{-n}", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

        Drawable drawable4 = new Drawable();
        drawable4.setFormula("\\frac{d}{dx} \\left (\\frac{1}{x} \\right )\\\\" +
                "=\\frac{d}{dx}(x^{-1})\\\\" +
                "=-1*x^{-1-1}\\\\" +
                "=-x^{-2}\\\\" +
                "=-\\frac{1}{x^2}", 0xFF000000);
        latex4.setLatexDrawable(drawable4.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutDer2Activity.this, LevelsDerivatesActivity.class);
        intent.putExtra("categoria", 2);
        startActivity(intent);
        finish();
    }
}
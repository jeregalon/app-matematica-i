package com.example.prueba4.tutoriales_derivadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.classes.Drawable;
import com.example.prueba4.R;
import com.example.prueba4.LevelsDerivatesActivity;

import ru.noties.jlatexmath.JLatexMathView;

public class TutDer6Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_der6);

        latex1 = findViewById(R.id.obj_latex1);
        latex2 = findViewById(R.id.obj_latex2);

        Drawable drawable1 = new Drawable();
        drawable1.setFormula("\\frac{d}{dx}[f(x)*g(x)]=" +
                "\\\\g(x)*\\frac{d}{dx}f(x)+f(x)\\frac{d}{dx}g(x)", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\frac{d}{dx}$\\left[\\frac{f(x)}{g(x)}$\\right]=" +
                "\\\\\\frac{g(x)*\\frac{d}{dx}f(x)-f(x)\\frac{d}{dx}g(x)}{[g(x)]^2}", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutDer6Activity.this, LevelsDerivatesActivity.class);
        intent.putExtra("categoria", 2);
        startActivity(intent);
        finish();
    }
}
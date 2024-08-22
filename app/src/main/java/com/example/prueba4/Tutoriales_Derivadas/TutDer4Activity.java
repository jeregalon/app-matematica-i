package com.example.prueba4.Tutoriales_Derivadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.Clases.Drawable;
import com.example.prueba4.R;
import com.example.prueba4.TemasActivity;

import ru.noties.jlatexmath.JLatexMathView;

public class TutDer4Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex2, latex3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_der4);

        latex1 = findViewById(R.id.obj_latex1);
        latex2 = findViewById(R.id.obj_latex2);
        latex3 = findViewById(R.id.obj_latex3);

        Drawable drawable1 = new Drawable();
        drawable1.setFormula("f(x)=a^{x}\\\\a\\in$\\mathbb{R}$", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\frac{d}{dx} \\left ( a^{cx} \\right ) = c*a^{cx}*ln(a)" +
                "\\\\\\text{a,c}\\in$\\mathbb{R}$", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("\\frac{d}{dx} \\left ( e^{x} \\right ) = e^{x}", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutDer4Activity.this, TemasActivity.class);
        intent.putExtra("categoria", 2);
        startActivity(intent);
        finish();
    }
}
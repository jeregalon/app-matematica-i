package com.example.prueba4.Tutoriales_Derivadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.Clases.Drawable;
import com.example.prueba4.R;
import com.example.prueba4.TemasActivity;

import ru.noties.jlatexmath.JLatexMathView;

public class TutDer8Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex2, latex3, latex4, latex5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_der8);

        latex1 = findViewById(R.id.obj_latex1);
        latex2 = findViewById(R.id.obj_latex2);
        latex3 = findViewById(R.id.obj_latex3);
        latex4 = findViewById(R.id.obj_latex4);
        latex5 = findViewById(R.id.obj_latex5);

        Drawable drawable1 = new Drawable();
        drawable1.setFormula("\\lim_{x \\to a} \\frac{f(x)}{g(x)}", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\frac{0}{0} \\text{ o } \\frac{\\pm\\infty}{\\pm\\infty}", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("\\lim_{x \\to a} \\frac{f(x)}{g(x)} = \\lim_{x \\to a} \\frac{f'(x)}{g'(x)}", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

        Drawable drawable4 = new Drawable();
        drawable4.setFormula("\\lim_{x \\to \\infty} \\left ( \\frac{ln(x)}{x-1} \\right ) " +
                "\\\\ = \\lim_{x \\to \\infty} \\left ( \\frac{\\frac{1}{x}}{1} \\right )" +
                "\\\\ = 0", 0xFF000000);
        latex4.setLatexDrawable(drawable4.getDrawable());

        Drawable drawable5 = new Drawable();
        drawable5.setFormula("\\lim_{t \\to 9} \\left ( \\frac{9-t}{3-\\sqrt{t}}  \\right )" +
                "\\\\ = \\lim_{t \\to 9} \\left ( \\frac{-1}{-\\frac{1}{2 \\sqrt{t}}} \\right )" +
                "\\\\ = \\lim_{t \\to 9} \\left ( 2 \\sqrt{t \\right )" +
                "\\\\ = 6", 0xFF000000);
        latex5.setLatexDrawable(drawable5.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutDer8Activity.this, TemasActivity.class);
        intent.putExtra("categoria", 2);
        startActivity(intent);
        finish();
    }
}
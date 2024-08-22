package com.example.prueba4.Tutoriales_Limites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.TemasActivity;
import com.example.prueba4.Clases.Drawable;
import com.example.prueba4.R;

import ru.noties.jlatexmath.JLatexMathView;

public class TutLim3Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex2, latex3, latex4, latex5, latex6, latex7,
            latex8, latex9, latex10, latex11, latex12;
    private String[] formulas = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_lim3);

        latex1 = findViewById(R.id.obj_latex1);
        latex2 = findViewById(R.id.obj_latex2);
        latex3 = findViewById(R.id.obj_latex3);
        latex4 = findViewById(R.id.obj_latex4);
        latex5 = findViewById(R.id.obj_latex5);
        latex6 = findViewById(R.id.obj_latex6);
        latex7 = findViewById(R.id.obj_latex7);
        latex8 = findViewById(R.id.obj_latex8);
        latex9 = findViewById(R.id.obj_latex9);
        latex10 = findViewById(R.id.obj_latex10);
        latex11 = findViewById(R.id.obj_latex11);
        latex12 = findViewById(R.id.obj_latex12);

        Drawable drawable1 = new Drawable();
        drawable1.setFormula("\\[ H(t) = \\begin{cases} 0 & \\text{ si } t < 0 \\\\ 1 & \\text{ si } t \\ge 0 \\end{cases} \\]", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\lim_{t \\to 0} H(t)", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("\\lim_{t \\to 0^{-}} H(t)", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

        Drawable drawable4 = new Drawable();
        drawable4.setFormula("\\lim_{t \\to 0^{+}} H(t)", 0xFF000000);
        latex4.setLatexDrawable(drawable4.getDrawable());

        Drawable drawable5 = new Drawable();
        drawable5.setFormula("\\lim_{t \\to a^{-}} f(x) \\\\ \\lim_{t \\to a^{+}} f(x)", 0xFF000000);
        latex5.setLatexDrawable(drawable5.getDrawable());

        Drawable drawable6 = new Drawable();
        drawable6.setFormula("g(x)=\\frac{2x+12}{\\left | x+6 \\right |}", 0xFF000000);
        latex6.setLatexDrawable(drawable6.getDrawable());

        Drawable drawable7 = new Drawable();
        drawable7.setFormula("\\lim_{x \\to -6^{-}} \\frac{2x+12}{\\left | x+6 \\right |}", 0xFF000000);
        latex7.setLatexDrawable(drawable7.getDrawable());

        Drawable drawable8 = new Drawable();
        drawable8.setFormula("\\frac{2(-6.01)+12}{\\left | -6.01+6 \\right |}=-2 \\\\\\frac{2(-6.001)+12}{\\left | -6.001+6 \\right |}=-2 \\\\\\frac{2(-6.0001)+12}{\\left | -6.0001+6 \\right |}=-2", 0xFF000000);
        latex8.setLatexDrawable(drawable8.getDrawable());

        Drawable drawable9 = new Drawable();
        drawable9.setFormula("\\lim_{x \\to -6^{+}} \\frac{2x+12}{\\left | x+6 \\right |}", 0xFF000000);
        latex9.setLatexDrawable(drawable9.getDrawable());

        Drawable drawable10 = new Drawable();
        drawable10.setFormula("\\frac{2(-5.9)+12}{\\left | -5.9+6 \\right |}=2 \\\\\\frac{2(-5.99)+12}{\\left | -5.99+6 \\right |}=2 \\\\\\frac{2(-5.999)+12}{\\left | -5.999+6 \\right |}=2", 0xFF000000);
        latex10.setLatexDrawable(drawable10.getDrawable());

        Drawable drawable11 = new Drawable();
        drawable11.setFormula("\\lim_{t \\to -6} g(x) \\text{ NO EXISTE}", 0xFF000000);
        latex11.setLatexDrawable(drawable11.getDrawable());

        Drawable drawable12 = new Drawable();
        drawable12.setFormula("\\lim_{x \\to a} f(x) \\text{ existe } \\\\ \\text{ si y solo si: } \\\\ \\lim_{x \\to a^{-}} f(x) = \\lim_{x \\to a^{+}} f(x) = L \\\\ (L\\in$\\mathbb{R}$)", 0xFF000000);
        latex12.setLatexDrawable(drawable12.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutLim3Activity.this, TemasActivity.class);
        intent.putExtra("categoria", 1);
        startActivity(intent);
        finish();
    }

}
package com.example.prueba4.Tutoriales_Limites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.TemasActivity;
import com.example.prueba4.Clases.Drawable;
import com.example.prueba4.R;

import ru.noties.jlatexmath.JLatexMathView;

public class TutLim5Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex2, latex3, latex4, latex5, latex6, latex7, latex8,
    latex9, latex10, latex11, latex12, latex13, latex14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_lim5);

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


        Drawable drawable1 = new Drawable();
        drawable1.setFormula("f(x)=x^2", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\lim_{x \\to \\infty} x^2 = \\infty" +
                "\\\\ \\text{y } \\lim_{x \\to -\\infty} x^2 = \\infty", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("f(x)=x^3", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

        Drawable drawable4 = new Drawable();
        drawable4.setFormula("\\lim_{x \\to \\infty} x^3=\\infty" +
                "\\\\ \\text{y }\\lim_{x \\to -\\infty} x^3=-\\infty", 0xFF000000);
        latex4.setLatexDrawable(drawable4.getDrawable());

        Drawable drawable5 = new Drawable();
        drawable5.setFormula("f(x)=\\frac{x^2-1}{x^2+1}", 0xFF000000);
        latex5.setLatexDrawable(drawable5.getDrawable());

        Drawable drawable6 = new Drawable();
        drawable6.setFormula("\\lim_{x \\to \\infty} \\left (\\frac{x^2-1}{x^2+1} \\right)=1 \\\\\\text{ y } \\lim_{x \\to -\\infty} \\left (\\frac{x^2-1}{x^2+1} \\right)=1", 0xFF000000);
        latex6.setLatexDrawable(drawable6.getDrawable());

        Drawable drawable7 = new Drawable();
        drawable7.setFormula("g(x)=\\frac{a_{0} + a_{1}x - a_{2}x^{2}+...+ a_{k}x^{k}}" +
                "{b_{0} + b_{1}x - b_{2}x^{2} +...+ b_{s}x^{s}}", 0xFF000000);
        latex7.setLatexDrawable(drawable7.getDrawable());

        Drawable drawable8 = new Drawable();
        drawable8.setFormula("\\lim_{x \\to \\infty} g(x) = \\frac{\\infty}{\\infty}", 0xFF000000);
        latex8.setLatexDrawable(drawable8.getDrawable());

        Drawable drawable9 = new Drawable();
        drawable9.setFormula("\\lim_{x \\to \\infty} g(x) = \\left (\\text{signo } \\frac{a_k}{b_s} \\right ) \\infty", 0xFF000000);
        latex9.setLatexDrawable(drawable9.getDrawable());

        Drawable drawable10 = new Drawable();
        drawable10.setFormula("\\lim_{x \\to \\infty} g(x) = 0", 0xFF000000);
        latex10.setLatexDrawable(drawable10.getDrawable());

        Drawable drawable11 = new Drawable();
        drawable11.setFormula("\\lim_{x \\to \\infty} g(x) = \\frac{a_k}{b_s}", 0xFF000000);
        latex11.setLatexDrawable(drawable11.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutLim5Activity.this, TemasActivity.class);
        intent.putExtra("categoria", 1);
        startActivity(intent);
        finish();
    }

}
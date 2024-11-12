package com.example.prueba4.tutoriales_limites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.LevelsLimitsActivity;
import com.example.prueba4.classes.Drawable;
import com.example.prueba4.R;

import ru.noties.jlatexmath.JLatexMathView;

public class TutLim4Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex2, latex3, latex4, latex5,
    latex6, latex7, latex8, latex9, latex10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_lim4);

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

        Drawable drawable1 = new Drawable();
        drawable1.setFormula("f(x)=\\frac{1}{x}", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\lim_{x \\to 0^{-}} \\left( \\frac{1}{x} \\right)=-\\infty \\\\ \\lim_{x \\to 0^{+}} \\left( \\frac{1}{x} \\right)=\\infty", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("\\text{Ejemplo 1: }" +
                "\\\\ \\lim_{x \\to 0} \\left( \\frac{1}{x^2} \\right)", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

        Drawable drawable4 = new Drawable();
        drawable4.setFormula("\\lim_{x \\to 0} \\left( \\frac{1}{x^2} \\right) = \\infty", 0xFF000000);
        latex4.setLatexDrawable(drawable4.getDrawable());

        Drawable drawable5 = new Drawable();
        drawable5.setFormula("\\text{Ejemplo 2: }" +
                "\\\\ \\lim_{x \\to 3^{+}} \\left( \\frac{2x}{x-3} \\right)", 0xFF000000);
        latex5.setLatexDrawable(drawable5.getDrawable());

        Drawable drawable6 = new Drawable();
        drawable6.setFormula("\\frac{2*3.1}{3.1-3}=62" +
                "\\\\ \\frac{2*3.001}{3.001-3}=6002" +
                "\\\\ \\frac{2*3.00001}{3.00001-3}=600002", 0xFF000000);
        latex6.setLatexDrawable(drawable6.getDrawable());

        Drawable drawable7 = new Drawable();
        drawable7.setFormula("\\lim_{x \\to 3^{+}} \\left( \\frac{2x}{x-3} \\right) = \\infty", 0xFF000000);
        latex7.setLatexDrawable(drawable7.getDrawable());

        Drawable drawable8 = new Drawable();
        drawable8.setFormula("\\text{Ejemplo 3: }" +
                "\\\\ \\lim_{x \\to 3^{-}} \\left( \\frac{2x}{x-3} \\right)", 0xFF000000);
        latex8.setLatexDrawable(drawable8.getDrawable());

        Drawable drawable9 = new Drawable();
        drawable9.setFormula("\\frac{2*2.9}{2.9-3}=-58" +
                "\\\\ \\frac{2*2.99}{2.99-3}=-598" +
                "\\\\ \\frac{2*2.9999}{2.9999-3}=-59998", 0xFF000000);
        latex9.setLatexDrawable(drawable9.getDrawable());

        Drawable drawable10 = new Drawable();
        drawable10.setFormula("\\lim_{x \\to 3^{-}} \\left( \\frac{2x}{x-3} \\right) = -\\infty", 0xFF000000);
        latex10.setLatexDrawable(drawable10.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutLim4Activity.this, LevelsLimitsActivity.class);
        intent.putExtra("categoria", 1);
        startActivity(intent);
        finish();
    }

}
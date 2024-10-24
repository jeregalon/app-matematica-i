package com.example.prueba4.tutoriales_derivadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.classes.Drawable;
import com.example.prueba4.R;
import com.example.prueba4.LevelsDerivatesActivity;

import ru.noties.jlatexmath.JLatexMathView;

public class TutDer1Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex2, latex3, latex4, latex5, latex6, latex7, latex8, latex9, latex10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_der1);

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
        drawable1.setFormula("f(x)=c\\\\c\\in$\\mathbb{R}$", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\frac{d}{dx} \\left( c \\right) = 0", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("f(x)=cx\\\\c\\in$\\mathbb{R}$", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

        Drawable drawable4 = new Drawable();
        drawable4.setFormula("\\frac{d}{dx} \\left( cx \\right) = c", 0xFF000000);
        latex4.setLatexDrawable(drawable4.getDrawable());

        Drawable drawable5 = new Drawable();
        drawable5.setFormula("f(x)=cx^{2}\\\\c\\in$\\mathbb{R}$", 0xFF000000);
        latex5.setLatexDrawable(drawable5.getDrawable());

        Drawable drawable6 = new Drawable();
        drawable6.setFormula("\\frac{d}{dx} \\left( cx^{2} \\right) = 2cx", 0xFF000000);
        latex6.setLatexDrawable(drawable6.getDrawable());

        Drawable drawable7 = new Drawable();
        drawable7.setFormula("\\frac{d}{dx} \\left( cx^n \\right) = n*c*x^{n-1}", 0xFF000000);
        latex7.setLatexDrawable(drawable7.getDrawable());

        Drawable drawable8 = new Drawable();
        drawable8.setFormula("\\frac{d}{dx}cf(x)=c\\frac{d}{dx}f(x)", 0xFF000000);
        latex8.setLatexDrawable(drawable8.getDrawable());

        Drawable drawable9 = new Drawable();
        drawable9.setFormula("\\frac{d}{dx}[f(x)+g(x)]=\\frac{d}{dx}f(x)+\\frac{d}{dx}g(x)", 0xFF000000);
        latex9.setLatexDrawable(drawable9.getDrawable());

        Drawable drawable10 = new Drawable();
        drawable10.setFormula("\\frac{d}{dx}[f(x)-g(x)]=\\frac{d}{dx}f(x)-\\frac{d}{dx}g(x)", 0xFF000000);
        latex10.setLatexDrawable(drawable10.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutDer1Activity.this, LevelsDerivatesActivity.class);
        intent.putExtra("categoria", 2);
        startActivity(intent);
        finish();
    }
}
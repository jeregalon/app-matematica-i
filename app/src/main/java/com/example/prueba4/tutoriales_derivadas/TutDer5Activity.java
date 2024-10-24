package com.example.prueba4.tutoriales_derivadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.classes.Drawable;
import com.example.prueba4.R;
import com.example.prueba4.LevelsDerivatesActivity;

import ru.noties.jlatexmath.JLatexMathView;

public class TutDer5Activity extends AppCompatActivity {

    private JLatexMathView latex1, latex2, latex3, latex4, latex5, latex6, latex7, latex8, latex9, latex10, latex11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_der5);

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
        drawable1.setFormula("f(x)=a\\,\\sin\\left( b\\,x \\right)\\\\ \\text{a,b}\\in$\\mathbb{R}$", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\frac{d}{dx} \\left( a\\,\\sin\\left( b\\,x \\right)  \\right) = a\\,b\\,\\cos\\left( b\\,x \\right)", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("\\frac{d}{dx} \\left( a\\,\\cos\\left( b\\,x \\right)  \\right) = -a\\,b\\,\\sin\\left( b\\,x \\right)", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

        Drawable drawable4 = new Drawable();
        drawable4.setFormula("f(x)=\\mathrm{tan}\\left(x\\right)=\\frac{\\sin(x)}{\\cos(x)}", 0xFF000000);
        latex4.setLatexDrawable(drawable4.getDrawable());

        Drawable drawable5 = new Drawable();
        drawable5.setFormula("\\frac{d}{dx}\\left(\\mathrm{tan}\\left(x\\right)\\right)=\\mathrm{sec}^{2}\\left(x\\right)", 0xFF000000);
        latex5.setLatexDrawable(drawable5.getDrawable());

        Drawable drawable6 = new Drawable();
        drawable6.setFormula("f(x)=\\mathrm{cot}\\left(x\\right)=\\frac{\\cos(x)}{\\sin(x)}", 0xFF000000);
        latex6.setLatexDrawable(drawable6.getDrawable());

        Drawable drawable7 = new Drawable();
        drawable7.setFormula("\\frac{d}{dx}\\left(\\mathrm{cot}\\left(x\\right)\\right)=-\\mathrm{csc}^{2}\\left(x\\right)", 0xFF000000);
        latex7.setLatexDrawable(drawable7.getDrawable());

        Drawable drawable8 = new Drawable();
        drawable8.setFormula("f(x)=\\mathrm{sec}\\left(x\\right)=\\frac{1}{\\cos(x)}", 0xFF000000);
        latex8.setLatexDrawable(drawable8.getDrawable());

        Drawable drawable9 = new Drawable();
        drawable9.setFormula("\\frac{d}{dx}\\left(\\mathrm{sec}\\left(x\\right)\\right)=\\mathrm{sec}\\left(x\\right)\\mathrm{tan}\\left(x\\right)", 0xFF000000);
        latex9.setLatexDrawable(drawable9.getDrawable());

        Drawable drawable10 = new Drawable();
        drawable10.setFormula("f(x)=\\mathrm{csc}\\left(x\\right)=\\frac{1}{\\sin(x)}", 0xFF000000);
        latex10.setLatexDrawable(drawable10.getDrawable());

        Drawable drawable11 = new Drawable();
        drawable11.setFormula("\\frac{d}{dx}\\left(\\mathrm{csc}\\left(x\\right)\\right)=-\\mathrm{csc}\\left(x\\right)\\mathrm{cot}\\left(x\\right)", 0xFF000000);
        latex11.setLatexDrawable(drawable11.getDrawable());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutDer5Activity.this, LevelsDerivatesActivity.class);
        intent.putExtra("categoria", 2);
        startActivity(intent);
        finish();
    }
}
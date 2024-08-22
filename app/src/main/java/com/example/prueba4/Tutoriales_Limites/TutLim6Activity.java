package com.example.prueba4.Tutoriales_Limites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.TemasActivity;
import com.example.prueba4.Clases.Drawable;
import com.example.prueba4.R;

import ru.noties.jlatexmath.JLatexMathView;

public class TutLim6Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex1_5, latex2, latex3, latex4, latex5, latex5_5, latex6, latex7, latex8,
    latex9, latex10, latex11, latex12, latex13, latex14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_lim6);

        latex1 = findViewById(R.id.obj_latex1);
        latex1_5 = findViewById(R.id.obj_latex1_5);
        latex2 = findViewById(R.id.obj_latex2);
        latex3 = findViewById(R.id.obj_latex3);
        latex4 = findViewById(R.id.obj_latex4);
        latex5 = findViewById(R.id.obj_latex5);
        latex5_5 = findViewById(R.id.obj_latex5_5);
        latex6 = findViewById(R.id.obj_latex6);
        latex7 = findViewById(R.id.obj_latex7);
//        latex8 = findViewById(R.id.obj_latex8);
//        latex9 = findViewById(R.id.obj_latex9);
//        latex10 = findViewById(R.id.obj_latex10);
//        latex11 = findViewById(R.id.obj_latex11);
//        latex12 = findViewById(R.id.obj_latex12);
//        latex13 = findViewById(R.id.obj_latex13);
//        latex14 = findViewById(R.id.obj_latex14);

        Drawable drawable1 = new Drawable();
        drawable1.setFormula("\\lim_{x \\to a} f(x) = f(a)", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable1_5 = new Drawable();
        drawable1_5.setFormula("\\text{1. } f(a) \\text{ exista}" +
                "\\\\ \\text{2. } \\lim_{x \\to a} f(x) \\text{ exista}" +
                "\\\\ \\text{3. } \\lim_{x \\to a} f(x) = f(a)", 0xFF000000);
        latex1_5.setLatexDrawable(drawable1_5.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("f(x)=\\frac{\\sqrt{t^2+9}-3}{t^2}", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("f(x)=\\begin{cases} \\frac{\\sqrt{t^2+9}-3}{t^2} & \\text{ si } x \\neq 0 " +
                "\\\\ 0.5 & \\text{ si } x = 0 \\end{cases}", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

        Drawable drawable4 = new Drawable();
        drawable4.setFormula("\\lim_{x \\to a} f(x) \\neq f(a)", 0xFF000000);
        latex4.setLatexDrawable(drawable4.getDrawable());

        Drawable drawable5 = new Drawable();
        drawable5.setFormula("\\lim_{t \\to 0^{-}} H(t) \\text{ existe}" +
                "\\\\ \\lim_{t \\to 0^{+}} H(t) \\text{ existe}" +
                "\\\\ \\text{pero } \\lim_{t \\to 0^{-}} H(t) \\neq \\lim_{t \\to 0^{+}} H(t)" +
                "\\\\ \\text{Por tanto: } \\lim_{t \\to 0} H(t) \\text{ no existe}", 0xFF000000);
        latex5.setLatexDrawable(drawable5.getDrawable());

        Drawable drawable5_5 = new Drawable();
        drawable5_5.setFormula("f(x)=\\frac{1}{x^2}", 0xFF000000);
        latex5_5.setLatexDrawable(drawable5_5.getDrawable());

        Drawable drawable6 = new Drawable();
        drawable6.setFormula("\\lim_{x \\to 0^{-}} f(x) =\\infty" +
                "\\\\ \\lim_{x \\to 0^{-}} f(x) =\\infty", 0xFF000000);
        latex6.setLatexDrawable(drawable6.getDrawable());

        Drawable drawable7 = new Drawable();
        drawable7.setFormula("(-\\infty; 0)" +
                "\\\\ (0; \\infty)", 0xFF000000);
        latex7.setLatexDrawable(drawable7.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutLim6Activity.this, TemasActivity.class);
        intent.putExtra("categoria", 1);
        startActivity(intent);
        finish();
    }

}
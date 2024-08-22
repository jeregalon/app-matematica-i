package com.example.prueba4.Tutoriales_Derivadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.Clases.Drawable;
import com.example.prueba4.R;
import com.example.prueba4.TemasActivity;

import ru.noties.jlatexmath.JLatexMathView;

public class TutDer7Activity extends AppCompatActivity {
    private JLatexMathView latex1, latex2, latex3, latex4, latex5, latex6, latex7, latex8, latex9, latex10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_der7);

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
        drawable1.setFormula("F_1(x)=cos(e^{x}) \\\\ F_2(x)=\\sqrt{x^2+1} \\\\ F_3(x)=ln(\\sqrt{x}) \\\\ F_4(x)=(x^2+1)^3", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\text{Si } F(x)=f[g(x)]" +
                "\\\\ F'(x)=f'[g(x)]*g'(x)", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

        Drawable drawable3 = new Drawable();
        drawable3.setFormula("F_1(x)=cos(e^{x}) " +
                "\\\\ f(x)=\\cos\\left(g\\left(x\\right)\\right) \\text{, } g(x)=e^{x}", 0xFF000000);
        latex3.setLatexDrawable(drawable3.getDrawable());

        Drawable drawable4 = new Drawable();
        drawable4.setFormula("F_2(x)=\\sqrt{x^2+1} " +
                "\\\\ f(x)=\\sqrt{g(x)} \\text{, } g(x)=x^2+1", 0xFF000000);
        latex4.setLatexDrawable(drawable4.getDrawable());

        Drawable drawable5 = new Drawable();
        drawable5.setFormula("F_3(x)=ln(\\sqrt{x}) " +
                "\\\\ f(x)=ln \\left(g(x)\\right) \\text{, } g(x)=\\sqrt{x}", 0xFF000000);
        latex5.setLatexDrawable(drawable5.getDrawable());

        Drawable drawable6 = new Drawable();
        drawable6.setFormula("F_4(x)=(x^2+1)^3" +
                "\\\\ f(x)=\\left(g\\left(x\\right)\\right)^3 \\text{, } g(x)=x^2+1", 0xFF000000);
        latex6.setLatexDrawable(drawable6.getDrawable());


        Drawable drawable7 = new Drawable();
        drawable7.setFormula("F'_1(x)=[cos(e^{x})]'*(e^{x})'" +
                "\\\\ F'_1(x)=-sen(e^{x})*e^{x}" +
                "\\\\ F'_1(x)=-e^{x}sen(e^{x})", 0xFF000000);
        latex7.setLatexDrawable(drawable7.getDrawable());

        Drawable drawable8 = new Drawable();
        drawable8.setFormula("F'_2(x)=[\\sqrt{x^2+1}]'*(x^2+1)'" +
                "\\\\ F'_2(x)=\\frac{1}{2 \\sqrt{x^2+1}}*2x" +
                "\\\\ F'_2(x)=\\frac{2x}{2 \\sqrt{x^2+1}}", 0xFF000000);
        latex8.setLatexDrawable(drawable8.getDrawable());

        Drawable drawable9 = new Drawable();
        drawable9.setFormula("F'_3(x)=[ln(\\sqrt{x})]'*(\\sqrt{x})'" +
                "\\\\ F'_3(x)=\\frac{1}{\\sqrt{x}}*\\frac{1}{2 \\sqrt{x}}" +
                "\\\\ F'_3(x)=\\frac{1}{2x}", 0xFF000000);
        latex9.setLatexDrawable(drawable9.getDrawable());

        Drawable drawable10 = new Drawable();
        drawable10.setFormula("F'_4(x)=[(x^2+1)^3]'*(x^2+1)'" +
                "\\\\ F'_4(x)=3(x^2+1)^2*2x" +
                "\\\\ F'_4(x)=6x(x^2+1)^2", 0xFF000000);
        latex10.setLatexDrawable(drawable10.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutDer7Activity.this, TemasActivity.class);
        intent.putExtra("categoria", 2);
        startActivity(intent);
        finish();
    }
}
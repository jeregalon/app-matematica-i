package com.example.prueba4.Tutoriales_Derivadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.prueba4.Clases.Drawable;
import com.example.prueba4.R;
import com.example.prueba4.TemasActivity;

import ru.noties.jlatexmath.JLatexMathView;

public class TutDer3Activity extends AppCompatActivity {

    private JLatexMathView latex1, latex2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut_der3);

        latex1 = findViewById(R.id.obj_latex1);
        latex2 = findViewById(R.id.obj_latex2);


        Drawable drawable1 = new Drawable();
        drawable1.setFormula("f(x)=\\sqrt(cx)\\\\c\\in$\\mathbb{R}$", 0xFF000000);
        latex1.setLatexDrawable(drawable1.getDrawable());

        Drawable drawable2 = new Drawable();
        drawable2.setFormula("\\frac{d}{dx}(\\sqrt{x})\\\\=\\frac{d}{dx}(x^{\\frac{1}{2}})" +
                "\\\\=\\frac{1}{2}x^{\\frac{1}{2}-1}\\\\=\\frac{1}{2}x^{-\\frac{1}{2}}" +
                "\\\\=\\frac{1}{2x^{\\frac{1}{2}}}\\\\=\\frac{1}{2\\sqrt{x}}", 0xFF000000);
        latex2.setLatexDrawable(drawable2.getDrawable());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TutDer3Activity.this, TemasActivity.class);
        intent.putExtra("categoria", 2);
        startActivity(intent);
        finish();
    }
}
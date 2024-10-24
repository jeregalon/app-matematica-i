package com.example.prueba4.classes;

import ru.noties.jlatexmath.JLatexMathDrawable;

public class Drawable {
    private String formula;
    private int color;
    private JLatexMathDrawable drawable;

    public void setFormula(String formula, int color) {
        this.formula = formula;
        this.color = color;

        drawable = JLatexMathDrawable.builder(formula)
                .textSize(70)
                .color(color)
                .padding(8)
                .align(JLatexMathDrawable.ALIGN_RIGHT)
                .build();
    }
    public JLatexMathDrawable getDrawable() {
        return drawable;
    }
}

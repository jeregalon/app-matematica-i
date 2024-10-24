package com.example.prueba4

import QuestionViewModel
import android.animation.Animator
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prueba4._backend.SupabaseAuthViewModel
import com.example.prueba4._backend.data.model.UserState
import com.example.prueba4._backend.data.network.SupabaseClient.client
import com.example.prueba4.classes.Drawable
import com.example.prueba4.classes.QuestionsItem
import com.example.prueba4.ui.theme.WhiteColor
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.put
import org.json.JSONException
import org.json.JSONObject
import ru.noties.jlatexmath.JLatexMathView
import java.io.IOException
import java.util.Collections
import kotlin.math.floor

// Actividad del quiz de preguntas y respuestas
class QuestionActivity : AppCompatActivity() {
    private lateinit var latex1: JLatexMathView
    private lateinit var latex2: JLatexMathView
    private lateinit var latex3: JLatexMathView
    private lateinit var latex4: JLatexMathView
    private lateinit var latex5: JLatexMathView

    // Textos, en LaTex, de la pregunta y las opciones de respuesta
    lateinit var listaDePreguntas: MutableList<QuestionsItem?>
    var packDePreguntas: Int = 1
    var currentQuestion: Int = 0
    var currentPackQuestion: Int = 0
    var currentPack: Int = 0
    var totalPreguntas: Int = 9
    var preguntasxPack: Int = 3
    var correct: Int = 0
    var wrong: Int = 0

    // Conteo de preguntas correctas e incorrectas
    var numVidas: Int = 3
    var derMode: Boolean = true

    // Contenido que se va a ejercitar (límites '0', derivadas '1' o integrales '2')
    var level: Int = 1
    var countAnimation: Int = 0
    var loadingQuestions: Boolean = false
    lateinit var nextqu: Button

    // Botón de Siguiente Pregunta (Next Question)
    lateinit var pb: ProgressBar

    // Barra de progreso del juego
    lateinit var oContainer: ConstraintLayout
    lateinit var vContainer: LinearLayout
    lateinit var vidaextra: ImageView
    var hayvidaextra: Boolean = false
    var namefile: String = ""

    val viewModel : QuestionViewModel by viewModels()
    val supabaseViewModel : SupabaseAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        latex1 = findViewById(R.id.obj_latex)
        latex2 = findViewById(R.id.obj_latex_a)
        latex3 = findViewById(R.id.obj_latex_b)
        latex4 = findViewById(R.id.obj_latex_c)
        latex5 = findViewById(R.id.obj_latex_d)

        oContainer = findViewById(R.id.optionsContainer)

        vContainer = findViewById(R.id.vidasContainer)

        nextqu = findViewById(R.id.btnNext)

        nextqu.setEnabled(false)

        pb = findViewById(R.id.progressBar)

        derMode = intent.getBooleanExtra("derMode", true)

        level = intent.getIntExtra("level", 1)

        vidaextra = findViewById(R.id.vida6)
        vidaextra.setVisibility(View.INVISIBLE)

        actualizarPB()

        mostrarMensajeTransparente()

        for (i in 0..3) {
            oContainer.getChildAt(i).setBackgroundResource(R.drawable.question_enable)
        }

        for (i in 0..3) {
            val finalI = i

            // Cuando se oprime una de las opciones:
            oContainer.getChildAt(i).setOnClickListener {
                nextqu.setEnabled(true) // Habilita el botón de siguiente pregunta
                nextqu.setBackgroundResource(R.drawable.btn_next)

                for (j in 0..3) {
                    oContainer.getChildAt(j).isClickable = false
                }

                // Si la opción es la correcta
                val el_correcto = listaDePreguntas!![currentQuestion]!!.correct
                if (el_correcto == finalI) {
                    correct++ // Se incrementa el contador de preguntas correctas
                    if (currentPack == 0) viewModel.increaseBy5()
                    else if (currentPack == 1) viewModel.increaseBy10()
                    else viewModel.increaseBy15()
                    if (hayvidaextra) {
                        playHeartAnimation()
                    }
                    oContainer.getChildAt(finalI)
                        .setBackgroundResource(R.drawable.right_answr) // La opción se pone en verde

                    // Si no es la correcta
                } else {
                    wrong++ // Se incrementa el contador de preguntas incorrectas
                    if (numVidas > 0) numVidas--
                    actualizarVidas()
                    oContainer.getChildAt(finalI).setBackgroundResource(R.drawable.wrong_answr)
                    oContainer.getChildAt(el_correcto).setBackgroundResource(R.drawable.right_answr)
                }
            }
        }

        // Cuando se oprime el botón de siguiente pregunta
        nextqu.setOnClickListener(View.OnClickListener {
            loadingQuestions = true
            // Si no se ha llegado al final de las preguntas
            if (currentQuestion < totalPreguntas - 1) {
                currentQuestion++
                actualizarPB()
                countAnimation = 0
                playAnimation(oContainer.getChildAt(0), 0)

                currentPackQuestion++

                if (currentPackQuestion >= preguntasxPack) {
                    currentPackQuestion = 0
                    currentPack++
                    mostrarMensajeTransparente()
                }
            } else {
                // llegó al final
                currentPack++
                currentQuestion++
                pb.setProgress(100)
                actualizarPuntuacion()
                mostrarMensajeTransparente()
            }
        })

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    val msg = R.string.msg_back
                    val title = R.string.app_name
                    mostrarMensaje(title, msg, true, true, true)
                }
            }
        onBackPressedDispatcher.addCallback(this, callback)

        val pointsView = findViewById<ComposeView>(R.id.points)
        pointsView.setContent {
            pointsCounter(viewModel = viewModel)
        }
    }

    @Composable
    private fun pointsCounter(viewModel: QuestionViewModel) {
        Text(
            text = viewModel.puntuation.value.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = WhiteColor
        )
    }

    private fun actualizarPuntuacion() {
        val currentUser = client.auth.currentUserOrNull()
        if (currentUser != null) {
            val currentPoints = currentUser.userMetadata?.get("puntuation").toString().toInt()
            val nick = currentUser.userMetadata?.get("nickname").toString()
            val totalPuntuation = viewModel.puntuation.value + currentPoints

            supabaseViewModel.update(totalPuntuation)
        }
    }

    private fun playAnimation(view: View?, value: Int) {
        view!!.animate()
            .alpha(value.toFloat())
            .scaleX(value.toFloat())
            .scaleY(value.toFloat())
            .setDuration(500)
            .setStartDelay(100)
            .setInterpolator(DecelerateInterpolator())
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {
                    if (value == 0) {
                        nextqu.isEnabled = false
                        nextqu.setBackgroundResource(R.drawable.btn_disable)

                        when (countAnimation) {
                            0 -> playAnimation(latex1, 0)
                            1 -> playAnimation(oContainer.getChildAt(1), 0)
                            2 -> playAnimation(oContainer.getChildAt(2), 0)
                            3 -> playAnimation(oContainer.getChildAt(3), 0)
                            else -> {}
                        }
                        countAnimation++
                    }
                }

                override fun onAnimationEnd(animator: Animator) {
                    if (value == 0) {
                        playAnimation(view, 1)

                        if (loadingQuestions == true) {
                            mostrarPreguntaEnPantalla() // Se muestra la siguiente pregunta en pantalla

                            // Se habilitan las cuatro opciones
                            for (i in 0..3) {
                                oContainer.getChildAt(i).isClickable = true
                                oContainer.getChildAt(i)
                                    .setBackgroundResource(R.drawable.question_enable)
                            }

                            loadingQuestions = false
                        }
                    }
                }

                override fun onAnimationCancel(animator: Animator) {
                }

                override fun onAnimationRepeat(animator: Animator) {
                }
            })
    }

    private fun mostrarMensaje(
        titulo: Int,
        mensaje: Int,
        negButton: Boolean,
        backPressed: Boolean,
        cancelable: Boolean
    ) {
        val AlertDialogBuilder = AlertDialog.Builder(this@QuestionActivity)
        AlertDialogBuilder.setTitle(titulo)
        AlertDialogBuilder.setMessage(mensaje)
        if (!cancelable) AlertDialogBuilder.setCancelable(false)
        if (negButton) {
            AlertDialogBuilder.setNegativeButton(android.R.string.no) { dialogInterface, i -> dialogInterface.dismiss() }
        }
        AlertDialogBuilder.setPositiveButton(android.R.string.yes) { dialogInterface, i ->
            if (backPressed) {
                var intent: Intent? = null
                intent =
                    if (derMode) Intent(this@QuestionActivity, LevelsDerivatesActivity::class.java)
                    else Intent(this@QuestionActivity, LevelsLimitsActivity::class.java)
                startActivity(intent)
                finish()
            } else {
            }
        }
        AlertDialogBuilder.show()
    }

    private fun mostrarMensajeTransparente() {
        val abuilder = AlertDialog.Builder(this@QuestionActivity)
        val inflater = layoutInflater
        var layout = 0
        layout = when (currentPack) {
            0 -> R.layout.custom_dialog_pack1
            1 -> R.layout.custom_dialog_pack2
            2 -> R.layout.custom_dialog_pack3
            else -> R.layout.custom_dialog_pack4
        }
        val adview = inflater.inflate(layout, null)
        abuilder.setView(adview)

        val dialog = abuilder.create()
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        try {
            dialog.show()

            val handler = Handler()
            val runnable = Runnable {
                if (dialog.isShowing) {
                    dialog.dismiss()

                    if (currentPack == 0) cargarPreguntas()
                    if (currentPack == 3) {
                        var intent: Intent? = null
                        intent = if (derMode) {
                            Intent(this@QuestionActivity, LevelsDerivatesActivity::class.java)
                        } else {
                            Intent(this@QuestionActivity, LevelsLimitsActivity::class.java)
                        }
                        startActivity(intent)
                        finish()
                    }
                }
            }

            handler.postDelayed(runnable, 3000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun cargarPreguntas() {
        listaDePreguntas = ArrayList()

        for (i in 0..2) {
            var archivojson = ""

            when (i) {
                0 -> if (derMode) {
                    if (level == 1) archivojson = "derivadas_de_polinomios_facil.json"
                    else if (level == 2) archivojson = "derivadas_de_inversas_facil.json"
                    else if (level == 3) archivojson = "derivadas_de_radicales_facil.json"
                    else if (level == 4) archivojson = "derivadas_de_exponenciales_facil.json"
                    else if (level == 5) archivojson = "derivadas_de_trigonometricas_facil.json"
                    else if (level == 6) archivojson = "derivadas_mult_y_div_facil.json"
                    else if (level == 7) archivojson = "derivadas_regla_cadena_facil.json"
                } else {
                    if (level == 1) archivojson = "limites_sustitucion_directa_facil.json"
                    else if (level == 2) archivojson = "limites_factorizables_facil.json"
                    else if (level == 3) archivojson = "limites_infinitos_facil.json"
                    else if (level == 4) archivojson = "limites_al_infinito_facil.json"
                }

                1 -> if (derMode) {
                    if (level == 1) archivojson = "derivadas_de_polinomios_media.json"
                    else if (level == 2) archivojson = "derivadas_de_inversas_media.json"
                    else if (level == 3) archivojson = "derivadas_de_radicales_media.json"
                    else if (level == 4) archivojson = "derivadas_de_exponenciales_media.json"
                    else if (level == 5) archivojson = "derivadas_de_trigonometricas_media.json"
                    else if (level == 6) archivojson = "derivadas_mult_y_div_media.json"
                    else if (level == 7) archivojson = "derivadas_regla_cadena_media.json"
                } else {
                    if (level == 1) archivojson = "limites_sustitucion_directa_media.json"
                    else if (level == 2) archivojson = "limites_factorizables_media.json"
                    else if (level == 3) archivojson = "limites_infinitos_media.json"
                    else if (level == 4) archivojson = "limites_al_infinito_media.json"
                }

                2 -> if (derMode) {
                    if (level == 1) archivojson = "derivadas_de_polinomios_dificil.json"
                    else if (level == 2) archivojson = "derivadas_de_inversas_dificil.json"
                    else if (level == 3) archivojson = "derivadas_de_radicales_dificil.json"
                    else if (level == 4) archivojson = "derivadas_de_exponenciales_dificil.json"
                    else if (level == 5) archivojson = "derivadas_de_trigonometricas_dificil.json"
                    else if (level == 6) archivojson = "derivadas_mult_y_div_dificil.json"
                    else if (level == 7) archivojson = "derivadas_regla_cadena_dificil.json"
                } else {
                    if (level == 1) archivojson = "limites_sustitucion_directa_dificil.json"
                    else if (level == 2) archivojson = "limites_factorizables_dificil.json"
                    else if (level == 3) archivojson = "limites_infinitos_dificil.json"
                    else if (level == 4) archivojson = "limites_al_infinito_dificil.json"
                }

                else -> {}
            }
            cargarPack(archivojson)
        }

        mostrarPreguntaEnPantalla()
    }

    private fun cargarPack(filename: String) {
        val jsonquiz = loadJsonFromAsset(filename)
        val listaPack: MutableList<QuestionsItem?> = ArrayList()

        try {
            val jsonObject = JSONObject(jsonquiz)
            val arrayname = filename.substring(0, filename.length - 5)
            val questions = jsonObject.getJSONArray(arrayname)

            for (i in 0 until questions.length()) {
                val question = questions.getJSONObject(i)

                val labels = arrayOf(
                    question.getString("question"),
                    question.getString("answer1"),
                    question.getString("answer2"),
                    question.getString("answer3"),
                    question.getString("answer4")
                )
                val correcto = question.getString("correct").toInt()

                listaPack.add(QuestionsItem(labels, correcto))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        Collections.shuffle(listaPack)

        for (i in 0 until preguntasxPack) {
            listaDePreguntas.add(listaPack[i])
        }
    }

    private fun loadJsonFromAsset(s: String): String {
        var json = ""
        try {
            val inputStream = assets.open(s)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }

    private fun mostrarPreguntaEnPantalla() {
        val latexLabels = arrayOf(latex1, latex2, latex3, latex4, latex5)

        for (i in 0..4) {
            val drw_answr = Drawable()
            drw_answr.setFormula(listaDePreguntas[currentQuestion]!!.labels[i], -0x1)
            latexLabels[i].setLatexDrawable(drw_answr.drawable)
        }

        var numAleatorio = 0
        numAleatorio = floor(Math.random() * 10).toInt()

        if (numAleatorio == 7 && numVidas < 5) {
            vidaextra.visibility = View.VISIBLE
            hayvidaextra = true
        } else {
            vidaextra.visibility = View.INVISIBLE
            hayvidaextra = false
        }
    }


    private fun playHeartAnimation() {
        vidaextra.animate()
            .translationX(vContainer.x - vidaextra.x)
            .translationY(vContainer.y - vidaextra.y)
            .alpha(0f)
            .setDuration(500)
            .setStartDelay(100)
            .setInterpolator(DecelerateInterpolator())
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {
                }

                override fun onAnimationEnd(animator: Animator) {
                    vidaextra.visibility = View.INVISIBLE
                    vidaextra.animate()
                        .translationX(vidaextra.x - vContainer.x)
                        .translationY(vidaextra.y - vContainer.y)
                        .alpha(1f)
                        .setDuration(500)
                        .setStartDelay(100)
                        .setInterpolator(DecelerateInterpolator())
                        .setListener(object : Animator.AnimatorListener {
                            override fun onAnimationStart(animator: Animator) {
                            }

                            override fun onAnimationEnd(animator: Animator) {
                            }

                            override fun onAnimationCancel(animator: Animator) {
                            }

                            override fun onAnimationRepeat(animator: Animator) {
                            }
                        })
                    numVidas++
                    actualizarVidas()
                }

                override fun onAnimationCancel(animator: Animator) {
                }

                override fun onAnimationRepeat(animator: Animator) {
                }
            })
    }

    private fun actualizarVidas() {
        for (i in 0..4) {
            if (i + 1 <= numVidas) vContainer.getChildAt(i).visibility = View.VISIBLE
            else vContainer.getChildAt(i).visibility = View.GONE
        }
        if (numVidas == 0) {
            actualizarPuntuacion()

            var intent: Intent? = null

            intent = if (derMode) Intent(
                this@QuestionActivity,
                LevelsDerivatesActivity::class.java
            )
            else Intent(
                this@QuestionActivity,
                LevelsLimitsActivity::class.java
            )
            startActivity(intent)
            finish()

        }
    }

    private fun actualizarPB() {
        val incremento = currentQuestion.toFloat() / totalPreguntas.toFloat() * 100
        pb.progress = incremento.toInt()
    }
}
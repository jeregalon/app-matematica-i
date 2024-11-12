package com.example.prueba4._frontend.components

//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextFieldDefaults
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.prueba4.R
import com.example.prueba4.classes.Drawable
import com.example.prueba4.ui.theme.BgColor
import com.example.prueba4.ui.theme.Primary
import com.example.prueba4.ui.theme.Secondary
import com.example.prueba4.ui.theme.WhiteColor
import ru.noties.jlatexmath.JLatexMathView


@Composable
fun NormalTextComponent(
    value: String = "Normal Text Component",
    color: Color = WhiteColor,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 24.sp,
    paddingTop: Dp = 10.dp,
    paddingBottom: Dp = 10.dp,
    paddingStart: Dp = 10.dp,
    paddingEnd: Dp = 10.dp,
) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = paddingTop,
                bottom = paddingBottom,
                start = paddingStart,
                end = paddingEnd
            ),
            style = TextStyle(
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontStyle = FontStyle.Normal
            ),
        color = color,
        textAlign = TextAlign.Center

    )
}

@Composable
fun SquareTextComponent(
    value: String = "Normal Text Component",
    color: Color = WhiteColor,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 24.sp,
    paddingTop: Dp = 10.dp,
    paddingBottom: Dp = 10.dp,
    paddingStart: Dp = 10.dp,
    paddingEnd: Dp = 10.dp,
) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = paddingTop,
                bottom = paddingBottom,
                start = paddingStart,
                end = paddingEnd
            ),
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = FontStyle.Normal
        ),
        color = color,
        textAlign = TextAlign.Right

    )
}

@Composable
fun MyTextFieldComponent(textValue: String, labelValue: String, painterResource: Painter){

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            backgroundColor = BgColor
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        }
    )
}

@Composable
fun WhiteBorderTransparentButtonComponent(
    onClick: () -> Unit,
    textValue: String = "White Border Button",
    fontColor: Color = WhiteColor,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 24.sp,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(50.dp),
    borderStroke: Dp = 5.dp,
    paddingTop: Dp = 10.dp,
    paddingBottom: Dp = 10.dp,
    paddingStart: Dp = 10.dp,
    paddingEnd: Dp = 10.dp,

    ) {
    Button(onClick = onClick,
        modifier = Modifier
            .heightIn(48.dp)
            .background(
                color = Color.Transparent
            )
            .border(
                BorderStroke(
                    borderStroke,
                    WhiteColor
                ),
                shape = roundedCornerShape
            )
            .padding(
                top = paddingTop,
                bottom = paddingBottom,
                start = paddingStart,
                end = paddingEnd
            )
            ,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(text = textValue,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = fontColor
        )
    }
}

@Composable
fun WhiteBorderTransparentButtonMaxWidthComponent(
    onClick: () -> Unit,
    textValue: String = "White Border Button",
    fontColor: Color = WhiteColor,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 24.sp,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(50.dp),
    borderStroke: Dp = 5.dp,
    paddingTop: Dp = 10.dp,
    paddingBottom: Dp = 10.dp,
    paddingStart: Dp = 10.dp,
    paddingEnd: Dp = 10.dp,

    ) {
    Button(onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent
            )
            .border(
                BorderStroke(
                    borderStroke,
                    WhiteColor
                ),
                shape = roundedCornerShape
            )
            .padding(
                top = paddingTop,
                bottom = paddingBottom,
                start = paddingStart,
                end = paddingEnd
            )
        ,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(text = textValue,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = fontColor
        )
    }
}

@Composable
fun GradientButtonComponent(
    onClick: () -> Unit,
    textValue: String = "Gradient Button",
    fontColor: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 24.sp,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(50.dp),
    paddingTop: Dp = 10.dp,
    paddingBottom: Dp = 10.dp,
    paddingStart: Dp = 10.dp,
    paddingEnd: Dp = 10.dp,

    ) {
    Button(onClick = onClick,
        modifier = Modifier
            .heightIn(48.dp)
            .padding(
                top = paddingTop,
                bottom = paddingBottom,
                start = paddingStart,
                end = paddingEnd
            )
        ,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = roundedCornerShape
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = textValue,
                fontSize = fontSize,
                fontWeight = fontWeight,
                color = fontColor)
        }
    }
}

@Composable
fun SimpleDialog(
    titleText : String,
    show : Boolean,
    onDissmiss : () -> Unit,
    onOkClick : () -> Unit,
    painterResource : Painter
) {
    if (show) {
        Dialog(
            onDismissRequest = { onDissmiss() }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        WhiteColor,
                        RoundedCornerShape(16.dp)
                    )
            ) {
                Image(
                    painter = painterResource,
                    contentDescription = "imagen del dialog",
                    modifier = Modifier.size(150.dp)
                        .padding(top = 10.dp, bottom = 10.dp)
                )

                Text(
                    text = titleText,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                TextButton(onClick = {
                    onOkClick()
                }) {
                    Text(
                        text = "OK",
                        modifier = Modifier.fillMaxWidth()
                            .padding(end = 15.dp, bottom = 15.dp),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Right
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingComponent() {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Cargando...",
            fontSize = 24.sp
        )
    }
}

@Composable
@Preview
fun DialogPreview() {
    SimpleDialog(titleText = "¡Usuario registrado exitosamente!",
        show = true,
        onDissmiss = { /*TODO*/ },
        onOkClick = { /*TODO*/ },
        painterResource = painterResource(id = R.drawable.log_success)
    )
}

@Composable
fun TableRow(
    vararg cells: @Composable () -> Unit
) {
    Row(modifier = Modifier.height(50.dp)) {
        cells.forEach { cell ->
            Spacer(modifier = Modifier.width(10.dp))
            cell()
        }
    }
}

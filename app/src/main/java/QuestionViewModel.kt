import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuestionViewModel : ViewModel() {

    private val _puntuation = mutableStateOf(0)
    val puntuation : State<Int> = _puntuation

    fun increaseBy5(){
        _puntuation.value = _puntuation.value + 5
    }

    fun increaseBy10(){
        _puntuation.value = _puntuation.value + 10
    }

    fun increaseBy15(){
        _puntuation.value = _puntuation.value + 15
    }
}
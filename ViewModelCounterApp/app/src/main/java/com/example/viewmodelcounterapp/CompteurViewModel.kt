import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CompteurViewModel : ViewModel() {
    private val _compteur = MutableStateFlow(0) // Mutable state (private)
    val compteur: StateFlow<Int> = _compteur // Public state

    fun incrementer() {
        _compteur.value++ // Increase the counter
    }
}

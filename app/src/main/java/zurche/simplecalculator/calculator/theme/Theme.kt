package zurche.simplecalculator.calculator.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun SimpleCalcTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        typography = Typography, content = content
    )
}
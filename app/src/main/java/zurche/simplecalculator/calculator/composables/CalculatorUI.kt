package zurche.simplecalculator.calculator.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import zurche.simplecalculator.calculator.theme.DarkOperatorTeal
import zurche.simplecalculator.calculator.theme.EqualsTeal
import zurche.simplecalculator.calculator.theme.InputGray
import zurche.simplecalculator.calculator.theme.NumberTeal

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun CalculatorUI() {
    Column {
        Row(
            modifier = Modifier
                .background(InputGray)
                .fillMaxHeight(0.33f)
                .fillMaxWidth()
        ) {

        }

        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {

        }
    }

}

@Composable
@Preview
fun InputAreaUI() {
    TODO()
}

enum class PadButton {
    AC, PlusMinus, Percent, Divide, Seven, Eight, Nine, Multiply, Four, Five, Six, Minus, One, Two, Three, Plus, Zero, Decimal, Equals
}

@Composable
@Preview
fun PadButtonUI(button: PadButton = PadButton.AC) {
    val backgroundColor = when (button) {
        PadButton.AC, PadButton.PlusMinus, PadButton.Percent, PadButton.Equals -> {
            EqualsTeal
        }

        PadButton.Divide, PadButton.Multiply, PadButton.Plus, PadButton.Minus -> {
            DarkOperatorTeal
        }

        PadButton.Seven, PadButton.Eight, PadButton.Nine, PadButton.Four, PadButton.Five, PadButton.Six, PadButton.One, PadButton.Two, PadButton.Three, PadButton.Zero, PadButton.Decimal -> {
            NumberTeal
        }
    }
}
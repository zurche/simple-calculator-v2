package zurche.simplecalculator.calculator.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import zurche.simplecalculator.app.R
import zurche.simplecalculator.calculator.theme.DarkOperatorTeal
import zurche.simplecalculator.calculator.theme.EqualsTeal
import zurche.simplecalculator.calculator.theme.InputGray
import zurche.simplecalculator.calculator.theme.NumberTeal

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun CalculatorUI() {
    Column(modifier = Modifier.fillMaxHeight()) {
        InputAreaUI()

        NumPadUI()
    }

}

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
private fun InputAreaUI() {
    Box(
        modifier = Modifier
            .background(InputGray)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            Text(
                text = "20 x 10 + 50",
                modifier = Modifier.background(InputGray),
                color = Color.White,
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = "250",
                modifier = Modifier.background(InputGray),
                color = Color.White,
                style = MaterialTheme.typography.displayLarge
            )
        }
    }
}

@Composable
@Preview
private fun NumPadUI() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            PadButtonUI(PadButton.AC)
            PadButtonUI(PadButton.PlusMinus)
            PadButtonUI(PadButton.Percent)
            PadButtonUI(PadButton.Divide)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            PadButtonUI(PadButton.Seven)
            PadButtonUI(PadButton.Eight)
            PadButtonUI(PadButton.Nine)
            PadButtonUI(PadButton.Multiply)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            PadButtonUI(PadButton.Four)
            PadButtonUI(PadButton.Five)
            PadButtonUI(PadButton.Six)
            PadButtonUI(PadButton.Plus)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            PadButtonUI(PadButton.One)
            PadButtonUI(PadButton.Two)
            PadButtonUI(PadButton.Three)
            PadButtonUI(PadButton.Minus)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            PadButtonUI(PadButton.Zero)
            PadButtonUI(PadButton.Decimal)
            PadButtonUI(PadButton.Equals)
        }
    }
}

enum class PadButton(val textResource: Int, val backgroundColor: Color) {
    AC(R.string.ac, EqualsTeal),
    PlusMinus(R.string.value_update, EqualsTeal),
    Percent(R.string.percentage, EqualsTeal),
    Divide(R.string.divide, DarkOperatorTeal),
    Seven(R.string.seven, NumberTeal),
    Eight(R.string.eight, NumberTeal),
    Nine(R.string.nine, NumberTeal),
    Multiply(R.string.multiply, DarkOperatorTeal),
    Four(R.string.four, NumberTeal),
    Five(R.string.five, NumberTeal),
    Six(R.string.six, NumberTeal),
    Minus(R.string.minus, DarkOperatorTeal),
    One(R.string.one, NumberTeal),
    Two(R.string.two, NumberTeal),
    Three(R.string.three, NumberTeal),
    Plus(R.string.plus, DarkOperatorTeal),
    Zero(R.string.zero, NumberTeal),
    Decimal(R.string.comma, NumberTeal),
    Equals(R.string.equals, EqualsTeal)
}

@Composable
@Preview(heightDp = 80, widthDp = 80)
fun PadButtonUI(button: PadButton = PadButton.AC) {
    Box(
        modifier = Modifier
            .background(button.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = button.textResource),
            color = Color.White,
            style = MaterialTheme.typography.displayMedium
        )
    }
}
package zurche.simplecalculator.calculator.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import zurche.simplecalculator.calculator.theme.SimpleCalcTheme

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun CalculatorUI() {
    SimpleCalcTheme {
        Column(modifier = Modifier.fillMaxHeight()) {
            Box(modifier = Modifier.weight(0.33f)) {
                InputAreaUI()
            }
            Box(modifier = Modifier.weight(0.66f)) {
                NumPadUI()
            }
        }
    }
}

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
private fun InputAreaUI() {
    Column(
        modifier = Modifier
            .background(InputGray)
            .fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "20 x 10 + 50",
            modifier = Modifier.background(InputGray),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "250",
            modifier = Modifier.background(InputGray),
            color = Color.White,
            style = MaterialTheme.typography.displayLarge
        )
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
                .fillMaxWidth()
                .weight(0.2f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.AC)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.PlusMinus)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Percent)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Divide)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Seven)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Eight)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Nine)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Multiply)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Four)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Five)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Six)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Plus)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.One)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Two)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Three)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Minus)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Zero)
            }

            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(PadButton.Decimal)
            }

            Column(modifier = Modifier.weight(0.5f)) {
                PadButtonUI(PadButton.Equals)
            }
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
            .background(button.backgroundColor)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = button.textResource),
            color = Color.White,
            style = MaterialTheme.typography.displayMedium
        )
    }
}
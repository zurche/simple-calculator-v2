package zurche.simplecalculator.calculator.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "20 x 10 + 50",
            modifier = Modifier.background(InputGray),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1
        )

        Text(
            text = "250",
            modifier = Modifier.background(InputGray),
            color = Color.White,
            style = MaterialTheme.typography.displayLarge,
            maxLines = 1
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
        val rowModifier = Modifier
            .fillMaxWidth()
            .weight(0.2f)

        FourButtonPadRow(
            rowModifier,
            listOf(PadButton.AC, PadButton.PlusMinus, PadButton.Percent, PadButton.Divide)
        )

        FourButtonPadRow(
            rowModifier,
            listOf(PadButton.Seven, PadButton.Eight, PadButton.Nine, PadButton.Multiply)
        )

        FourButtonPadRow(
            rowModifier,
            listOf(PadButton.Four, PadButton.Five, PadButton.Six, PadButton.Plus)
        )

        FourButtonPadRow(
            rowModifier,
            listOf(PadButton.One, PadButton.Two, PadButton.Three, PadButton.Minus)
        )

        Row(
            modifier = rowModifier,
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

@Composable
private fun FourButtonPadRow(
    modifier: Modifier = Modifier,
    rowElements: List<PadButton> = listOf(
        PadButton.AC,
        PadButton.PlusMinus,
        PadButton.Percent,
        PadButton.Divide
    )
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (rowElement in rowElements) {
            Column(modifier = Modifier.weight(0.25f)) {
                PadButtonUI(rowElement)
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
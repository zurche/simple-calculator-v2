package zurche.simplecalculator.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import zurche.simplecalculator.app.R
import zurche.simplecalculator.calculator.composables.CalculatorScreen
import zurche.simplecalculator.calculator.ui.theme.SimpleCalcTheme

class MainActivity : ComponentActivity() {

    private var viewModel: CalculatorViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModels<CalculatorViewModel>().value

        viewModel.apply {
            this?.getInvalidExpressionMessageEvent()?.observe(this@MainActivity) { shouldShow ->
                if (shouldShow != null && shouldShow) {
                    this@MainActivity.showInvalidExpressionMessage()
                }
            }
        }

        setContent {
            SimpleCalcTheme {
                CalculatorScreen(viewModel)
            }
        }
    }

    private fun showInvalidExpressionMessage(): Unit =
        Toast.makeText(this, getString(R.string.invalid_expression_message), Toast.LENGTH_SHORT)
            .show()
}
package zurche.simplecalculator.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import zurche.simplecalculator.app.R
import zurche.simplecalculator.app.databinding.MainActBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: MainActBinding
    private var mCalculatorViewModel: CalculatorViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mCalculatorViewModel = viewModels<CalculatorViewModel>().value

        mCalculatorViewModel.apply {
            this?.getInvalidExpressionMessageEvent()?.observe(this@MainActivity) { shouldShow ->
                if (shouldShow != null && shouldShow) {
                    this@MainActivity.showInvalidExpressionMessage()
                }
            }
        }

        binding.let {
            it.viewModel = mCalculatorViewModel
            it.lifecycleOwner = this
        }
    }

    private fun showInvalidExpressionMessage(): Unit =
        Toast.makeText(this, getString(R.string.invalid_expression_message), Toast.LENGTH_SHORT)
            .show()
}

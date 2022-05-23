package zurche.simplecalculator.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import zurche.simplecalculator.app.R
import zurche.simplecalculator.app.databinding.MainActBinding

class MainActivity : ComponentActivity() {

    private var mCalculatorViewModel: CalculatorViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)

        mCalculatorViewModel = viewModels<CalculatorViewModel>().value

        mCalculatorViewModel.apply {
            this!!.getInvalidExpressionMessageEvent().observe(
                this@MainActivity,
                Observer { shouldShow ->
                    if (shouldShow != null && shouldShow) {
                        this@MainActivity.showInvalidExpressionMessage()
                    }
                })
        }

        val binding: MainActBinding? = DataBindingUtil.setContentView(this, R.layout.main_act)
        binding?.let {
            it.viewModel = mCalculatorViewModel
            it.lifecycleOwner = this
        }
    }

    private fun showInvalidExpressionMessage() {
        Toast.makeText(this, getString(R.string.invalid_expression_message), Toast.LENGTH_SHORT)
            .show()
    }
}

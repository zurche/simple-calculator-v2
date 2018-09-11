package zurche.simplecalculator.calculator

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import zurche.simplecalculator.app.R
import zurche.simplecalculator.app.databinding.MainActBinding

class MainActivity : AppCompatActivity() {

    private var mCalculatorViewModel: CalculatorViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)

        mCalculatorViewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)

        mCalculatorViewModel.apply {
            this!!.getInvalidExpressionMessageEvent().observe(
                    this@MainActivity,
                    Observer { shouldShow ->
                        if (shouldShow != null && shouldShow) {
                            this@MainActivity.showInvalidExpressionMessage()
                        }
                    })
        }

        val binding : MainActBinding? = DataBindingUtil.setContentView(this, R.layout.main_act)
        binding?.let{
            it.viewModel = mCalculatorViewModel
            it.setLifecycleOwner(this)
        }
    }

    private fun showInvalidExpressionMessage() {
        Toast.makeText(this, getString(R.string.invalid_expression_message), Toast.LENGTH_SHORT).show()
    }
}

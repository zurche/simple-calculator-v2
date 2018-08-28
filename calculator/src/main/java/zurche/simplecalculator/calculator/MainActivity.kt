package zurche.simplecalculator.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CalculatorContract.View {

    private val presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zero.setOnClickListener { presenter.onOperatorAdd(getString(R.string.zero)) }
        one.setOnClickListener { presenter.onOperatorAdd(getString(R.string.one)) }
        two.setOnClickListener { presenter.onOperatorAdd(getString(R.string.two)) }
        three.setOnClickListener { presenter.onOperatorAdd(getString(R.string.three)) }
        four.setOnClickListener { presenter.onOperatorAdd(getString(R.string.four)) }
        five.setOnClickListener { presenter.onOperatorAdd(getString(R.string.five)) }
        six.setOnClickListener { presenter.onOperatorAdd(getString(R.string.six)) }
        seven.setOnClickListener { presenter.onOperatorAdd(getString(R.string.seven)) }
        eight.setOnClickListener { presenter.onOperatorAdd(getString(R.string.eight)) }
        nine.setOnClickListener { presenter.onOperatorAdd(getString(R.string.nine)) }

        ac.setOnClickListener { presenter.onClearExpression() }
        plus_minus_switch.setOnClickListener { presenter.onExpressionSignChange() }
        percentage.setOnClickListener { presenter.onOperatorAdd(getString(R.string.percentage)) }
        comma.setOnClickListener { presenter.onOperatorAdd(getString(R.string.comma_expression)) }
        equals.setOnClickListener { presenter.onCalculateResult() }

        divide.setOnClickListener { presenter.onOperatorAdd(getString(R.string.divide_operator)) }
        multiply.setOnClickListener { presenter.onOperatorAdd(getString(R.string.multiply_expression)) }
        plus.setOnClickListener { presenter.onOperatorAdd(getString(R.string.plus)) }
        minus.setOnClickListener { presenter.onOperatorAdd(getString(R.string.minus)) }
    }

    override fun showResult(result: String) {
        operation_result.text = result
    }

    override fun updateCurrentExpression(updatedExpression: String) {
        operation.text = updatedExpression
    }

    override fun showInvalidExpressionMessage() {
        Toast.makeText(this, getString(R.string.invalid_expression_message), Toast.LENGTH_LONG).show()
    }
}

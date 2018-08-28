package zurche.simplecalculator.calculator

import com.udojava.evalex.Expression
import java.math.BigDecimal
import java.util.*

class Presenter constructor(private val view: CalculatorContract.View) : CalculatorContract.Presenter {

    private var mCurrentStringExpression = ""
    private var isNumberPositive = true

    private val STRING_COMMA = "."
    private val PERCENTAGE = "%"
    private val SCIENTIFIC_NOTATION_CHAR = "E"
    private val INFINITY = "Infinity"
    private val validOperators = Arrays.asList('+', '-', '/', '*')

    override fun onOperatorAdd(addedValue: String) {
        if (mCurrentStringExpression.isEmpty() && addedValue == STRING_COMMA) {
            view.showInvalidExpressionMessage()
        } else {
            var isCommaAddedToExpression = false

            if ((isValueAnOperator(addedValue) || addedValue == PERCENTAGE) && mCurrentStringExpression.length > 0) {
                val lastCharacterOfExpression = mCurrentStringExpression.get(mCurrentStringExpression.length - 1)

                if (isValueAnOperator(lastCharacterOfExpression.toString())) {
                    clearLastCharOfExpression()
                }
            } else if (addedValue == STRING_COMMA) {
                val expressionArray = mCurrentStringExpression.toCharArray()
                for (c in expressionArray) {
                    if (c == STRING_COMMA.toCharArray()[0]) {
                        isCommaAddedToExpression = true
                    }
                    if (validOperators.contains(c)) {
                        isCommaAddedToExpression = false
                    }
                }

                // If last character of expression is either a number or an operator, do not add the comma to the expression.
                val lastCharacterOfExpression = mCurrentStringExpression.get(mCurrentStringExpression.length - 1)
                if (validOperators.contains(lastCharacterOfExpression)) {
                    isCommaAddedToExpression = true
                }
            }

            if (!isCommaAddedToExpression) {
                mCurrentStringExpression += addedValue
                view.updateCurrentExpression(mCurrentStringExpression)
            }
        }
    }

    override fun onClearExpression() {
        mCurrentStringExpression = ""
        view.updateCurrentExpression(mCurrentStringExpression)
        view.showResult("")
    }

    override fun onCalculateResult() {
        if (mCurrentStringExpression.isEmpty() || mCurrentStringExpression.contains(INFINITY)) {
            view.showInvalidExpressionMessage()
        } else {
            clearLastValueIfItIsAnOperator()

            mCurrentStringExpression = mCurrentStringExpression.replace(PERCENTAGE.toRegex(), "/100")

            val expression = Expression(mCurrentStringExpression)

            val bigDecimalResult = expression.eval()

            val doubleResult = bigDecimalResult.toDouble()

            val stringResult: String

            if (isValueInteger(doubleResult) && !isScientificNotation(java.lang.Double.toString(doubleResult))) {
                val roundedValue = Math.round(doubleResult).toInt()
                stringResult = roundedValue.toString()
            } else {
                stringResult = java.lang.Double.toString(doubleResult)
            }

            view.showResult(stringResult)
            mCurrentStringExpression = stringResult
        }
    }

    override fun onExpressionSignChange() {
        mCurrentStringExpression = if (isNumberPositive)
            "-$mCurrentStringExpression"
        else
            mCurrentStringExpression.substring(1, mCurrentStringExpression.length)
        isNumberPositive = !isNumberPositive
        view.updateCurrentExpression(mCurrentStringExpression)
    }

    private fun isValueAnOperator(value: String): Boolean {
        return validOperators.contains(value.toCharArray()[0])
    }

    private fun clearLastCharOfExpression() {
        mCurrentStringExpression = mCurrentStringExpression.substring(0, mCurrentStringExpression.length - 1)
    }

    private fun clearLastValueIfItIsAnOperator() {
        if (isValueAnOperator(getLastCharOfExpression().toString())) {
            clearLastCharOfExpression()
            view.updateCurrentExpression(mCurrentStringExpression)
        }
    }

    private fun getLastCharOfExpression(): Char {
        val currentExpressionLastValuePosition = mCurrentStringExpression.length - 1
        return mCurrentStringExpression[currentExpressionLastValuePosition]
    }

    private fun isValueInteger(number: Double): Boolean {
        val roundedValue = Math.round(number).toInt()
        return number % roundedValue == 0.0
    }

    private fun isScientificNotation(numberString: String): Boolean {
        try {
            BigDecimal(numberString)
        } catch (e: NumberFormatException) {
            return false
        }

        return numberString.toUpperCase().contains(SCIENTIFIC_NOTATION_CHAR)
    }
}
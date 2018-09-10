package zurche.simplecalculator.calculator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.udojava.evalex.Expression
import java.math.BigDecimal
import java.util.*

class CalculatorViewModel : ViewModel() {

    private var mCurrentExpression: MutableLiveData<String>? = null
    private var mResult: MutableLiveData<String>? = null

    private var isNumberPositive = true

    private val STRING_COMMA = "."
    private val PERCENTAGE = "%"
    private val SCIENTIFIC_NOTATION_CHAR = "E"
    private val INFINITY = "Infinity"
    private val validOperators = Arrays.asList('+', '-', '/', '*')

    private val mInvalidExpressionMessageEvent = SingleLiveEvent<Boolean>()

    fun getInvalidExpressionMessageEvent(): SingleLiveEvent<Boolean> {
        return mInvalidExpressionMessageEvent
    }

    fun getCurrentExpression(): MutableLiveData<String> {
        if (mCurrentExpression == null) {
            mCurrentExpression = MutableLiveData()
        }
        return mCurrentExpression as MutableLiveData<String>
    }

    fun getResult(): MutableLiveData<String> {
        if (mResult == null) {
            mResult = MutableLiveData()
        }
        return mResult as MutableLiveData<String>
    }

    fun onOperatorAdd(addedValue: String) {
        if (currentExpressionIsInvalid() && addedValue == STRING_COMMA || addedValue == PERCENTAGE) {
            showInvalidExpressionMessage()
        } else {
            var isCommaAddedToExpression = false

            if ((isValueAnOperator(addedValue) || addedValue == PERCENTAGE) && mCurrentExpression!!.value!!.isNotEmpty()) {
                val lastCharacterOfExpression = mCurrentExpression!!.value!!.get(mCurrentExpression!!.value!!.length - 1)

                if (isValueAnOperator(lastCharacterOfExpression.toString())) {
                    clearLastCharOfExpression()
                }
            } else if (addedValue == STRING_COMMA) {
                val expressionArray = mCurrentExpression!!.value!!.toCharArray()
                for (c in expressionArray) {
                    if (c == STRING_COMMA.toCharArray()[0]) {
                        isCommaAddedToExpression = true
                    }
                    if (validOperators.contains(c)) {
                        isCommaAddedToExpression = false
                    }
                }

                // If last character of expression is either a number or an operator, do not add the comma to the expression.
                val lastCharacterOfExpression = mCurrentExpression!!.value!!.get(mCurrentExpression!!.value!!.length - 1)
                if (validOperators.contains(lastCharacterOfExpression)) {
                    isCommaAddedToExpression = true
                }
            }

            if (!isCommaAddedToExpression) {
                if (mCurrentExpression!!.value == null) {
                    mCurrentExpression!!.value = addedValue
                } else {
                    mCurrentExpression!!.value += addedValue
                }
            }
        }
    }

    fun onClearExpression() {
        mCurrentExpression!!.value = ""
        mResult!!.value = ""
    }

    fun onCalculateResult() {
        if (mCurrentExpression!!.value == null || mCurrentExpression!!.value!!.contains(INFINITY)) {
            showInvalidExpressionMessage()
        } else {
            clearLastValueIfItIsAnOperator()

            mCurrentExpression!!.value = mCurrentExpression!!.value!!.replace(PERCENTAGE.toRegex(), "/100")

            val expression = Expression(mCurrentExpression!!.value!!)

            val bigDecimalResult = expression.eval()

            val doubleResult = bigDecimalResult.toDouble()

            val stringResult: String

            if (isValueInteger(doubleResult) && !isScientificNotation(java.lang.Double.toString(doubleResult))) {
                val roundedValue = Math.round(doubleResult).toInt()
                stringResult = roundedValue.toString()
            } else {
                stringResult = java.lang.Double.toString(doubleResult)
            }

            mCurrentExpression!!.value = stringResult
            mResult!!.value = stringResult
        }
    }

    fun onExpressionSignChange() {
        if (currentExpressionIsInvalid()) {
            showInvalidExpressionMessage()
        } else {
            mCurrentExpression!!.value = if (isNumberPositive)
                "-${mCurrentExpression!!.value}"
            else
                mCurrentExpression!!.value!!.substring(1, mCurrentExpression!!.value!!.length)

            isNumberPositive = !isNumberPositive
        }
    }

    private fun currentExpressionIsInvalid() =
            mCurrentExpression!!.value == null || mCurrentExpression!!.value!!.isEmpty()

    private fun isValueAnOperator(value: String): Boolean {
        return validOperators.contains(value.toCharArray()[0])
    }

    private fun clearLastCharOfExpression() {
        mCurrentExpression!!.value = mCurrentExpression!!.value!!.substring(0, mCurrentExpression!!.value!!.length - 1)
    }

    private fun clearLastValueIfItIsAnOperator() {
        if (isValueAnOperator(getLastCharOfExpression().toString())) {
            clearLastCharOfExpression()
        }
    }

    private fun getLastCharOfExpression(): Char {
        val currentExpressionLastValuePosition = mCurrentExpression!!.value!!.length - 1
        return mCurrentExpression!!.value!![currentExpressionLastValuePosition]
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

    private fun showInvalidExpressionMessage() {
        mInvalidExpressionMessageEvent.value = true
    }
}
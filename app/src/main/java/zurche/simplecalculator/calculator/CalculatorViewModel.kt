package zurche.simplecalculator.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udojava.evalex.Expression
import java.math.BigDecimal
import java.util.*

class CalculatorViewModel : ViewModel() {

    private val mCurrentExpression = MutableLiveData<String>()
    private val mResult = MutableLiveData<String>()

    private var isNumberPositive = true

    private val STRING_COMMA = "."
    private val PERCENTAGE = "%"
    private val SCIENTIFIC_NOTATION_CHAR = "E"
    private val INFINITY = "Infinity"
    private val validOperators = Arrays.asList("+", "-", "/", "*")

    private val mInvalidExpressionMessageEvent = SingleLiveEvent<Boolean>()

    fun getInvalidExpressionMessageEvent(): SingleLiveEvent<Boolean> {
        return mInvalidExpressionMessageEvent
    }

    fun getCurrentExpression(): MutableLiveData<String> {
        return mCurrentExpression
    }

    fun getResult(): MutableLiveData<String> {
        return mResult
    }

    fun onOperatorAdd(addedValue: String) {
        if (currentExpressionIsInvalid() && (validOperators.contains(addedValue) || STRING_COMMA == addedValue || PERCENTAGE == addedValue)) {
            showInvalidExpressionMessage()
        } else {
            var isCommaAddedToExpression = false

            if ((isValueAnOperator(addedValue) || addedValue == PERCENTAGE) && mCurrentExpression.value!!.isNotEmpty()) {
                val lastCharacterOfExpression =
                    mCurrentExpression.value!!.get(mCurrentExpression.value!!.length - 1)

                if (isValueAnOperator(lastCharacterOfExpression.toString())) {
                    clearLastCharOfExpression()
                }
            } else if (addedValue == STRING_COMMA) {
                val expressionArray = mCurrentExpression.value!!.toCharArray()
                for (c in expressionArray) {
                    if (c == STRING_COMMA.toCharArray()[0]) {
                        isCommaAddedToExpression = true
                    }
                    if (validOperators.contains(c.toString())) {
                        isCommaAddedToExpression = false
                    }
                }

                // If last character of expression is either a number or an operator, do not add the comma to the expression.
                val lastCharacterOfExpression =
                    mCurrentExpression.value!!.get(mCurrentExpression.value!!.length - 1)
                if (validOperators.contains(lastCharacterOfExpression.toString())) {
                    isCommaAddedToExpression = true
                }
            }

            if (!isCommaAddedToExpression) {
                if (mCurrentExpression.value == null) {
                    mCurrentExpression.postValue(addedValue)
                } else {
                    mCurrentExpression.postValue(mCurrentExpression.value + addedValue)
                }
            }
        }
    }

    fun onClearExpression() {
        mCurrentExpression.postValue("")
        mResult.postValue("")
    }

    fun onCalculateResult() {
        if (mCurrentExpression.value == null || mCurrentExpression.value!!.contains(INFINITY) || mCurrentExpression.value!!.isEmpty()) {
            showInvalidExpressionMessage()
        } else {
            clearLastValueIfItIsAnOperator()

            mCurrentExpression.value =
                mCurrentExpression.value!!.replace(PERCENTAGE.toRegex(), "/100")

            val expression = Expression(mCurrentExpression.value!!)

            var bigDecimalResult = BigDecimal(0)

            try {
                bigDecimalResult = expression.eval()
            } catch (exception: ArithmeticException) {
                showInvalidExpressionMessage()
            }

            val doubleResult = bigDecimalResult.toDouble()

            val stringResult: String

            if (isValueInteger(doubleResult) && !isScientificNotation(
                    doubleResult.toString()
                )
            ) {
                val roundedValue = Math.round(doubleResult).toInt()
                stringResult = roundedValue.toString()
            } else {
                stringResult = java.lang.Double.toString(doubleResult)
            }

            mCurrentExpression.postValue(stringResult)
            mResult.postValue(stringResult)
        }
    }

    fun onExpressionSignChange() {
        if (currentExpressionIsInvalid()) {
            showInvalidExpressionMessage()
        } else {
            val newCurrentExpression = if (isNumberPositive)
                "-${mCurrentExpression.value}"
            else
                mCurrentExpression.value!!.substring(1, mCurrentExpression.value!!.length)

            mCurrentExpression.postValue(newCurrentExpression)
            isNumberPositive = !isNumberPositive
        }
    }

    private fun currentExpressionIsInvalid() =
        mCurrentExpression.value == null || mCurrentExpression.value!!.isEmpty()

    private fun isValueAnOperator(value: String): Boolean {
        return validOperators.contains(value.toCharArray()[0].toString())
    }

    private fun clearLastCharOfExpression() {
        mCurrentExpression.postValue(
            mCurrentExpression.value!!.substring(
                0,
                mCurrentExpression.value!!.length - 1
            )
        )
    }

    private fun clearLastValueIfItIsAnOperator() {
        if (currentExpressionIsInvalid()) {
            showInvalidExpressionMessage()
        } else if (isValueAnOperator(getLastCharOfExpression().toString())) {
            clearLastCharOfExpression()
        }
    }

    private fun getLastCharOfExpression(): Char {
        val currentExpressionLastValuePosition = mCurrentExpression.value!!.length - 1
        return mCurrentExpression.value!![currentExpressionLastValuePosition]
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
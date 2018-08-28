package zurche.simplecalculator.calculator

interface CalculatorContract {

    interface View {
        fun showResult(result: String)
        fun updateCurrentExpression(updatedExpression: String)
        fun showInvalidExpressionMessage()
    }

    interface Presenter {
        fun onOperatorAdd(addedValue: String)
        fun onClearExpression()
        fun onCalculateResult()
        fun onExpressionSignChange()
    }
}


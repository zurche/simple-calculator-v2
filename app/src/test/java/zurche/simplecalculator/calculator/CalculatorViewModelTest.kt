package zurche.simplecalculator.calculator

import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {

    lateinit var calculatorViewModel: CalculatorViewModel

    @Before
    fun setup() {
        calculatorViewModel = CalculatorViewModel()
    }

    @Test
    fun `When dividing by zero error is handled`() {
        calculatorViewModel.onOperatorAdd("0")
        calculatorViewModel.onOperatorAdd("/")
        calculatorViewModel.onOperatorAdd("0")

        calculatorViewModel.onCalculateResult()
    }
}
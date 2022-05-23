package zurche.simplecalculator.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CalculatorViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

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

        calculatorViewModel.getInvalidExpressionMessageEvent().value?.let {
            assertTrue(
                "Invalid Expression Event should be true",
                it
            )
        }
    }
}
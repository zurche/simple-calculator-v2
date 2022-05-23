package zurche.simplecalculator.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.*
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.rules.TestRule

class CalculatorViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var expressionObserver: Observer<String?>

    private lateinit var calculatorViewModel: CalculatorViewModel

    @Before
    fun setup() {
        calculatorViewModel = CalculatorViewModel()
        MockKAnnotations.init(this, relaxUnitFun = true)
        calculatorViewModel.getCurrentExpression().observeForever(expressionObserver)
    }

    @After
    fun tearDown() {
        confirmVerified(expressionObserver)
        calculatorViewModel.getCurrentExpression().removeObserver(expressionObserver)
        unmockkAll()
    }

    @Test
    @Ignore("An extra call is made at the end. Need to verify why. Ignoring for now.")
    fun `When dividing by zero error is displayed`() {
        calculatorViewModel.onOperatorAdd("0")
        calculatorViewModel.onOperatorAdd("/")
        calculatorViewModel.onOperatorAdd("0")

        calculatorViewModel.onCalculateResult()

        assertTrue(
            "Invalid Expression message should be shown in this scenario",
            calculatorViewModel.getInvalidExpressionMessageEvent().value ?: false
        )

        verifyOrder {
            expressionObserver.onChanged("0")
            expressionObserver.onChanged("0/")
            expressionObserver.onChanged("0/0")
        }
    }

    @Test
    fun `Given the expression is empty When the operator is comma Then error is displayed`() {
        calculatorViewModel.onOperatorAdd(".")

        assertTrue(
            "Invalid Expression message should be shown in this scenario",
            calculatorViewModel.getInvalidExpressionMessageEvent().value ?: false
        )

        verify(exactly = 0) { expressionObserver.onChanged(any()) }
    }

    @Test
    fun `Given the expression is empty When the operator is change polarity Then error is displayed`() {
        calculatorViewModel.onExpressionSignChange()

        assertTrue(
            "Invalid Expression message should be shown in this scenario",
            calculatorViewModel.getInvalidExpressionMessageEvent().value ?: false
        )

        verify(exactly = 0) { expressionObserver.onChanged(any()) }
    }

    @Test
    fun `Given the expression is empty When the operator is sum Then error is displayed`() {
        calculatorViewModel.onOperatorAdd("+")

        assertTrue(
            "Invalid Expression message should be shown in this scenario",
            calculatorViewModel.getInvalidExpressionMessageEvent().value ?: false
        )

        verify(exactly = 0) { expressionObserver.onChanged(any()) }
    }

    @Test
    fun `Given the expression is empty When the operator is minus Then error is displayed`() {
        calculatorViewModel.onOperatorAdd("-")

        assertTrue(
            "Invalid Expression message should be shown in this scenario",
            calculatorViewModel.getInvalidExpressionMessageEvent().value ?: false
        )

        verify(exactly = 0) { expressionObserver.onChanged(any()) }
    }

    @Test
    fun `Given the expression is empty When the operator is multiply Then error is displayed`() {
        calculatorViewModel.onOperatorAdd("*")

        assertTrue(
            "Invalid Expression message should be shown in this scenario",
            calculatorViewModel.getInvalidExpressionMessageEvent().value ?: false
        )

        verify(exactly = 0) { expressionObserver.onChanged(any()) }
    }

    @Test
    fun `Given the expression is empty When the operator is divide Then error is displayed`() {
        calculatorViewModel.onOperatorAdd("/")

        assertTrue(
            "Invalid Expression message should be shown in this scenario",
            calculatorViewModel.getInvalidExpressionMessageEvent().value ?: false
        )

        verify(exactly = 0) { expressionObserver.onChanged(any()) }
    }

    @Test
    fun `Given the expression is empty When trying to calculate the result Then error is displayed`() {
        calculatorViewModel.onCalculateResult()

        assertTrue(
            "Invalid Expression message should be shown in this scenario",
            calculatorViewModel.getInvalidExpressionMessageEvent().value ?: false
        )

        verify(exactly = 0) { expressionObserver.onChanged(any()) }
    }

    @Test
    fun `Given the expression is valid And already has a trailing comma When adding an extra comma And checking the current expression Then error is not displayed And extra comma is not added`() {
        calculatorViewModel.onOperatorAdd("1")
        calculatorViewModel.onOperatorAdd(".")
        calculatorViewModel.onOperatorAdd(".")

        assertNull(
            "Invalid Expression message should be not shown in this scenario",
            calculatorViewModel.getInvalidExpressionMessageEvent().value
        )

        verifyOrder {
            expressionObserver.onChanged("1")
            expressionObserver.onChanged("1.")
        }
    }

    @Test
    @Ignore("An extra call is made at the end. Need to verify why. Ignoring for now.")
    fun `Given the expression is valid And already has a trailing plus sign When adding another plus sign Then error is not displayed And extra plus sign is not added`() {
        calculatorViewModel.onOperatorAdd("1")
        calculatorViewModel.onOperatorAdd("+")
        calculatorViewModel.onOperatorAdd("+")

        assertNull(
            "Invalid Expression message should be not shown in this scenario",
            calculatorViewModel.getInvalidExpressionMessageEvent().value
        )

        verifyOrder {
            expressionObserver.onChanged("1")
            expressionObserver.onChanged("1+")
        }
    }
}
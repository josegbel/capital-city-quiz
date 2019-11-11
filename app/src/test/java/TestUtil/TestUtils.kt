package TestUtil

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MainCoroutineRule : TestRule {
    override fun apply(base: Statement, description: Description?): Statement? {
        val testDispatcher = TestCoroutineDispatcher()
        try {
            return object : Statement() {
                @Throws(Throwable::class)
                override fun evaluate() {
                    Dispatchers.setMain(testDispatcher)
                    base.evaluate()
                }
            }
        } finally {
            Dispatchers.resetMain()
            testDispatcher.cleanupTestCoroutines()
        }
    }
}
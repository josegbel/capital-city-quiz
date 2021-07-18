package com.example.capitalcityquizktx.testUtil

import com.example.capitalcityquizktx.common.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.*

@ExperimentalCoroutinesApi
@ExtendWith(CoroutineTestExtension::class)
interface CoroutineTest {
    var testScope: TestCoroutineScope
    var testDispatcher: TestCoroutineDispatcher
}

@ExperimentalCoroutinesApi
class CoroutineTestExtension(val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    BeforeEachCallback, AfterEachCallback, TestInstancePostProcessor {

    val testDispatcherProvider = object : DispatcherProvider {
        override fun default(): CoroutineDispatcher = testDispatcher
        override fun io(): CoroutineDispatcher = testDispatcher
        override fun main(): CoroutineDispatcher = testDispatcher
        override fun unconfined(): CoroutineDispatcher = testDispatcher
    }

//    override fun starting(description: Description?) {
//        super.starting(description)
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    override fun finished(description: Description?) {
//        super.finished(description)
//        Dispatchers.resetMain()
//        testDispatcher.cleanupTestCoroutines()
//    }

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    override fun postProcessTestInstance(testInstance: Any?, context: ExtensionContext?) {
        val testDispatcher = TestCoroutineDispatcher()
        val testScope = TestCoroutineScope(testDispatcher)

        (testInstance as? CoroutineTest)?.let {
                it.testScope = testScope
                it.testDispatcher = testDispatcher
        }
    }
}

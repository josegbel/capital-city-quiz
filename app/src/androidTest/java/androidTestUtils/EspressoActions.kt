package androidTestUtils

import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import org.junit.runner.Description


/**
 *  Helper function used to set the progress of the seek bar
 */
object SeekbarActions {
    fun setProgress(progress: Int): ViewAction? {
        return object : ViewAction {
            override fun perform(uiController: UiController?, view: View) {
                val seekBar = view as SeekBar
                seekBar.progress = progress
            }

            override fun getDescription(): String {
                return "Set a progress on a SeekBar"
            }

            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(SeekBar::class.java)
            }
        }
    }

    /**
     * Helper function that returns a matcher used to check the progress from the Seekbar
     */
    fun withSeekbarProgress(expectedProgress: Int): Matcher<View?>? {
        return object :
            BoundedMatcher<View?, AppCompatSeekBar>(AppCompatSeekBar::class.java) {
            fun describeTo(description: Description) {

            }

            override fun matchesSafely(seekBar: AppCompatSeekBar): Boolean {
                return seekBar.progress == expectedProgress
            }

            override fun describeTo(description: org.hamcrest.Description?) {
                description?.appendText("expected: ")
                description?.appendText("" + expectedProgress)
            }
        }
    }

    fun getMax(matcher: ViewInteraction): Int {
        var max = 0
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(SeekBar::class.java)
            }

            override fun getDescription(): String {
                return "Max of the seekbar"
            }

            override fun perform(uiController: UiController, view: View) {
                val sb = view as SeekBar
                max = sb.max
            }
        })

        return max
    }
}

object TextViewActions{
    fun getText(matcher: ViewInteraction): String {
        var text = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "Text of the view"
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView
                text = tv.text.toString()
            }
        })

        return text
    }
}

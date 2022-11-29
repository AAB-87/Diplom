package ru.iteco.fmhandroid.utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.R;

public class SwipeActions {

    public static boolean ViewAfterSwipe(ViewInteraction locator, int recycler, boolean finishSwipe) {
        try {
            locator.check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException ignored) {
        }
        boolean invisible = true;
        int n = 1;
        while (invisible) {
            try {
                if (recycler == 1) {
                    onView(allOf(withId(R.id.news_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
                }
                if (recycler == 2) {
                    onView(allOf(withId(R.id.claim_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
                }
                if (recycler == 3) {
                    onView(allOf(withId(R.id.our_mission_item_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
                }
                if (recycler == 4) {
                    onView(allOf(withId(R.id.claim_comments_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
                }
            } catch (PerformException e) {
                return false;
            }
            try {
                locator.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));//.check(matches(isDisplayed()));
                invisible = false;
            } catch (NoMatchingViewException ignored) {
            }
            n++;
            if (!invisible & finishSwipe) {
                try {
                    if (recycler == 1) {
                        onView(allOf(withId(R.id.news_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
                    }
                    if (recycler == 2) {
                        onView(allOf(withId(R.id.claim_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
                    }
                    if (recycler == 3) {
                        onView(allOf(withId(R.id.our_mission_item_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
                    }
                    if (recycler == 4) {
                        onView(allOf(withId(R.id.claim_comments_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
                    }
                } catch (PerformException e) {
                    return false;
                }
            }
            if (n > 700) {
                return false;
            }
            SystemClock.sleep(2000);
        }
        return true;
    }
}

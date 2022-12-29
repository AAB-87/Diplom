package ru.iteco.fmhandroid.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.rule.ActivityTestRule;

import ru.iteco.fmhandroid.ui.AppActivity;

public class AuthorizationPageElements {

    public static void checkTextInvalidUsernameOrPassword(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Неверный логин или пароль"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
    }

    public static void checkTextLoginAndPasswordCannotBeEmpty(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Логин и пароль не могут быть пустыми"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
    }

    public static void checkTextCharacterLimitExceeded(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Превышен лимит символов"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
    }

    public static void checkTextTheUsersAccountIsBlocked(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Учётная запись пользователя заблокирована"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
    }


}

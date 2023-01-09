package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.utils.ViewActions;

public class AboutAppPage {

    public static void checkOpenAboutAppPage() {
        onView(withId(R.id.about_version_title_text_view)).check(matches(isDisplayed())); // проверка что версия отображается
    }

    public static void openAboutApp() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("О приложении")).perform(click()); // кликаем по О приложении
    }

    public static void checkVersionApp() {
        onView(withId(R.id.about_version_value_text_view)).check(matches(withText("1.0.0"))); // проверяем какая версия отображается
    }

    public static void clickPrivacyPolicy() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.about_privacy_policy_value_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.about_privacy_policy_value_text_view)).perform(click()); // кликаем по ссылке Политика конфиденциальности
        intended(allOf(hasData("https://vhospice.org/#/privacy-policy/"), hasAction(Intent.ACTION_VIEW))); // переход по ссылке, открытие браузера
    }

    public static void clickUserAgreement() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.about_terms_of_use_value_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.about_terms_of_use_value_text_view)).perform(click()); // кликаем по ссылке Пользовательское соглашение
        intended(allOf(hasData("https://vhospice.org/#/terms-of-use"), hasAction(Intent.ACTION_VIEW))); // переход по ссылке, открытие браузера
    }

    public static void checkCompanyNameAndYear() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.about_company_info_label_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.about_company_info_label_text_view)).check(matches(withText("© Айтеко, 2022"))); // проверяем соответствие
    }

}

package ru.iteco.fmhandroid.utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import android.widget.TimePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;


public class FillInFieldsForCreateClaims {

    public static void FillInFieldsClaims(String emptyTitle, String title, String emptyExecutor, String choiceOfExecutor, String chosenExecutor, String executor, String emptyDate, String emptyTime, String withDialPadOrTextInput, String saveOrCancelTime, String emptyDescription, String description) {
        // позиции всех исполнителей
        Integer executorPosition = null;
        if (chosenExecutor == "Ivanov Ivan Ivanovich") {
            executorPosition = 0;
        } else if (chosenExecutor == "Иванов Данил Данилович") {
            executorPosition = 1;
        } else if (chosenExecutor == "Петров Егор Егорович") {
            executorPosition = 2;
        } else if (chosenExecutor == "Сидоров Дмитрий Дмитриевич") {
            executorPosition = 3;
        } else if (chosenExecutor == "Тестов Тест Тестович") {
            executorPosition = 4;
        } else if (chosenExecutor == "Netology Diplom QAMID") {
            executorPosition = 5;
        }
        // заполнение поля "Тема"
        if (emptyTitle == "no") {
            onView(withId(R.id.title_edit_text)).perform(replaceText(title));
            onView(withId(R.id.title_edit_text)).check(matches(withText(title)));
        }
        // заполнение поля "Исполнитель"
        if (emptyExecutor == "no") {
            if (choiceOfExecutor == "yes") {
                onView(withId(R.id.executor_drop_menu_auto_complete_text_view)).perform(click());
                Espresso.onData(Matchers.anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(executorPosition).perform(ViewActions.click());
            } else {
                onView(withId(R.id.executor_drop_menu_text_input_layout)).perform(replaceText(executor));
                onView(withId(R.id.executor_drop_menu_text_input_layout)).check(matches(withText(executor)));
            }
        }
        // заполнение поля "Дата"
        if (emptyDate == "no") {
            onView(withId(R.id.date_in_plan_text_input_edit_text)).perform(click());
            onView(withText("ОК")).perform(click());
        }
        // заполнение поля "Время"
        if (emptyTime == "no") {
            if (withDialPadOrTextInput == "dial") {
                onView(withId(R.id.time_in_plan_text_input_edit_text)).perform(click());
                if (saveOrCancelTime == "save") {
                    onView(withText("ОК")).perform(click());
                } else {
                    onView(withText("ОТМЕНА")).perform(click());
                }
            } else {
                onView(withId(R.id.time_in_plan_text_input_edit_text)).perform(click());
                onView(withContentDescription("Switch to text input mode for the time input.")).perform(click());
                onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(2, 23));
                onView(withText("ОК")).perform(click());
                onView(withId(R.id.time_in_plan_text_input_edit_text)).check(matches(withText("02:23")));
            }
        }
        // заполнение поля "Описание"
        if (emptyDescription == "no") {
            onView(withId(R.id.description_edit_text)).perform(replaceText(description));
            onView(withId(R.id.description_edit_text)).check(matches(withText(description)));
        }
    }

    public static void timeInput(String hours, String minutes) {
        onView(withId(R.id.time_in_plan_text_input_edit_text)).perform(click());
        onView(withContentDescription("Switch to text input mode for the time input.")).perform(click());
        onView(withIndex(withClassName(is("androidx.appcompat.widget.AppCompatEditText")), 0)).perform(replaceText(hours));
        onView(withIndex(withClassName(is("androidx.appcompat.widget.AppCompatEditText")), 1)).perform(replaceText(minutes));
        onView(withText("ОК")).perform(click());
    }

    public static void checkMessageOfTimeInputError() {
        onView(withText("Указано недопустимое время")).check(matches(isDisplayed()));
    }

    public static void saveClaim() {
        onView(withId(R.id.save_button)).perform(click());
        onView(withText("Заявки")).check(matches(isDisplayed()));
    }

    public static void cancelSavingOfClaim() {
        onView(withId(R.id.cancel_button)).perform(click());
        onView(withText("ОК")).perform(click());
        onView(withText("Заявки")).check(matches(isDisplayed()));
    }

    public static void checkMessageThatFieldsShouldBeFilled(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText(R.string.empty_fields))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("Заполните пустые поля")));
    }
}
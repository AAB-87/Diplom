package ru.iteco.fmhandroid.utils;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.TimePicker;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.Matchers;
import ru.iteco.fmhandroid.R;


public class FillInFieldsForCreateNews {

    public static void FillInFieldsNews(String emptyCategory, String choiceOfCategory, String chosenCategory, String category, String title, String emptyDate, String emptyTime, String withDialPadOrTextInput, String saveOrCancelTime, String emptyDescription, String description) {
        // позиции всех категорий
        Integer categoryPosition = null;
        if (chosenCategory == "Объявление") {
            categoryPosition = 0;
        } else if (chosenCategory == "День рождения") {
            categoryPosition = 1;
        } else if (chosenCategory == "Зарплата") {
            categoryPosition = 2;
        } else if (chosenCategory == "Профсоюз") {
            categoryPosition = 3;
        } else if (chosenCategory == "Праздник") {
            categoryPosition = 4;
        } else if (chosenCategory == "Массаж") {
            categoryPosition = 5;
        } else if (chosenCategory == "Благодарность") {
            categoryPosition = 6;
        } else if (chosenCategory == "Нужна помощь") {
            categoryPosition = 7;
        }

        // заполнение поля "Категория"
        if (emptyCategory == "no") {
            if (choiceOfCategory == "yes") {
                onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
                Espresso.onData(Matchers.anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(categoryPosition).perform(ViewActions.click());
            } else {
                onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(replaceText(category));
                onView(withId(R.id.news_item_category_text_auto_complete_text_view)).check(matches(isDisplayed()));
            }
        } else {
            onView(withId(R.id.news_item_title_text_input_edit_text)).perform(replaceText(title));
            onView(withId(R.id.news_item_title_text_input_edit_text)).check(matches(withText(title)));
        }
        // заполнение поля "Дата"
        if (emptyDate == "no") {
            onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(click());
            onView(withText("ОК")).perform(click());
        }
        // заполнение поля "Время"
        if (emptyTime == "no") {
            closeSoftKeyboard(); // скрываем клавиатуру ввода
            if (withDialPadOrTextInput == "dial") {
                onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(click());
                if (saveOrCancelTime == "save") {
                    onView(withText("ОК")).perform(click());
                } else {
                    onView(withText("ОТМЕНА")).perform(click());
                }
            } else {
                onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(click());
                onView(withContentDescription("Switch to text input mode for the time input.")).perform(click());
                onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(2, 23));
                onView(withText("ОК")).perform(click());
                onView(withId(R.id.news_item_publish_time_text_input_edit_text)).check(matches(withText("02:23")));
            }
        }
        // заполнение поля "Описание"
        if (emptyDescription == "no") {
            closeSoftKeyboard(); // скрываем клавиатуру ввода
            onView(withId(R.id.news_item_description_text_input_edit_text)).perform(replaceText(description));
            onView(withId(R.id.news_item_description_text_input_edit_text)).check(matches(withText(description)));
        }
    }
}
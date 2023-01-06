package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.data.MissionData;
import ru.iteco.fmhandroid.utils.ViewActions;

public class OurMissionPage {

    static MissionData.QuotesData text = new MissionData.QuotesData();

    public static OurMissionPage openOurMission() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.our_mission_image_button), 10000)); // ожидаем появления нужного элемента
        onView(withId(R.id.our_mission_image_button)).perform(click()); // находим иконку для входа и кликаем по ней
        return new OurMissionPage();
    }

    public static OurMissionPage checkTitleMissionPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.our_mission_title_text_view), 10000)); // ожидаем появления нужного элемента
        onView(withId(R.id.our_mission_title_text_view)).check(matches(withText(text.getTitleText()))); // проверяем что отображается заголовок страницы
        return new OurMissionPage();
    }

    public static OurMissionPage openSecondQuote() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.our_mission_item_open_card_image_button), 10000)); // ожидаем появления нужного элемента
        onView(withIndex(withId(R.id.our_mission_item_open_card_image_button), 1)).perform(click()); // с помощью утилиты находим 2ю цитату в списке
        return new OurMissionPage();
    }

    public static OurMissionPage viewDescriptionSecondQuote() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.our_mission_item_description_text_view), 10000)); // ожидаем появления нужного элемента
        onView(withIndex(withId(R.id.our_mission_item_description_text_view), 1)).check(matches(isDisplayed())); // с помощью утилиты находим описание 2ой цитаты в списке
        return new OurMissionPage();
    }

    public static OurMissionPage scrollAndOpenPageQuote() {
        onView(ViewMatchers.withId(R.id.our_mission_item_list_recycler_view)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText(text.getTitleEighthQuote())))); // скролим вниз до последней цитаты
        onView(isRoot()).perform(ViewActions.waitElement((withText(text.getTitleFifthQuote())), 10000)); // ожидаем появление нужного элемента
        onView(withText(text.getTitleFifthQuote())).perform(click()); // кликаем цитате
        return new OurMissionPage();
    }

    public static OurMissionPage viewDescriptionFifthQuote() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.our_mission_item_description_text_view), 10000)); // ожидаем появления нужного элемента
        onView(withIndex(withId(R.id.our_mission_item_description_text_view), 4)).check(matches(isDisplayed())); // с помощью утилиты находим описание 5ой цитаты в списке
        return new OurMissionPage();
    }


}




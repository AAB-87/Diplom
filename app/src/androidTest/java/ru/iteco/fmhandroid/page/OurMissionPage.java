package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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

    public static OurMissionPage scrollAndOpenPageQuote() {
        onView(ViewMatchers.withId(R.id.our_mission_item_list_recycler_view)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText(text.getTitleEighthQuote())))); // скролим вниз до последней цитаты
        onView(isRoot()).perform(ViewActions.waitElement((withText(text.getTitleFifthQuote())), 10000)); // ожидаем появление нужного элемента
        onView(withText(text.getTitleFifthQuote())).perform(click()); // кликаем цитате
        return new OurMissionPage();
    }


}




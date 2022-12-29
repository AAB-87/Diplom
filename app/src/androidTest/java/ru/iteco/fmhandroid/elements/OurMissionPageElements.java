package ru.iteco.fmhandroid.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class OurMissionPageElements {

    public static ViewInteraction viewTitleMissionPage = onView(withId(R.id.our_mission_title_text_view)); // заголовок страницы
    public static ViewInteraction openSecondQuote = onView(withIndex(withId(R.id.our_mission_item_open_card_image_button), 1)); // с помощью утилиты находим 2ю цитату в списке
    public static ViewInteraction descriptionFifthQuote = onView(withIndex(withId(R.id.our_mission_item_description_text_view), 4)); // с помощью утилиты находим описание 5ой цитаты в списке
    public static ViewInteraction descriptionSecondQuote = onView(withIndex(withId(R.id.our_mission_item_description_text_view), 1)); // с помощью утилиты находим описание 2ой цитаты в списке

}

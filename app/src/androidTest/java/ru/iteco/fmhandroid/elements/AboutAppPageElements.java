package ru.iteco.fmhandroid.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutAppPageElements {

    public static ViewInteraction viewVersion = onView(withId(R.id.about_version_title_text_view)); // проверка что версия отображается


}

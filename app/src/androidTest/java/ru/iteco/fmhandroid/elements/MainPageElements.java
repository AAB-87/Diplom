package ru.iteco.fmhandroid.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainPageElements {

    public static ViewInteraction mainPage = onView(withId(R.id.container_list_news_include_on_fragment_main)); // проверяем что отображается блок новостей
    public static ViewInteraction viewNewsBlock = onView(withText("Новости")); // проверяем что заголовок Новости отображается
    public static ViewInteraction viewClaimsBlock = onView(withText("Заявки")); // проверяем что заголовок Заявки отображается
    public static ViewInteraction viewNewsList = onView(withId(R.id.container_list_news_include)); // открытие списка новостей
    public static ViewInteraction viewClaimsList = onView(withId(R.id.claim_list_recycler_view)); // открытие списка новостей
    public static ViewInteraction openFirstNews = onView(withIndex(withId(R.id.news_item_material_card_view), 0)); // с помощью утилиты находим 1ю новость в списке
    public static ViewInteraction descriptionFirstNews = onView(withIndex(withId(R.id.news_item_description_text_view), 0)); // с помощью утилиты находим 1ю новость в списке
    public static ViewInteraction arrowOfNews = onView(withIndex(withId(R.id.expand_material_button), 0)); // кликаем по кнопке сокрытия блока новостей
    public static ViewInteraction noNewsBlock = onView(withIndex(withId(R.id.news_item_material_card_view), 0)); // блок новостей
    public static ViewInteraction arrowOfClaims = onView(withIndex(withId(R.id.expand_material_button), 1)); // кликаем по кнопке сокрытия блока новостей
    public static ViewInteraction noClaimsBlock = onView(withIndex(withId(R.id.claim_list_card), 1)); // блок зявок
    public static ViewInteraction viewCreateClaimPage = onView(withId(R.id.title_edit_text)); // страница создания заявки

}

package ru.iteco.fmhandroid.tests;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;

import ru.iteco.fmhandroid.ui.AppActivity;

public class RunRuleTest {

    @Rule // указываем какое приложение будем запускать. Cм activity в AndroidManifest.xml
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);
}

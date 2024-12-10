package com.ivan.myapplication.navigation


sealed class Screen(val route: String) {
    object MainPage : Screen("mainPage")
    object TiketsPage : Screen("tiketsPage")
    object SettingPage: Screen("settingPage")
}
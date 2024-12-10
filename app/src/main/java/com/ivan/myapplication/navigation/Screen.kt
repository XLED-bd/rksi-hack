package com.ivan.myapplication.navigation


sealed class Screen(val route: String) {
    object MainPage : Screen("mainPage")
    object ListInvestmentsPage : Screen("investmentsPage")
    object SettingPage: Screen("settingPage")
}
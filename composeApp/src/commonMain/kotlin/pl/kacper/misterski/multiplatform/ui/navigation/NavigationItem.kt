package pl.kacper.misterski.multiplatform.ui.navigation

sealed class NavigationItem(
    val route: String,
) {
    data object Dog : NavigationItem(Screen.DOG.name)
    data object Start : NavigationItem(Screen.START.name)

    data object Permission : NavigationItem(Screen.Permission.name)


}
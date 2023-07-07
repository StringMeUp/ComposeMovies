import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.sr.compose.NavigationConstants
import com.sr.compose.customSerializable
import com.sr.compose.navigation.NavigationItem
import com.sr.compose.ui.screens.*

//function to navigate with a navController
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigation(
    navController: NavHostController
) {
    //navHost with start destination
    AnimatedNavHost(navController = navController, startDestination = NavigationItem.Main.route) {
        composable(
            route = NavigationItem.Main.route,
            enterTransition = {
                when (initialState.destination.route) {
                    NavigationItem.DefaultArgs.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(500))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    NavigationItem.DefaultArgs.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(500))
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    NavigationItem.DefaultArgs.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(500))
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    NavigationItem.DefaultArgs.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(500))
                    else -> null
                }
            }
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = NavigationItem.DefaultArgs.route,
            arguments = listOf(
                navArgument(name = NavigationConstants.Arg_Default) {
                    type = NavType.StringType
                    defaultValue = "Default"
                }
            ),
            enterTransition = {
                when (initialState.destination.route) {
                    NavigationItem.Main.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(500))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    NavigationItem.Main.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(500))
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    NavigationItem.Main.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(500))
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    NavigationItem.Main.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(500))
                    else -> null
                }
            }) { backStackEntry ->
            DefaultArgsScreen(navController = navController,
                value = backStackEntry.arguments?.getString(NavigationConstants.Arg_Default))
        }

        composable(route = NavigationItem.NullableArgs.route, arguments = listOf(
            navArgument(name = NavigationConstants.Arg_Nullable) {
                type = NavType.StringType
                nullable = true
            })) { backStackEntry ->
            NullableArgsScreen(
                navController = navController,
                args = backStackEntry.arguments?.getString(NavigationConstants.Arg_Nullable))
        }

        composable(route = NavigationItem.SerializableArgs.route, arguments = listOf(
            navArgument(name = NavigationConstants.Arg_Serializable) {
                type = NavType.StringType
                nullable = true
            })) { backStackEntry ->
            SerializableScreen(
                navController = navController,
                args = backStackEntry.customSerializable(NavigationConstants.Arg_Serializable))
        }

        /** Jetpack Compose without animations::
        composable(route = NavigationItem.Main.route) {
        MainScreen(navController = navController)
        }
        composable(route = NavigationItem.Detail.route) {
        DetailScreen()
        }*/
    }
}

inline fun Modifier.clickWithDebounce(
    debounceInterval: Long = 700,
    crossinline onClick: () -> Unit,
): Modifier {
    var lastClickTime = 0L
    val currentTime = System.currentTimeMillis()
    return clickable {
        if ((currentTime - lastClickTime) < debounceInterval) return@clickable
        lastClickTime = currentTime
        onClick()
    }
}
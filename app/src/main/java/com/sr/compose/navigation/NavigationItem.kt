package com.sr.compose.navigation

import android.os.Parcelable
import com.sr.compose.NavigationConstants
import com.sr.compose.R
import kotlinx.parcelize.Parcelize


@Parcelize
data class ComposeItem(
    val text: String,
    val image: Int,
) : Parcelable {
    companion object {
        fun generate() = listOf(
            ComposeItem("Arguments", R.drawable.logo),
            ComposeItem("Bottom Navigation", R.drawable.jet),
            ComposeItem("Ui", R.drawable.ui),
            ComposeItem("Material", R.drawable.material)
        )
    }
}

sealed class NavigationItem(val route: String, val icon: Int, val name: Int) {
    object Main :
        NavigationItem(
            route = "main",
            icon = R.drawable.ic_siding,
            name = R.string.main_desc
        )

    object BottomMain :
        NavigationItem(
            route = "bottomMain",
            icon = R.drawable.ic_siding,
            name = R.string.main_desc
        ) {

        object BottomNavHome : NavigationItem(
            route = "Honme", icon = R.drawable.ic_siding,
            name = R.string.bottom_one
        )

        object BottomNavProfile : NavigationItem(
            route = "Profile", icon = R.drawable.ic_account,
            name = R.string.bottom_two
        )

        object BottomNavSettings : NavigationItem(
            route = "Settings", icon = R.drawable.ic_settings,
            name = R.string.bottom_three
        )

        object BottomNavContacts : NavigationItem(
            route = "Contacts", icon = R.drawable.ic_contacts,
            name = R.string.bottom_four
        )

        fun bottomNavDestinations() = listOf<NavigationItem>(
            BottomNavHome,
            BottomNavProfile,
            BottomNavSettings,
            BottomNavContacts
        )
    }

    object DefaultArgs :
    /** Navigate with a default parameter */
        NavigationItem(
            route = "default/{${NavigationConstants.Arg_Default}}",
            icon = R.drawable.ic_boat,
            name = R.string.detail_desc
        )

    object NullableArgs :
        NavigationItem(
            route = "nullable?${NavigationConstants.Arg_Nullable}={${NavigationConstants.Arg_Nullable}}",
            icon = R.drawable.ic_next,
            name = R.string.nullable_desc
        )

    object SerializableArgs :
        NavigationItem(
            route = "serializable?${NavigationConstants.Arg_Serializable}={${NavigationConstants.Arg_Serializable}}",
            icon = R.drawable.ic_close,
            name = R.string.detail_serial
        )

    companion object {
        private fun all() = listOf(Main, DefaultArgs)
        fun findNavItem(route: String?): NavigationItem {
            return all().find { it.route == route } ?: Main
        }

        fun withRouteArgs(navItem: NavigationItem, arg: String): String {
            return "${navItem.route.substringBefore("{")}$arg"
        }

        fun withNullableRouteArgs(navItem: NavigationItem, arg: String? = null): String {
            return "${navItem.route.substringBefore("=")}=$arg"
        }

        /** NOTE: The Jetpack Compose team doesn’t recommend passing Parcelable in the
        navigation composable routes. Instead the route structure in Navigation
        Compose has the best analog with a restful web service so developers
        should use bookDetails/{bookid} not bookDetails/{a whole set of fields representing a book}
        which is essentially what passing a Parcelable is doing. */

        /** With that being said look at AndroidExtensions File -> */
    }
}

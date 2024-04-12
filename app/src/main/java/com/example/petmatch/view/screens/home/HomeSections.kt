package com.example.petmatch.view.screens.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.petmatch.R

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    FEED(title= R.string.home_feed, icon = Icons.Outlined.Home, "home/feed"),
    CART(title = R.string.home_cart, icon = Icons.Outlined.ShoppingCart, "home/cart"),
    PROFILE(title = R.string.home_profile, icon = Icons.Outlined.AccountCircle, "home/profile")
}
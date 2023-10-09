package com.sr.compose.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitSlideOutToRight() =
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(500)
    )

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterSlideInFromRight() =
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(500)
    )

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentTransitionScope<NavBackStackEntry>.enterSlideInFromLeft() =
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(500)
    )

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentTransitionScope<NavBackStackEntry>.exitSlideOutToLeft() =
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(500)
    )

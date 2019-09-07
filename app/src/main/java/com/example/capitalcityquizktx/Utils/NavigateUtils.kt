package com.example.capitalcityquizktx.Utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.navigation.Navigator

fun NavController.navigateSafe(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    val action = currentDestination?.getAction(resId)
    if (action != null) navigate(resId, args, navOptions, navExtras)
}

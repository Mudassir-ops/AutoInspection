import androidx.annotation.IdRes
import androidx.navigation.NavController

fun NavController.safeNav(
    @IdRes currentDestId: Int,
    @IdRes actionId: Int
) {
    if (currentDestination?.id == currentDestId) {
        navigate(actionId)
    }
}
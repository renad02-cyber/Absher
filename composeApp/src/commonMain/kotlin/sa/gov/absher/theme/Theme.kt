package sa.gov.absher.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import sa.gov.absher.data.UserType

val LocalAppColors = staticCompositionLocalOf { AppColors.Dark }
val LocalUserType = staticCompositionLocalOf { UserType.INDIVIDUAL }

private fun getMaterialColorScheme(primary: Color, isDark: Boolean): ColorScheme {
    return if (isDark) {
        darkColorScheme(
            primary = primary,
            onPrimary = Color.White,
            primaryContainer = primary.copy(alpha = 0.2f),
            onPrimaryContainer = primary,
            background = AbsherColors.Dark.Background,
            onBackground = AbsherColors.Dark.TextPrimary,
            surface = AbsherColors.Dark.Surface,
            onSurface = AbsherColors.Dark.TextPrimary,
            surfaceVariant = AbsherColors.Dark.SurfaceVariant,
            onSurfaceVariant = AbsherColors.Dark.TextSecondary,
            error = AbsherColors.Error,
            onError = Color.White,
            outline = AbsherColors.Dark.Border,
            outlineVariant = AbsherColors.Dark.BorderLight
        )
    } else {
        lightColorScheme(
            primary = primary,
            onPrimary = Color.White,
            primaryContainer = primary.copy(alpha = 0.1f),
            onPrimaryContainer = primary,
            background = AbsherColors.Light.Background,
            onBackground = AbsherColors.Light.TextPrimary,
            surface = AbsherColors.Light.Surface,
            onSurface = AbsherColors.Light.TextPrimary,
            surfaceVariant = AbsherColors.Light.SurfaceVariant,
            onSurfaceVariant = AbsherColors.Light.TextSecondary,
            error = AbsherColors.Error,
            onError = Color.White,
            outline = AbsherColors.Light.Border,
            outlineVariant = AbsherColors.Light.BorderLight
        )
    }
}

object AbsherTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current
    
    val userType: UserType
        @Composable
        @ReadOnlyComposable
        get() = LocalUserType.current
}

@Composable
fun AbsherTheme(
    userType: UserType = UserType.INDIVIDUAL,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = getMaterialColorScheme(userType.color, darkTheme)
    val appColors = if (darkTheme) AppColors.Dark else AppColors.Light
    
    CompositionLocalProvider(
        LocalAppColors provides appColors,
        LocalUserType provides userType
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

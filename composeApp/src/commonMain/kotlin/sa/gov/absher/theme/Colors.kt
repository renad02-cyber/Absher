package sa.gov.absher.theme

import androidx.compose.ui.graphics.Color

object AbsherColors {
    val AfradGreen = Color(0xFF0D6B3D)
    val AfradGreenDark = Color(0xFF0A5530)
    val AmaalBlue = Color(0xFF1E4A7A)
    val AmaalBlueDark = Color(0xFF153A62)
    val HkomaGold = Color(0xFFD4A537)
    val HkomaGoldDark = Color(0xFFB8922F)
    
    val GoldAccent = Color(0xFFD4AF37)
    val GoldLight = Color(0xFFE5C76B)
    
    val Error = Color(0xFFE53935)
    val Warning = Color(0xFFFFA726)
    val Success = Color(0xFF43A047)
    val Info = Color(0xFF1E88E5)
    
    val White = Color.White
    val Black = Color.Black
    
    object Dark {
        val Background = Color(0xFF0A0A0A)
        val Surface = Color(0xFF1A1A1A)
        val SurfaceVariant = Color(0xFF2A2A2A)
        val Card = Color(0xFF1E1E1E)
        val CardElevated = Color(0xFF252525)
        val Border = Color(0xFF3A3A3A)
        val BorderLight = Color(0xFF2D2D2D)
        val TextPrimary = Color(0xFFFFFFFF)
        val TextSecondary = Color(0xFFB0B0B0)
        val TextTertiary = Color(0xFF707070)
        val TextHint = Color(0xFF505050)
        val Divider = Color(0xFF333333)
        val InputBackground = Color(0xFF1A1A1A)
        val InputBorder = Color(0xFF3A3A3A)
    }
    
    object Light {
        val Background = Color(0xFFF8F9FA)
        val Surface = Color.White
        val SurfaceVariant = Color(0xFFF5F5F5)
        val Card = Color.White
        val CardElevated = Color.White
        val Border = Color(0xFFE0E0E0)
        val BorderLight = Color(0xFFF0F0F0)
        val TextPrimary = Color(0xFF1A1A1A)
        val TextSecondary = Color(0xFF666666)
        val TextTertiary = Color(0xFF999999)
        val TextHint = Color(0xFFBBBBBB)
        val Divider = Color(0xFFEEEEEE)
        val InputBackground = Color(0xFFF5F5F5)
        val InputBorder = Color(0xFFE0E0E0)
    }
}

data class AppColors(
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val card: Color,
    val cardElevated: Color,
    val border: Color,
    val borderLight: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textHint: Color,
    val divider: Color,
    val inputBackground: Color,
    val inputBorder: Color,
    val isDark: Boolean
) {
    companion object {
        val Dark = AppColors(
            background = AbsherColors.Dark.Background,
            surface = AbsherColors.Dark.Surface,
            surfaceVariant = AbsherColors.Dark.SurfaceVariant,
            card = AbsherColors.Dark.Card,
            cardElevated = AbsherColors.Dark.CardElevated,
            border = AbsherColors.Dark.Border,
            borderLight = AbsherColors.Dark.BorderLight,
            textPrimary = AbsherColors.Dark.TextPrimary,
            textSecondary = AbsherColors.Dark.TextSecondary,
            textTertiary = AbsherColors.Dark.TextTertiary,
            textHint = AbsherColors.Dark.TextHint,
            divider = AbsherColors.Dark.Divider,
            inputBackground = AbsherColors.Dark.InputBackground,
            inputBorder = AbsherColors.Dark.InputBorder,
            isDark = true
        )
        
        val Light = AppColors(
            background = AbsherColors.Light.Background,
            surface = AbsherColors.Light.Surface,
            surfaceVariant = AbsherColors.Light.SurfaceVariant,
            card = AbsherColors.Light.Card,
            cardElevated = AbsherColors.Light.CardElevated,
            border = AbsherColors.Light.Border,
            borderLight = AbsherColors.Light.BorderLight,
            textPrimary = AbsherColors.Light.TextPrimary,
            textSecondary = AbsherColors.Light.TextSecondary,
            textTertiary = AbsherColors.Light.TextTertiary,
            textHint = AbsherColors.Light.TextHint,
            divider = AbsherColors.Light.Divider,
            inputBackground = AbsherColors.Light.InputBackground,
            inputBorder = AbsherColors.Light.InputBorder,
            isDark = false
        )
    }
}

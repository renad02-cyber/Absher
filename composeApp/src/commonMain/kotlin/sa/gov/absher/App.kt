package sa.gov.absher

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import sa.gov.absher.theme.AbsherTheme
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import sa.gov.absher.navigation.WelcomeScreen

@Composable
fun App() {
    AbsherTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AbsherTheme.colors.background
        ) {
            Navigator(WelcomeScreen()) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}

package sa.gov.absher.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import sa.gov.absher.data.User
import sa.gov.absher.ui.*

class WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        WelcomeScreenContent()
    }
}

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        LoginScreenContent()
    }
}

class HomeScreen(
    private val userId: String,
    private val userName: String,
    private val userUsername: String,
    private val userTypeOrdinal: Int
) : Screen {
    
    constructor(user: User) : this(
        userId = user.id,
        userName = user.name,
        userUsername = user.username,
        userTypeOrdinal = user.type.ordinal
    )
    
    @Composable
    override fun Content() {
        val user = User.fromParams(userId, userName, userUsername, userTypeOrdinal)
        HomeScreenContent(user = user)
    }
}

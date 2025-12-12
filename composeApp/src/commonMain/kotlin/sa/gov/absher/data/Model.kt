package sa.gov.absher.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource
import sa.gov.absher.theme.AbsherColors
import absher.composeapp.generated.resources.*

enum class UserType(
    val icon: ImageVector,
    val logo: DrawableResource,
    val color: Color,
    val colorDark: Color
) {
    INDIVIDUAL(
        icon = Icons.Default.Person,
        logo = Res.drawable.absher_gov,
        color = AbsherColors.AfradGreen,
        colorDark = AbsherColors.AfradGreenDark
    ),
    BUSINESS(
        icon = Icons.Default.Business,
        logo = Res.drawable.absher_gov,
        color = AbsherColors.AmaalBlue,
        colorDark = AbsherColors.AmaalBlueDark
    ),
    GOVERNMENT(
        icon = Icons.Default.AccountBalance,
        logo = Res.drawable.absher_gov,
        color = AbsherColors.HkomaGold,
        colorDark = AbsherColors.HkomaGoldDark
    )
}

data class User(
    val id: String,
    val name: String,
    val username: String,
    val type: UserType
) {
    companion object {
        fun fromParams(id: String, name: String, username: String, typeOrdinal: Int): User {
            return User(
                id = id,
                name = name,
                username = username,
                type = UserType.entries[typeOrdinal]
            )
        }
    }
}

enum class ServiceIcon {
    PASSPORT,
    CARD,
    TRAFFIC,
    CALENDAR,
    USERS,
    VISA,
    TRANSFER,
    CERTIFICATE,
    SEARCH,
    CHART,
    LOCK,
    CAR,
    DOCUMENT,
    UNKNOWN
}

data class Service(
    val title: String,
    val icon: ServiceIcon
)

data class InfoItem(
    val title: String,
    val subtitle: String? = null,
    val status: String? = null
)

data class Section(
    val title: String,
    val items: List<InfoItem>
)

data class DashboardData(
    val services: List<Service>,
    val sections: List<Section>
)

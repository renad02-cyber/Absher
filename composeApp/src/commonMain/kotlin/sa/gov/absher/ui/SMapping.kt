package sa.gov.absher.ui

import absher.composeapp.generated.resources.Res
import absher.composeapp.generated.resources.type_business
import absher.composeapp.generated.resources.type_government
import absher.composeapp.generated.resources.type_individual
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import sa.gov.absher.data.ServiceIcon
import sa.gov.absher.data.UserType

val ServiceIcon.imageVector: ImageVector
    get() = when (this) {
        ServiceIcon.PASSPORT -> Icons.Default.Flight
        ServiceIcon.CARD -> Icons.Default.CreditCard
        ServiceIcon.TRAFFIC -> Icons.Default.DirectionsCar
        ServiceIcon.CALENDAR -> Icons.Default.DateRange
        ServiceIcon.USERS -> Icons.Default.People
        ServiceIcon.VISA -> Icons.Default.AirplanemodeActive
        ServiceIcon.TRANSFER -> Icons.Default.SwapHoriz
        ServiceIcon.CERTIFICATE -> Icons.Default.Verified
        ServiceIcon.SEARCH -> Icons.Default.Search
        ServiceIcon.CHART -> Icons.Default.BarChart
        ServiceIcon.LOCK -> Icons.Default.Lock
        ServiceIcon.CAR -> Icons.Default.DirectionsCar
        ServiceIcon.DOCUMENT -> Icons.Default.Description
        ServiceIcon.UNKNOWN -> Icons.Default.Info
    }



val UserType.titleRes: StringResource
    get() = when (this) {
        UserType.INDIVIDUAL -> Res.string.type_individual
        UserType.BUSINESS -> Res.string.type_business
        UserType.GOVERNMENT -> Res.string.type_government
    }

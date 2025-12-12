package sa.gov.absher.ui

import absher.composeapp.generated.resources.Res
import absher.composeapp.generated.resources.absher_gov
import absher.composeapp.generated.resources.app_name
import absher.composeapp.generated.resources.home_title
import absher.composeapp.generated.resources.id_number
import absher.composeapp.generated.resources.last_login
import absher.composeapp.generated.resources.logout
import absher.composeapp.generated.resources.my_data_title
import absher.composeapp.generated.resources.national_id
import absher.composeapp.generated.resources.notifications_tab
import absher.composeapp.generated.resources.passports
import absher.composeapp.generated.resources.reports
import absher.composeapp.generated.resources.services_tab
import absher.composeapp.generated.resources.services_title
import absher.composeapp.generated.resources.status
import absher.composeapp.generated.resources.status_active
import absher.composeapp.generated.resources.today
import absher.composeapp.generated.resources.vehicles
import absher.composeapp.generated.resources.view_all
import absher.composeapp.generated.resources.welcome
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.Flight
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import sa.gov.absher.data.DashboardData
import sa.gov.absher.data.InfoItem
import sa.gov.absher.data.MockAbsherRepository
import sa.gov.absher.data.Service
import sa.gov.absher.data.User
import sa.gov.absher.navigation.WelcomeScreen
import sa.gov.absher.theme.AbsherColors
import sa.gov.absher.theme.AbsherTheme
import sa.gov.absher.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(user: User) {
    val navigator = LocalNavigator.currentOrThrow
    val repository = remember { MockAbsherRepository() }
    val colors = AbsherTheme.colors

    val primaryColor = user.type.color

    var dashboardData by remember { mutableStateOf<DashboardData?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var selectedTab by remember { mutableStateOf(0) }

    LaunchedEffect(user) {
        dashboardData = repository.getDashboardData(user.type)
        isLoading = false
    }

    Scaffold(
        containerColor = colors.background,
        topBar = {
            Surface(
                color = colors.surface,
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { navigator.replaceAll(WelcomeScreen()) },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = AbsherColors.Error.copy(alpha = 0.1f)
                        )
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = stringResource(Res.string.logout),
                            tint = AbsherColors.Error
                        )
                    }

                    Image(
                        painter = painterResource(Res.drawable.absher_gov),
                        contentDescription = stringResource(Res.string.app_name),
                        modifier = Modifier.height(40.dp),
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(primaryColor)
                    )

                    IconButton(
                        onClick = { },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = colors.surfaceVariant
                        )
                    ) {
                        Icon(
                            Icons.Rounded.Notifications,
                            contentDescription = stringResource(Res.string.notifications_tab),
                            tint = colors.textPrimary
                        )
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = colors.surface,
                tonalElevation = 4.dp
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Rounded.Home,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            stringResource(Res.string.home_title),
                            fontSize = 11.sp,
                            maxLines = 1
                        )
                    },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = primaryColor,
                        selectedTextColor = primaryColor,
                        unselectedIconColor = colors.textTertiary,
                        unselectedTextColor = colors.textTertiary,
                        indicatorColor = primaryColor.copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Rounded.Apps,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            stringResource(Res.string.services_tab),
                            fontSize = 11.sp,
                            maxLines = 1
                        )
                    },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = primaryColor,
                        selectedTextColor = primaryColor,
                        unselectedIconColor = colors.textTertiary,
                        unselectedTextColor = colors.textTertiary,
                        indicatorColor = primaryColor.copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Rounded.Notifications,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            stringResource(Res.string.notifications_tab),
                            fontSize = 11.sp,
                            maxLines = 1
                        )
                    },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = primaryColor,
                        selectedTextColor = primaryColor,
                        unselectedIconColor = colors.textTertiary,
                        unselectedTextColor = colors.textTertiary,
                        indicatorColor = primaryColor.copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Rounded.Person,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            stringResource(Res.string.my_data_title),
                            fontSize = 11.sp,
                            maxLines = 1
                        )
                    },
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = primaryColor,
                        selectedTextColor = primaryColor,
                        unselectedIconColor = colors.textTertiary,
                        unselectedTextColor = colors.textTertiary,
                        indicatorColor = primaryColor.copy(alpha = 0.1f)
                    )
                )
            }
        }
    ) { padding ->
        if (isLoading) {
            Box(
                Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = primaryColor)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    UserProfileCard(user, primaryColor, colors)
                }

                item {
                    QuickActionsRow(primaryColor, colors)
                }

                item {
                    SectionHeader(stringResource(Res.string.services_title), primaryColor, colors)
                }

                item {
                    dashboardData?.services?.let { services ->
                        ServicesGrid(services, primaryColor, colors)
                    }
                }

                dashboardData?.sections?.forEach { section ->
                    item {
                        SectionHeader(section.title, primaryColor, colors)
                    }

                    items(section.items) { item ->
                        InfoItemCard(item, primaryColor, colors)
                    }
                }

                item {
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun UserProfileCard(user: User, primaryColor: Color, colors: AppColors) {
    val userTypeName = stringResource(user.type.titleRes)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(primaryColor, primaryColor.copy(alpha = 0.8f))
                    )
                )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.6f),
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(Modifier.weight(1f))

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = stringResource(Res.string.welcome),
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 14.sp
                        )
                        Text(
                            text = user.name,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(4.dp))
                        Surface(
                            color = AbsherColors.GoldAccent,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = userTypeName,
                                color = Color.Black,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(Modifier.width(16.dp))

                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f))
                            .border(2.dp, Color.White.copy(alpha = 0.4f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = user.name.take(1),
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                HorizontalDivider(color = Color.White.copy(alpha = 0.2f))

                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfileInfoItem(stringResource(Res.string.id_number), user.id)
                    ProfileInfoItem(
                        stringResource(Res.string.status),
                        stringResource(Res.string.status_active)
                    )
                    ProfileInfoItem(
                        stringResource(Res.string.last_login),
                        stringResource(Res.string.today)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileInfoItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 11.sp
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = value,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun QuickActionsRow(primaryColor: Color, colors: AppColors) {
    val nationalId = stringResource(Res.string.national_id)
    val vehicles = stringResource(Res.string.vehicles)
    val passports = stringResource(Res.string.passports)
    val reports = stringResource(Res.string.reports)

    LazyRow(
        modifier = Modifier.padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val quickActions = listOf(
            Pair(Icons.Rounded.CreditCard, nationalId),
            Pair(Icons.Rounded.DirectionsCar, vehicles),
            Pair(Icons.Rounded.Flight, passports),
            Pair(Icons.Rounded.Description, reports)
        )

        items(quickActions) { (icon, title) ->
            QuickActionCard(icon, title, primaryColor, colors)
        }
    }
}

@Composable
private fun QuickActionCard(
    icon: ImageVector,
    title: String,
    primaryColor: Color,
    colors: AppColors
) {
    Card(
        modifier = Modifier
            .width(90.dp)
            .clickable { },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = colors.card),
        elevation = CardDefaults.cardElevation(defaultElevation = if (colors.isDark) 0.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(primaryColor.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = primaryColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = title,
                color = colors.textPrimary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun SectionHeader(title: String, primaryColor: Color, colors: AppColors) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = { }) {
            Text(
                stringResource(Res.string.view_all),
                color = primaryColor,
                fontSize = 13.sp
            )
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = primaryColor,
                modifier = Modifier.size(18.dp)
            )
        }

        Text(
            text = title,
            color = colors.textPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ServicesGrid(services: List<Service>, primaryColor: Color, colors: AppColors) {
    val chunked = services.chunked(2)
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        chunked.forEach { rowServices ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowServices.forEach { service ->
                    Box(Modifier.weight(1f)) {
                        ServiceCard(service, primaryColor, colors)
                    }
                }
                if (rowServices.size == 1) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ServiceCard(service: Service, primaryColor: Color, colors: AppColors) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .clickable { },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = colors.card),
        elevation = CardDefaults.cardElevation(defaultElevation = if (colors.isDark) 0.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(primaryColor.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = service.icon.imageVector,
                    contentDescription = null,
                    tint = primaryColor,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = service.title,
                color = colors.textPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun InfoItemCard(item: InfoItem, primaryColor: Color, colors: AppColors) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = colors.card),
        elevation = CardDefaults.cardElevation(defaultElevation = if (colors.isDark) 0.dp else 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = colors.textTertiary,
                modifier = Modifier.size(20.dp)
            )

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    item.status?.let {
                        Surface(
                            color = AbsherColors.Success.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = it,
                                color = AbsherColors.Success,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(
                        text = item.title,
                        color = colors.textPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                item.subtitle?.let {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = it,
                        color = colors.textSecondary,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}

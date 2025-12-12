package sa.gov.absher.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import sa.gov.absher.navigation.LoginScreen
import sa.gov.absher.theme.AbsherColors
import sa.gov.absher.theme.AbsherTheme
import sa.gov.absher.util.AbsherUrls
import sa.gov.absher.util.openUrl
import absher.composeapp.generated.resources.*

@Composable
fun WelcomeScreenContent() {
    val navigator = LocalNavigator.currentOrThrow
    val colors = AbsherTheme.colors
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))
            
            Card(
                modifier = Modifier.size(130.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (colors.isDark) colors.card else Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (colors.isDark) 2.dp else 6.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(Res.drawable.absher_gov),
                        contentDescription = stringResource(Res.string.app_name),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(AbsherColors.AfradGreen)
                    )
                }
            }
            
            Spacer(Modifier.height(32.dp))
            
            Text(
                text = stringResource(Res.string.welcome_title),
                color = colors.textPrimary,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(Modifier.height(12.dp))
            
            Text(
                text = stringResource(Res.string.app_subtitle),
                color = colors.textSecondary,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )
            
            Spacer(Modifier.weight(1f))
            Spacer(Modifier.height(40.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (colors.isDark) colors.card else colors.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (colors.isDark) 0.dp else 4.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(Res.string.welcome_subtitle),
                        color = colors.textPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(Modifier.height(8.dp))
                    
                    Text(
                        text = stringResource(Res.string.welcome_description),
                        color = colors.textSecondary,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                    
                    Spacer(Modifier.height(28.dp))
                    
                    Button(
                        onClick = { navigator.push(LoginScreen()) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AbsherColors.AfradGreen
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            stringResource(Res.string.login),
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    Spacer(Modifier.height(12.dp))
                    
                    OutlinedButton(
                        onClick = { openUrl(AbsherUrls.REGISTER) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = AbsherColors.AfradGreen
                        ),
                        border = ButtonDefaults.outlinedButtonBorder(enabled = true),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            stringResource(Res.string.register),
                            color = AbsherColors.AfradGreen,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            
            Spacer(Modifier.height(16.dp))
            
            Text(
                text = stringResource(Res.string.copyright),
                color = colors.textTertiary,
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
            
            Spacer(Modifier.height(8.dp))
        }
    }
}

package sa.gov.absher.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import sa.gov.absher.data.MockAbsherRepository
import sa.gov.absher.data.UserType
import sa.gov.absher.navigation.HomeScreen
import sa.gov.absher.theme.AbsherTheme
import absher.composeapp.generated.resources.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.ColorFilter

@Composable
fun LoginScreenContent() {
    val navigator = LocalNavigator.currentOrThrow
    val repository = remember { MockAbsherRepository() }
    val colors = AbsherTheme.colors
    
    val scope = rememberCoroutineScope()
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(UserType.INDIVIDUAL) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }
    var keepLoggedIn by remember { mutableStateOf(false) }
    
    val primaryColor by animateColorAsState(
        targetValue = selectedType.color,
        animationSpec = tween(300)
    )

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
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { navigator.pop() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.back),
                        tint = colors.textPrimary
                    )
                }
            }
            
            Spacer(Modifier.height(16.dp))
            
            Image(
                painter = painterResource(selectedType.logo),
                contentDescription = stringResource(Res.string.app_name),
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(selectedType.color)
            )
            
            Spacer(Modifier.height(16.dp))
            
            Text(
                text = stringResource(Res.string.login_title),
                color = colors.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            
            Spacer(Modifier.height(24.dp))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colors.surfaceVariant)
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                UserType.entries.forEach { type ->
                    val isSelected = selectedType == type
                    val bgColor by animateColorAsState(
                        targetValue = if (isSelected) type.color else Color.Transparent,
                        animationSpec = tween(200)
                    )
                    
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(bgColor)
                            .clickable { selectedType = type }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Spacer(Modifier.width(6.dp))
                            Text(
                                text = stringResource(type.titleRes),
                                color = if (isSelected) Color.White else colors.textSecondary,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }
            }
            
            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = id,
                onValueChange = { id = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(Res.string.username_label)) },
                placeholder = { Text(stringResource(Res.string.username_placeholder)) },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.End,
                    color = colors.textPrimary
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = colors.border,
                    focusedLabelColor = primaryColor,
                    unfocusedLabelColor = colors.textSecondary,
                    focusedContainerColor = colors.surface,
                    unfocusedContainerColor = colors.surface,
                    cursorColor = primaryColor
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(Res.string.password_label)) },
                placeholder = { Text(stringResource(Res.string.password_placeholder)) },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.End,
                    color = colors.textPrimary
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null,
                            tint = colors.textSecondary
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = colors.border,
                    focusedLabelColor = primaryColor,
                    unfocusedLabelColor = colors.textSecondary,
                    focusedContainerColor = colors.surface,
                    unfocusedContainerColor = colors.surface,
                    cursorColor = primaryColor
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(Res.string.keep_logged_in),
                    color = colors.textSecondary,
                    fontSize = 14.sp
                )
                Spacer(Modifier.width(8.dp))
                Checkbox(
                    checked = keepLoggedIn,
                    onCheckedChange = { keepLoggedIn = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = primaryColor,
                        uncheckedColor = colors.border,
                        checkmarkColor = Color.White
                    )
                )
            }

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    tint = primaryColor,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = stringResource(Res.string.app_lock_hint),
                    color = colors.textTertiary,
                    fontSize = 11.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f),
                    lineHeight = 14.sp
                )
            }

            if (errorMessage != null) {
                Spacer(Modifier.height(16.dp))
                Surface(
                    color = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                    )
                }
            }

            Spacer(Modifier.weight(1f))
            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    if (id.isEmpty() || password.isEmpty()) {
                        errorMessage = "الرجاء إدخال رقم الهوية وكلمة المرور"
                        return@Button
                    }
                    isLoading = true
                    errorMessage = null
                    scope.launch {
                        try {
                            val user = repository.login(id, password, selectedType)
                            navigator.replace(HomeScreen(user))
                        } catch (e: Exception) {
                            errorMessage = "بيانات الدخول غير صحيحة"
                        } finally {
                            isLoading = false
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        stringResource(Res.string.login),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            TextButton(onClick = { }) {
                Text(
                    stringResource(Res.string.forgot_password),
                    color = primaryColor,
                    fontSize = 14.sp
                )
            }
            
            Spacer(Modifier.height(24.dp))
        }
    }
}

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val textColor = Color.White
    val backgroundColor = Color(0xFF1E1E1E)
    val buttonColor = Color(0xFFFFC107)

    val textStyle = TextStyle(
        color = textColor,
        fontFamily = FontFamily.SansSerif,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        textAlign = TextAlign.Start
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .semantics { contentDescription = "Pantalla de inicio de sesión" },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Iniciar Sesión",
            style = textStyle
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario", style = textStyle) },
            singleLine = true,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Campo de texto para ingresar el usuario" },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                cursorColor = buttonColor,
                focusedBorderColor = buttonColor,
                unfocusedBorderColor = textColor,
                focusedLabelColor = buttonColor,
                unfocusedLabelColor = textColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", style = textStyle) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Campo de texto para ingresar la contraseña" },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                cursorColor = buttonColor,
                focusedBorderColor = buttonColor,
                unfocusedBorderColor = textColor,
                focusedLabelColor = buttonColor,
                unfocusedLabelColor = textColor
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* TODO: login */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .semantics { contentDescription = "Botón para iniciar sesión" }
        ) {
            Text(text = "Ingresar", style = textStyle)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = { /* TODO: navigate to register */ },
                modifier = Modifier.semantics { contentDescription = "Botón para registrarse" }
            ) {
                Text("Registrarse", color = buttonColor, style = textStyle)
            }
            TextButton(
                onClick = { /* TODO: navigate to recover */ },
                modifier = Modifier.semantics { contentDescription = "Botón para recuperar contraseña" }
            ) {
                Text("Recuperar contraseña", color = buttonColor, style = textStyle)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        LoginScreen(navController = navController)
    }
}
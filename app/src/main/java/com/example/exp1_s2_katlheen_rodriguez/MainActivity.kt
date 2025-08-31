package com.example.exp1_s2_katlheen_rodriguez

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

val ArialFont = FontFamily(Font(R.font.arial))
//Clase para usuarios
data class User(val username: String, val password: String)


//Colores accesibles para daltónicos
val backgroundColor = Color(0xFF1E1E1E)
val textColor = Color.White
val buttonLoginColor = Color(0xFFFFB74D)
val buttonRegisterColor = Color(0xFF81C784)
val buttonRecoverColor = Color(0xFFFF8A65)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    val navController = rememberNavController()
    val users = remember { mutableStateListOf<User>() }

    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, users) }
        composable("register") { RegisterScreen(navController, users) }
        composable("recover") { RecoverPasswordScreen(navController, users) }
    }
}

//Login
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, users: MutableList<User>) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val textStyle = TextStyle(color = textColor, fontFamily = ArialFont, fontSize = 14.sp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
            .semantics { contentDescription = "Pantalla de inicio de sesión" },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "Logo de ChromaAssist",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "ChromaAssist – Iniciar Sesión",
            style = textStyle.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario", style = textStyle) },
            singleLine = true,
            textStyle = textStyle,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                cursorColor = buttonLoginColor,
                focusedBorderColor = buttonLoginColor,
                unfocusedBorderColor = textColor,
                focusedLabelColor = buttonLoginColor,
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
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                cursorColor = buttonLoginColor,
                focusedBorderColor = buttonLoginColor,
                unfocusedBorderColor = textColor,
                focusedLabelColor = buttonLoginColor,
                unfocusedLabelColor = textColor
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val user = users.find { it.username == username }
                if (user != null && user.password == password) {
                    Toast.makeText(context, "Login OK", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonLoginColor, contentColor = Color.Black),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.login), contentDescription = "Icono login", modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ingresar", style = textStyle)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp) // deja espacio uniforme
        ) {
            Button(
                onClick = { navController.navigate("register") },
                colors = ButtonDefaults.buttonColors(containerColor = buttonRegisterColor, contentColor = Color.Black),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f) // <- hace que ocupe mismo ancho que el otro
                    .height(60.dp) // <- altura uniforme
            ) {
                Image(
                    painter = painterResource(id = R.drawable.register),
                    contentDescription = "Icono registrar",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Registrarse", style = textStyle)
            }

            Button(
                onClick = { navController.navigate("recover") },
                colors = ButtonDefaults.buttonColors(containerColor = buttonRecoverColor, contentColor = Color.Black),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f) // <- mismo ancho que el de "Registrarse"
                    .height(60.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.reset),
                    contentDescription = "Icono recuperar",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Recuperar", style = textStyle, maxLines = 1) // texto más corto para que no se corte
            }
        }
    }
}
//Registro
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController, users: MutableList<User>) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val textStyle = TextStyle(color = textColor, fontFamily = ArialFont, fontSize = 14.sp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
            .semantics { contentDescription = "Pantalla de registro" },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "Logo de ChromaAssist",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "ChromaAssist – Registro",
            style = textStyle.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario", style = textStyle) },
            singleLine = true,
            textStyle = textStyle,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                cursorColor = buttonRegisterColor,
                focusedBorderColor = buttonRegisterColor,
                unfocusedBorderColor = textColor,
                focusedLabelColor = buttonRegisterColor,
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
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                cursorColor = buttonRegisterColor,
                focusedBorderColor = buttonRegisterColor,
                unfocusedBorderColor = textColor,
                focusedLabelColor = buttonRegisterColor,
                unfocusedLabelColor = textColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Contraseña", style = textStyle) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            textStyle = textStyle,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                cursorColor = buttonRegisterColor,
                focusedBorderColor = buttonRegisterColor,
                unfocusedBorderColor = textColor,
                focusedLabelColor = buttonRegisterColor,
                unfocusedLabelColor = textColor
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                when {
                    username.isBlank() || password.isBlank() -> Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                    password != confirmPassword -> Toast.makeText(context, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    users.any { it.username == username } -> Toast.makeText(context, "Usuario ya registrado", Toast.LENGTH_SHORT).show()
                    users.size >= 5 -> Toast.makeText(context, "Máximo 5 usuarios permitidos", Toast.LENGTH_SHORT).show()
                    else -> {
                        users.add(User(username, password))
                        Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()
                        navController.navigate("login")
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonRegisterColor, contentColor = Color.Black),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().height(60.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.register), contentDescription = "Icono registrar", modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Registrar", style = textStyle)
        }
    }
}

//Recuperación de Clave
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoverPasswordScreen(navController: NavHostController, users: MutableList<User>) {
    var username by remember { mutableStateOf("") }
    val context = LocalContext.current
    val textStyle = TextStyle(color = textColor, fontFamily = ArialFont, fontSize = 14.sp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
            .semantics { contentDescription = "Pantalla recuperar contraseña" },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "Logo de ChromaAssist",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "ChromaAssist – Recuperar Contraseña",
            style = textStyle.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario", style = textStyle) },
            singleLine = true,
            textStyle = textStyle,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                cursorColor = buttonRecoverColor,
                focusedBorderColor = buttonRecoverColor,
                unfocusedBorderColor = textColor,
                focusedLabelColor = buttonRecoverColor,
                unfocusedLabelColor = textColor
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val user = users.find { it.username == username }
                if (user != null) Toast.makeText(context, "Contraseña: ${user.password}", Toast.LENGTH_LONG).show()
                else Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonRecoverColor, contentColor = Color.Black),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().height(60.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.reset), contentDescription = "Icono recuperar", modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Enviar", style = textStyle)
        }
    }
}

//Vistas Previas
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    val users = remember { mutableStateListOf<User>() }
    LoginScreen(navController, users)
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    val users = remember { mutableStateListOf<User>() }
    RegisterScreen(navController, users)
}

@Preview(showBackground = true)
@Composable
fun RecoverPasswordScreenPreview() {
    val navController = rememberNavController()
    val users = remember { mutableStateListOf<User>() }
    RecoverPasswordScreen(navController, users)
}
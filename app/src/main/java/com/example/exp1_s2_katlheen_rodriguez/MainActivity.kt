package com.example.exp1_s2_katlheen_rodriguez

//Librerías
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exp1_s2_katlheen_rodriguez.ui.theme.Exp1S2KatlheenRodriguezTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Definir la UI usando Jetpack Compose
        setContent {
            Exp1S2KatlheenRodriguezTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(), //Ocupa toda la pantalla
                    color = MaterialTheme.colorScheme.background //Color de fondo del tema
                ) {
                    //Llamamos a la función de navegación principal
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    //NavController para manejar la navegación entre pantallas
    val navController = rememberNavController()

    NavHost(
        navController = navController, //Controlador de navegación
        startDestination = "login" //Pantalla inicial
    ) {
        //Definición de rutaa y sus pantallas correspondientes
        composable("login") { LoginScreen(navController) }       //Login
        composable("register") { RegisterScreen(navController) } //Registro
        composable("recover") { RecoverScreen(navController) }   //Recuperar contraseña
    }
}

//Login
@Composable
fun LoginScreen(navController: NavHostController) {
    Text("Login Screen")
}

//Registro
@Composable
fun RegisterScreen(navController: NavHostController) {
    Text("Register Screen")
}

//Recuperar contraseña
@Composable
fun RecoverScreen(navController: NavHostController) {
    Text("Recover Screen")
}
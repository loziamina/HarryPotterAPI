package com.amonteiro.a01_supvincia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amonteiro.a01_supvincia.model.MainViewModel
import com.amonteiro.a01_supvincia.ui.Routes
import com.amonteiro.a01_supvincia.ui.screen.DetailScreen
import com.amonteiro.a01_supvincia.ui.screen.HarryPotterCharactersScreen
import com.amonteiro.a01_supvincia.ui.screen.SearchScreen
import com.amonteiro.a01_supvincia.ui.theme._01_SupVinciATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _01_SupVinciATheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation(){

    val navController : NavHostController = rememberNavController()
    val viewModel : MainViewModel = viewModel()


    NavHost(navController = navController,
        startDestination = Routes.HarryPotterCharactersScreen.route
    ) {

        composable(Routes.SearchScreen.route) {
            SearchScreen(navController, viewModel)
        }

        composable(Routes.DetailScreen.route,
            arguments = listOf(
                navArgument("data"){ type = NavType.IntType}
            )
        ) {
            val position = it.arguments?.getInt("data", 0) ?: 0
            DetailScreen(position, navController = navController, viewModel)
        }

        composable(Routes.HarryPotterCharactersScreen.route) {
            HarryPotterCharactersScreen(navController, viewModel)
        }

        composable(Routes.HarryPotterScreenDetail.route,
            arguments = listOf(
                navArgument("data"){ type = NavType.IntType}
            )
        ) {
            val position = it.arguments?.getInt("data", 0) ?: 0
            DetailScreen(position, navController = navController, viewModel)
        }

    }

}
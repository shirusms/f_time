package com.shirusms.secure_facetime2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shirusms.secure_facetime2.model.AuthViewModel
import com.shirusms.secure_facetime2.ui.theme.screens.call.CallScreen
import com.shirusms.secure_facetime2.ui.theme.screens.contactlist.ContactListScreen
import com.shirusms.secure_facetime2.ui.theme.screens.home.HomeScreen
import com.shirusms.secure_facetime2.ui.theme.screens.register.RegisterScreen
import com.shirusms.secure_facetime2.ui.theme.screens.login.LoginScreen
import com.shirusms.secure_facetime2.ui.theme.screens.splash.SplashScreen
import com.shirusms.secure_facetime2.ui.theme.screens.startnewcall.StartNewCallScreen


@Composable
fun AppNavHost(modifier: Modifier=Modifier,navController:NavHostController= rememberNavController(),startDestination:String= ROUTE_SPLASH) {

    NavHost(navController = navController, modifier=modifier, startDestination = startDestination ){
        composable (ROUTE_SPLASH){
            SplashScreen(navController)
        }


        composable(ROUTE_LOGIN){
            val authViewModel: AuthViewModel = viewModel()
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(ROUTE_HOME) {
                        popUpTo(ROUTE_LOGIN) { inclusive = true }
                    }
                })

        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }

        composable(ROUTE_HOME){
            HomeScreen(navController)
        }
        composable(ROUTE_STARTNEWCALL) {
            StartNewCallScreen(navController)
        }
        composable(ROUTE_CONTACTLIST) {
            ContactListScreen(navController)
        }

        composable(ROUTE_CALL) {
            CallScreen(navController)
        }

    }

}

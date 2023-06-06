package cz.vratislavjindra.nba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import cz.vratislavjindra.nba.common.design.system.compose.theme.NbaTheme
import cz.vratislavjindra.nba.common.navigation.data.NbaNavigationActions
import cz.vratislavjindra.nba.common.navigation.device.NbaNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            NbaTheme {
                val navController = rememberNavController()
                val navigationActions = remember(Unit) {
                    NbaNavigationActions(navController = navController)
                }
                NbaNavGraph(
                    navController = navController,
                    navigationActions = navigationActions,
                )
            }
        }
    }
}

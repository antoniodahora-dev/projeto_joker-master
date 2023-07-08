package co.tiagoaguiar.tutorial.jokerappdev.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import co.tiagoaguiar.tutorial.jokerappdev.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

  private lateinit var appBarConfiguration: AppBarConfiguration

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    //irá iniciar a tela com logo antes da  tela principal
    installSplashScreen()

    setContentView(R.layout.activity_main)

    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)


    val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
    val navView = findViewById<NavigationView>(R.id.nav_view)

    //sincroniza a toolbar com o host do fragment
    val navController = findNavController(R.id.nav_host_fragment_content_main)


    appBarConfiguration = AppBarConfiguration(
      setOf(
          R.id.nav_home, R.id.nav_joke_day, R.id.nav_about
      ), drawerLayout
    )

    //sincronizar tudo na navegacao do menus
    setupActionBarWithNavController(navController, appBarConfiguration)

    navView.setupWithNavController(navController)
  }

  //evento click do menu do app
  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }
}
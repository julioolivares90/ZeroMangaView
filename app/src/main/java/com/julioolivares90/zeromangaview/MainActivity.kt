package com.julioolivares90.zeromangaview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.julioolivares90.zeromangaview.domain.useCases.GetMangasPopulares
import com.julioolivares90.zeromangaview.ui.theme.ZeroMangaViewTheme
import com.julioolivares90.zeromangaview.ui.theme.pages.homePage.HomePage
import com.julioolivares90.zeromangaview.ui.theme.pages.homePage.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var homePageViewModel : HomePageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            ZeroMangaViewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    HomePage(viewModel = homePageViewModel)
                }
            }
        }
    }
}


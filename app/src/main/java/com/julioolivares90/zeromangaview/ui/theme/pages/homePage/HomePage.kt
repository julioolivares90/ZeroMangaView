package com.julioolivares90.zeromangaview.ui.theme.pages.homePage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.julioolivares90.zeromangaview.R
import com.julioolivares90.zeromangaview.data.remote.models.Manga
import com.julioolivares90.zeromangaview.navigation.Screen
import com.julioolivares90.zeromangaview.ui.theme.Backgroundcoloramarillo
import com.julioolivares90.zeromangaview.ui.theme.Backgroundcolorrojo
import com.julioolivares90.zeromangaview.ui.theme.ColorNegroTransparente
import com.julioolivares90.zeromangaview.utilidades.getTextFormated

@Composable
fun HomePage (viewModel: HomePageViewModel,navHostController: NavHostController){

    Column {
        layoutMainPage(viewModel,navHostController)
    }
}

@Composable
fun layoutMainPage(viewModel: HomePageViewModel, navHostController: NavHostController){
    val mangaData = viewModel.state.value

    ListManga(mangaList = mangaData.mangasPopulares, onClick = {navHostController.navigate(Screen.DetailPage.route)})
}

@Composable
fun ListManga(mangaList : MutableList<Manga>,onClick: () -> Unit){
    val scrollState = rememberLazyGridState()
    
     Column {
         if (mangaList.isNotEmpty()){
             LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                 items(mangaList.toList()){ manga->
                     MangaItem(manga = manga, onClick = onClick)
                 }
             }, userScrollEnabled = true, state = scrollState)
         }
         else {
             Text(text = "No hay datos para mostrar", modifier = Modifier.align(Alignment.CenterHorizontally))
         }
         
     }
}

@Composable
fun MangaItem(manga: Manga,onClick : () -> Unit ){
    Card(modifier = Modifier
        .width(300.dp)
        .height(450.dp)
        .padding(10.dp)
        .clickable { onClick() }, shape = MaterialTheme.shapes.medium) {
        Box(contentAlignment =  Alignment.Center, modifier = Modifier
            .width(300.dp)
            .height(450.dp)){
            ConstraintLayout(Modifier.fillMaxSize()) {
                val (imagen,titulo,demografia) = createRefs()

                AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(manga.mangaImagen)
                    .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .width(300.dp)
                        .height(450.dp).constrainAs(imagen) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                      ,
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background)
                )

                Text(text = getTextFormated(manga.title), modifier = Modifier
                    .fillMaxWidth()
                    .background(color = ColorNegroTransparente).constrainAs(titulo){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    , color = Color.White, textAlign = TextAlign.Center)

                //Spacer(modifier = Modifier.width(5.dp))
                if (manga.demography == "Shounen"){
                    TextDemography(value = manga.demography, color = Backgroundcoloramarillo, modifier = Modifier.constrainAs(demografia){
                        bottom.linkTo(parent.bottom)
                        end.linkTo(imagen.end)
                        start.linkTo(imagen.start)
                    })
                }else {
                    TextDemography(value = manga.demography, color = Backgroundcolorrojo,modifier = Modifier.constrainAs(demografia){
                        bottom.linkTo(parent.bottom)
                        end.linkTo(imagen.end)
                        start.linkTo(imagen.start)

                    })
                }
            }
        }
    }
}

@Composable
fun TextDemography(value : String,color : Color,modifier: Modifier){
    Text(text = value, modifier = modifier
        .background(color = color)
        .fillMaxWidth(), Color.White, textAlign = TextAlign.Center, fontSize = 16.sp)
}
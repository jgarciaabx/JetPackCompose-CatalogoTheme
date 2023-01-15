package com.example.catalogotheme

import android.content.ClipData.Item
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*

import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catalogotheme.model.Superhero
import kotlinx.coroutines.launch


@Composable
fun SimpleRecyclerView() {

    val myList = listOf("Juan", "Pepe", "Carlos", "Cesar")

    LazyColumn {
        item { Text(text = "Header") }

        items(myList) {
            Text(text = "Hola me llamo $it")
        }
        item { Text(text = "Footer") }
    }
}

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperHero()) { superhero ->
            ItemHero(superhero = superhero)
            { Toast.makeText(context, it.superheroname, Toast.LENGTH_SHORT).show() }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView() {
    val context = LocalContext.current
    val superhero: Map<String, List<Superhero>> = getSuperHero().groupBy { it.publisher }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        superhero.forEach { (publisher, mySuperHero) ->

            stickyHeader {
                Text(
                    text = publisher,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            items(mySuperHero) { superhero ->
                ItemHero(superhero = superhero)
                { Toast.makeText(context, it.superheroname, Toast.LENGTH_SHORT).show() }
            }
        }
    }
}


@Composable
fun SuperHeroWitchSpecialControlsView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column() {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperHero()) { superhero ->
                ItemHero(superhero = superhero)
                { Toast.makeText(context, it.superheroname, Toast.LENGTH_SHORT).show() }
            }
        }


        val showbutton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }

        rvState.firstVisibleItemScrollOffset

        if (showbutton) {
            Button(
                onClick = {
                    coroutineScope.launch { rvState.animateScrollToItem(0) }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(text = "Soy un Botoncito !! ")
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    LazyVerticalGrid(cells = GridCells.Fixed(2), content = {
        items(getSuperHero()) { superhero ->
            ItemHero(superhero = superhero)
            { Toast.makeText(context, it.superheroname, Toast.LENGTH_SHORT).show() }
        }
    })

}

@Composable
fun ItemHero(superhero: Superhero, onItemSelected: (Superhero) -> Unit) {
    Card(border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(superhero) }
            .padding(top = 8.dp, bottom = 8.dp, end = 8.dp, start = 8.dp)) {
        Column {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "SuperHero Avatar", modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.superheroname,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.realname,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.publisher,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.End)
            )
        }

    }
}


fun getSuperHero(): List<Superhero> {
    return listOf(
        Superhero("America", "olivia", "Marvel", R.drawable.america),
        Superhero("thanos Golpe", "Thanitos pa los Compas", "Marvel", R.drawable.thanosfuego),
        Superhero("Thanos Puñetas", "Thaños >:v", "Marvel", R.drawable.thanosgolpe),
        Superhero("Avatar", "Yo", "Marvel", R.drawable.avatar),
        Superhero("America", "olivia", "DC", R.drawable.america),
        Superhero("thanos Golpe", "Thanitos pa los Compas", "DC", R.drawable.thanosfuego),
        Superhero("Thanos Puñetas", "Thaños >:v", "Comic", R.drawable.thanosgolpe),
        Superhero("Avatar", "Yo", "Comic", R.drawable.avatar)

    )
}
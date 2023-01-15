package com.example.catalogotheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.catalogotheme.model.Routes
import com.example.catalogotheme.ui.CheckInfo
import com.example.catalogotheme.ui.theme.CatalogoThemeTheme

class MainActivity : ComponentActivity() {
    @ExperimentalComposeApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatalogoThemeTheme {
                // A surface container using the 'background' color from the theme

                var selected by remember {
                    mutableStateOf("Juan")
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Pantalla1.route
                    ) {

                        composable(Routes.Pantalla1.route) { Screen1(navigationController) }
                        composable(Routes.Pantalla2.route) { Screen2(navigationController) }
                        composable(Routes.Pantalla3.route) { Screen3(navigationController) }
                        composable(
                            Routes.Pantalla4.route,
                            arguments = listOf(navArgument("age") { type = NavType.IntType })
                        ) { backStackEntry ->
                            Screen4(
                                navigationController,
                                backStackEntry.arguments!!.getInt("age")
                            )
                        }
                        composable(
                            Routes.Pantalla5.route,
                            arguments = listOf(navArgument("name", { defaultValue = "Pepe" }))
                        ) { backStackEntry ->
                            Screen5(
                                navigationController,
                                backStackEntry.arguments?.getString("name")
                            )
                        }
                    }

                }
            }
        }
    }
}


//                    var show by remember {
//                        mutableStateOf(false)
//                    }
//                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//                        Button(onClick = { show = true }) {
//                            Text(text = "MOSTRAR DIALOGO")
//                        }
//                        MyConfirmationDialog(
//                            show = show,
//                            onDismiss = { show = false },
//                        )
////                        MySimpleCustomDialog(
////                            show = show,
////                            onDismiss = { show = false },
//////                            onConfirm = { Log.i("Juan", "click") }
////                        )
//                    }
//                    MyRangeSlider()
//                    AdvanceSlider()
//                    BasicSlider()
//                    MyDropDowMenu()
//                    val myOptions = getOptions(listOf("Juan", "Ejemplo", "Pikachu"))
//                    Column {
//                        MyDivider()
//                        MyBadgeBox()
//                        MyCard()
//                        MyRadioButtonList(selected) { selected = it }
//                        MyRadioButton()
//                        MyTrisStatusCheckBox()
//                        myOptions.forEach {
//                            MyCheckBoxWithTextCompleted(it)
//                        }
//                        MyCheckBoxWithText()
//                    }


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CatalogoThemeTheme {
        MyDivider()
    }
}


@Composable
fun MyDropDowMenu() {
    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val desserts = listOf("Helado", "Chocolate", "Cafe", "Fresa")

    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { desserts ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    selectedText = desserts
                }) {
                    Text(text = desserts)
                }
            }
        }
    }
}


@Composable
fun MyDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp), color = Color.Red
    )
}


@Composable
fun MyBadgeBox() {
    BadgedBox(
        badge = { Badge { Text("100") } },
        modifier = Modifier
            .background(Color.Red)
            .padding(16.dp)
    ) {

        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Favorite"
        )
    }

}


@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.Red,
        contentColor = Color.Green,
        border = BorderStroke(5.dp, Color.Green)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Ejemplo 1")
            Text(text = "Ejemplo 2")
            Text(text = "Ejemplo 3")
        }

    }
}


@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {


    Column(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = name == "Juan", onClick = { onItemSelected("Juan") }
            )
            Text(text = "Juan ")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = name == "Aris", onClick = { onItemSelected("Aris") }
            )
            Text(text = "Aris")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = name == "David", onClick = { onItemSelected("David") }
            )
            Text(text = "David")
        }

    }

}

@Composable
fun MyRadioButtonListDialog(name: String, onItemSelected: (String) -> Unit) {


    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = name == "Juan", onClick = { onItemSelected("Juan") }
            )
            Text(text = "Juan ")
        }
        Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = name == "Aris", onClick = { onItemSelected("Aris") }
            )
            Text(text = "Aris")
        }
        Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = name == "David", onClick = { onItemSelected("David") }
            )
            Text(text = "David")
        }

    }

}


@Composable
fun MyRadioButton() {

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        RadioButton(
            selected = false, onClick = { }, enabled = false, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledColor = Color.Green
            )
        )
        Text(text = "EJEMPLO 1 ")
    }

}


@Composable
fun MyTrisStatusCheckBox() {
    var status by rememberSaveable { mutableStateOf(ToggleableState.Off) }
    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> {
                ToggleableState.Off
                //todo se puede aumentar para cualquier funcion a los 3 estados
            }
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}


@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { myNuevoEstado -> status = myNuevoEstado }
        )
    }

}


@Composable
fun MyCheckBoxWithTextCompleted(checkInfo: CheckInfo) {

    /** como anotacion tener en cuenta el verticalAlignment
    para que este recto los check con textos (Juan)
     **/
    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title)

    }

//        }

//    Column(Modifier.height(30.dp)) {

//        Row(Modifier.padding(8.dp)) {
//            Text("HOLA MUNDOI")
//            Spacer(modifier = Modifier.width(15.dp))
//            Text("SOY JUAN ")
//            Spacer(modifier = Modifier.width(15.dp))
//            Checkbox(
//                checked = checkInfo.selected,
//                onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
//        }
//
//        Row(
//            modifier = Modifier.height(48.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            Checkbox(
//                checked = true,
//                enabled = true,
//                onCheckedChange = {checkInfo.onCheckedChange(!checkInfo.selected)}
//            )
//            Spacer(Modifier.width(32.dp))
//            Text("hola mundo")
//        }


}


@Composable
fun MyCheckBoxWithText() {
    var state by rememberSaveable { mutableStateOf(false) }

    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ejemplo 1 ")
    }

}


@Composable
fun MyCheckBox() {
    var state by rememberSaveable { mutableStateOf(false) }
    Checkbox(
        checked = state, onCheckedChange = { state = !state }, enabled = false,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Yellow,
            checkmarkColor = Color.Blue
        )
    )
}


@Composable
fun MySwitch() {
    var state by rememberSaveable { mutableStateOf(true) }
    Switch(
        checked = state, onCheckedChange = { state = !state },
        enabled = true,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            checkedThumbColor = Color.Green,
        )
    )
}

@Composable
fun MyProfressAdvance() {
    var profressStatus by rememberSaveable {
        mutableStateOf(0f)
    }
    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(50.dp))
        LinearProgressIndicator(progress = profressStatus)
        Spacer(modifier = Modifier.height(10.dp))
        CircularProgressIndicator(progress = profressStatus)
        Row(Modifier.fillMaxSize()) {
            Button(onClick = { profressStatus += 0.1f }) {
                Text(text = "Incrementar")
            }
            Button(onClick = { profressStatus -= 0.1f }) {
                Text(text = "Reducir")
            }
        }
    }
}

@Composable
fun MyProgress() {
    var showLoading by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red, strokeWidth = 10.dp)
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                backgroundColor = Color.Green
            )
        }
        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Cargar Perfil")
        }

    }
}

@Composable
fun MyIcon() {
    Icon(
        imageVector = Icons.Rounded.Star,
        contentDescription = "Icono",
        tint = Color.Red
    )
}


@Composable
fun MyImageAdvance() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape)
    )
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        alpha = 0.5f
    )
}


@Composable
fun MyButtonExample() {
    var enabled by rememberSaveable {
        mutableStateOf(true)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { enabled = false },
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta,
                contentColor = Color.Blue
            ),
            border = BorderStroke(5.dp, Color.Green)
        )
        {
            Text(text = "Hola")
        }

        OutlinedButton(
            onClick = { enabled = false },
            enabled = enabled,
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta,
                contentColor = Color.Blue,
                disabledBackgroundColor = Color.Green,
                disabledContentColor = Color.Red

            )
        )
        {
            Text(text = "Hola")

        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Hola")
        }
    }
}


@Composable
fun MyTextFieldState(name: String, onchangevalue: (String) -> Unit) {
    TextField(value = name, onValueChange = { onchangevalue(it) })

}

@Composable
fun MyTextFieldOutline() {
    var myText by remember { mutableStateOf("") }
    OutlinedTextField(
        value = myText, onValueChange = { myText = it },
        modifier = Modifier.padding(24.dp),
        label = { Text(text = "Holita") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Blue
        )
    )
}


@Composable
fun MyTextFieldAdvance() {
    var myText by remember { mutableStateOf("") }
    TextField(value = myText, onValueChange = {
        myText =
            if (it.contains("a")) {
                it.replace("a", "")
            } else {
                it
            }
    },
        label = { Text(text = "Introduce tu Nombre") })
}

@Composable
fun MyTextField() {
    var myText by remember { mutableStateOf("") }
    TextField(value = myText, onValueChange = { myText = it })
}

@Composable
fun MyText() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Ejemplo1")
        Text(text = "EjemploColor", color = Color.Blue)
        Text(text = "Ejemplo1", fontWeight = FontWeight.ExtraLight)
        Text(text = "Ejemplo1", fontWeight = FontWeight.Light)
        Text(
            text = "Ejemplo1",
            style = androidx.compose.ui.text.TextStyle(fontFamily = FontFamily.Cursive)
        )
        Text(
            text = "Ejemplo1",
            style = androidx.compose.ui.text.TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Ejemplo1",
            style = androidx.compose.ui.text.TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "Ejmplo1", style = androidx.compose.ui.text.TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.Underline, TextDecoration.LineThrough)
                )
            )
        )
        Text(text = "Ejemplo Gigante", fontSize = 30.sp)
    }
}

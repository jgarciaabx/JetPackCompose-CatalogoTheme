package com.example.catalogotheme


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun MyConfirmationDialog(
    show: Boolean, onDismiss: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                MyTitleDialog(text = "Phone Ringtone", modifier = Modifier.padding(24.dp))
                Divider(Modifier.fillMaxWidth(), Color.Black)
                var status by remember { mutableStateOf("") }
                MyRadioButtonListDialog(status) { status = it }
                Divider(Modifier.fillMaxWidth(), Color.Black, thickness = 13.dp)
                Row(
                    Modifier
                        .align(Alignment.End)
                        .padding(8.dp)
                ) {
                    TextButton(onClick = { }) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = { }) {
                        Text(text = "Ok")
                    }
                }
            }
        }

    }
}

@Composable
fun MyCustomDialog(
    show: Boolean, onDismiss: () -> Unit
) {
    if (show) {

        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleDialog("Set Back account")
                AccountItem("ejemplo1@gmail.com", R.drawable.avatar)
                AccountItem("ejemplo2@gmail.com", R.drawable.avatar)
                AccountItem("Añadir Nueva Cuenta", R.drawable.add)
            }
        }
    }

}


@Composable
fun MySimpleCustomDialog(
    show: Boolean, onDismiss: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = {}, properties = DialogProperties(dismissOnBackPress = false)) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Ejemplo")
                Text(text = "Ejemplo")
                Text(text = "Ejemplo")
            }
        }
    }
}


@Composable
fun MyAlertDailog(
    show: Boolean, onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(onDismissRequest = { onDismiss() },
            title = { Text(text = "Titulo") },
            text = { Text(text = "Hola soy una Descripcion xD") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "ConfirmButton")
                }
            },
            dismissButton = {
                TextButton(onClick = { }) {
                    Text(text = "DismissButton")
                }
            })

    }
}


@Composable
fun AccountItem(email: String, @DrawableRes drawable: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {

//        Image(
//            painterResource(R.drawable.ic_launcher_background),
//            contentDescription = "",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )

        Image(
            painter = painterResource(id = drawable), contentDescription = "",
            contentScale = ContentScale.Crop, modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(
                    CircleShape
                )
        )
        Text(text = email, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(8.dp))

    }
}

@Composable
fun MyTitleDialog(text: String, modifier: Modifier = Modifier.padding(bottom = 12.dp)) {
    Text(
        text = text, fontWeight = FontWeight.SemiBold, fontSize = 20.sp,
        modifier = modifier
    )
}
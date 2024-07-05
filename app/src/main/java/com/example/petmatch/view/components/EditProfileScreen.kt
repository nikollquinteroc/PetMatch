@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.petmatch.view.components


import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petmatch.R

@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    initialValue: String
) {
    val textValue = remember { mutableStateOf(initialValue) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue, color = MaterialTheme.colorScheme.primary) },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
        leadingIcon = {
            Icon(
                painter = painterResource,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    )
}

@Composable
fun CircularImagePicker() {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var isPressed by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val launcher = rememberLauncher { uri ->
        if (uri != null) {
            selectedImageUri = uri
            isPressed = true
        }
    }
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Surface(
            modifier = Modifier
                .padding(top = 32.dp)
                .clip(CircleShape)
                .clickable {
                    launcher.launch("image/*")
                }
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        ) {
            imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            } ?: Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
        }

        if (isPressed) {
            AlertDialog(
                onDismissRequest = {
                    isPressed = false
                    selectedImageUri = null
                },
                title = {
                    Text("¿Aceptar el cambio de imagen?", fontSize = 20.sp)
                },
                confirmButton = {
                    Button(
                        onClick = {
                            selectedImageUri?.let { uri ->
                                loadImage(uri, context) { loadedBitmap ->
                                    imageBitmap = loadedBitmap
                                }
                            }
                            isPressed = false
                            selectedImageUri = null
                        }
                    ) {
                        Text("Aceptar")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            isPressed = false
                            selectedImageUri = null
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

fun loadImage(uri: Uri?, context: Context, onLoaded: (ImageBitmap?) -> Unit) {
    if (uri != null) {
        val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        onLoaded(bitmap.asImageBitmap())
    } else {
        onLoaded(null)
    }
}

@Composable
fun rememberLauncher(onResult: (Uri?) -> Unit): ManagedActivityResultLauncher<String, Uri?> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        onResult(uri)
    }
}

@Composable
fun GenderEditDropdown() {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var role by remember {
        mutableStateOf("Female")
    }

    var rolPorDefecto by remember {
        mutableStateOf("Female")
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
    ) {
        TextField(
            value = role,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.gender),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),

            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = "Male")
                },
                onClick = {
                    role = "Male"
                    rolPorDefecto = "Male"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = "Female")
                },
                onClick = {
                    role = "Female"
                    rolPorDefecto = "Female"
                    isExpanded = false
                }
            )
        }
    }
}


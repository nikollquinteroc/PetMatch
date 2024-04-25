@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.petmatch.view.components

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.petmatch.R
import com.example.petmatch.view.ui_theme.theme.Primary
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.petmatch.model.data.ProfileViewModel
import com.example.petmatch.model.data.Users
import com.example.petmatch.view.ui_theme.theme.Secondary


@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    initialValue: String,
    onValueChange: (String) -> Unit,
    userState: Users
) {
    val textValue = remember { mutableStateOf(initialValue) }

    LaunchedEffect(userState) {
        textValue.value = when (labelValue) {
            "First Name" -> userState.firstName ?: ""
            "Last Name" -> userState.lastName ?: ""
            else -> initialValue
        }
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Primary,
            cursorColor = Primary
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onValueChange(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderEditDropdown(
    selectedGender: String,
    onGenderSelected: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var role by remember { mutableStateOf(selectedGender) }

    LaunchedEffect(selectedGender) {
        role = selectedGender
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
                Icon(painter = painterResource(id = R.drawable.gender), contentDescription = "")
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
                    onGenderSelected("Male")
                    isExpanded = false
                }
            ) 
            DropdownMenuItem(
                text = {
                    Text(text = "Female")
                },
                onClick = {
                    role = "Female"
                    onGenderSelected("Female")
                    isExpanded = false
                }
            )
        }
    }
}


@Composable
fun CircularImagePicker(
    imageUrl: String?,
    onImageSelected: (String) -> Unit,
    profileViewModel: ProfileViewModel
) {
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
                .border(2.dp, Color.Black, CircleShape)
        ) {
            if (imageUrl != null) {
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }
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
                                profileViewModel.uploadImageToStorage(uri) { downloadUri ->
                                    onImageSelected(downloadUri)
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

@Composable
fun rememberLauncher(onResult: (Uri?) -> Unit): ManagedActivityResultLauncher<String, Uri?> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        onResult(uri)
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
fun btnUpdate(
    value: String,
    onUpdateBtn: () -> Unit,
    isEnabled: Boolean = false,
    updateSuccess: Boolean = false
) {
    val showDialog = remember { mutableStateOf(updateSuccess) }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onUpdateBtn.invoke()
            showDialog.value = true
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                )
                .clickable { }
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },
            title = {
                Text("¡Actualización exitosa!")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog.value = false
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}

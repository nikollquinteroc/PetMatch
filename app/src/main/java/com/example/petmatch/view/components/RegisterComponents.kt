@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.petmatch.view.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petmatch.R
import com.example.petmatch.ui.theme.Primary
import com.example.petmatch.ui.theme.Secondary
import com.example.petmatch.ui.theme.TextColor
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource


@Composable
fun RegisterTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 20.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false,
    maxCharacters: Int = Int.MAX_VALUE
) {
    val textValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Primary,
            cursorColor = Primary,
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            if (it.length <= maxCharacters) {
                textValue.value = it
                onTextSelected(it)
            }
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )
}


@Composable
fun PasswordFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false,
    maxCharacters: Int = Int.MAX_VALUE ) {
    val localFocusManager = LocalFocusManager.current
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Primary,
            cursorColor = Primary,
        ),
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        singleLine = true,
        maxLines = 1,
        value = password.value,
        onValueChange = { newValue ->
            if (newValue.length <= maxCharacters) {
                password.value = newValue
                onTextSelected(newValue)
            }
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}

@Composable
fun CheckboxComponent(isChecked: MutableState<Boolean>) {
    Row(
        modifier = Modifier.fillMaxWidth().heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
            }
        )
        ClickableComponent()
    }
}



@Composable
fun ClickableComponent() {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Term of Use"

    val annotationString = buildAnnotatedString {
        append(initialText)

        withStyle(style = SpanStyle(color = Primary, textDecoration = TextDecoration.Underline)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }

        append(andText)

        withStyle(style = SpanStyle(color = Primary, textDecoration = TextDecoration.Underline)) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(
        text = annotationString,
        onClick = { offset ->
            annotationString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableComponent", "$span")
                }
        }
    )
}


@Composable
fun btnRegister(value: String , onRegisterBtn : () -> Unit, isEnabled: Boolean = false){
    Button(

        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onRegisterBtn.invoke()
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
                ),
            contentAlignment =  Alignment.Center
        ){
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DividerText(){
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically ){

        HorizontalDivider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Gray,
            thickness = 1.dp)

        Text(
            modifier = Modifier.padding(8.dp),
            text = "or",
            fontSize = 18.sp,
            color = TextColor)

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            thickness = 1.dp, color = Color.Gray
        )
    }
}

@Composable
fun ClickableLoginTextComponent(onTextSelected: (String) -> Unit){

    val initialText = "Already have an account? "
    val loginText =" Login"

    val annotationString = buildAnnotatedString {
        append(initialText)

        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }

    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotationString, onClick = {offset ->
            annotationString.getStringAnnotations(offset,offset)
                .firstOrNull()?.also { span->
                    Log.d("ClickableComponent","{${span.item}}")

                    if (span.item == loginText){
                        onTextSelected(span.item)
                    }
                }
        } )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderDropdown(
    onGenderSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {
    var isExpanded by remember { mutableStateOf(false) }
    var gender by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        TextField(
            value = gender,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.gender), contentDescription = "")
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            placeholder = { Text("Select Gender") },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                .clickable(onClick = { isExpanded = true }),
            isError = !errorStatus
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = "Male") },
                onClick = {
                    gender = "Male"
                    isExpanded = false
                    onGenderSelected(gender)
                }
            )
            DropdownMenuItem(
                text = { Text(text = "Female") },
                onClick = {
                    gender = "Female"
                    isExpanded = false
                    onGenderSelected(gender)
                }
            )
        }
    }
}

@Composable
fun RoleDropdown(
    onRoleSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {
    var isExpanded by remember { mutableStateOf(false) }
    var role by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        TextField(
            value = role,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.roles), contentDescription = "")
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            placeholder = { Text("Select Role") },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                .clickable(onClick = { isExpanded = true }),
            isError = !errorStatus
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = "Adopter") },
                onClick = {
                    role = "Adopter"
                    isExpanded = false
                    onRoleSelected(role)
                }
            )
            DropdownMenuItem(
                text = { Text(text = "Pet Responsible") },
                onClick = {
                    role = "Pet Responsible"
                    isExpanded = false
                    onRoleSelected(role)
                }
            )
        }
    }
}

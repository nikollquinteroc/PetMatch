package com.example.petmatch
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.petmatch.view.components.*
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

 
@Composable
fun DetailsUserScreen() {

    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            CircularImagePicker()

            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first),
                painterResource = painterResource(id = R.drawable.user),
                initialValue = "Steven"
            )
            Spacer(modifier = Modifier.height(10.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last),
                painterResource = painterResource(id = R.drawable.user),
                initialValue = "Vergara Garcia"
            )
            Spacer(modifier = Modifier.height(10.dp))
            GenderEditDropdown()


        }
    }
}
@Preview
@Composable
fun PreviewRDetailsUserScreen() {
    DetailsUserScreen()

}


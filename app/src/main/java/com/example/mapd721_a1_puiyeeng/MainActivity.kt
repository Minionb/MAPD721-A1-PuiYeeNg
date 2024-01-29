package com.example.mapd721_a1_puiyeeng

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mapd721_a1_puiyeeng.datastore.StoreUserInfo
import com.example.mapd721_a1_puiyeeng.ui.theme.MAPD721A1PuiYeeNgTheme
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAPD721A1PuiYeeNgTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    // context
    val context = LocalContext.current
    // scope
    val scope = rememberCoroutineScope()
    // datastore Email
    val dataStore = StoreUserInfo(context)

    // get saved email and password
    val savedUsernameState = dataStore.getUsername.collectAsState(initial = "")
    val savedPassState = dataStore.getPassword.collectAsState(initial = "")
    val savedIdState = dataStore.getId.collectAsState(initial = "")

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxSize()) {

        //email field
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username", color = Color.Gray, fontSize = 12.sp) },
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password", color = Color.Gray, fontSize = 12.sp) },
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "ID", color = Color.Gray, fontSize = 12.sp) },
        )

        Spacer(modifier = Modifier.height(20.dp))
        // save button
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFFFFA500)),
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(start = 16.dp, end = 16.dp),
                onClick = {
                    username = savedUsernameState.value ?: ""
                    password = savedPassState.value ?: ""
                    id = savedIdState.value ?: ""
                },
            )
            {
                // button text
                Text(
                    text = "Load",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFF006400)),
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(start = 16.dp, end = 16.dp),
                onClick = {
                    //launch the class in a coroutine scope
                    scope.launch {
                        dataStore.saveInfo(username, password, id)
                        username = ""
                        password = ""
                        id = ""
                    }
                },
            )
            {
                // button text
                Text(
                    text = "Save",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(start = 16.dp, end = 16.dp),
                onClick = {
                    //launch the class in a coroutine scope
                    scope.launch {
                        dataStore.clearInfo()
                    }
                    username = ""
                    password = ""
                    id = ""
                },
            )
            {
                // button text
                Text(
                    text = "Clear",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            Spacer(modifier = Modifier.fillMaxWidth())
        }
        // Box with two lines of text at the bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(16.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = "Pui Yee Ng",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "301366105",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MAPD721A1PuiYeeNgTheme {
        MainScreen()
    }
}
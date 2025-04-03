package com.example.viewmodelcounterapp

//import CompteurViewModel
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MonApp()
//        }
//    }
//}
//
//@Composable
//fun MonApp(viewModel: CompteurViewModel = viewModel()) {
//    val compteur by viewModel.compteur.collectAsState() // Observe state
//
//    Surface(modifier = Modifier.fillMaxSize()) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = "Compteur : $compteur", style = MaterialTheme.typography.titleLarge)
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(onClick = { viewModel.incrementer() }) {
//                Text("Incrémenter")
//            }
//        }
//    }
//}

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApp()
        }
    }
}

@Composable
fun TodoApp() {
    var titre by remember { mutableStateOf("Loading...") }

    // API Call using enqueue() (asynchronous but without coroutines)
    LaunchedEffect(Unit) {
        val call = RetrofitClient.api.getTodo()
        call.enqueue(object : Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                if (response.isSuccessful) {
                    val todo = response.body()
                    titre = todo?.title ?: "Empty response"
                } else {
                    titre = "HTTP Error ${response.code()}"
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                titre = "Network Error: ${t.message}"
            }
        })
    }

    // Display the fetched title
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = titre, style = MaterialTheme.typography.titleLarge)
        }
    }
}
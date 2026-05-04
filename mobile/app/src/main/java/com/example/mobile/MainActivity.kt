package com.example.mobile

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile.ViewModels.MyViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LaadData()
            }
        }
    }
}

@Composable
fun LaadData(modifier: Modifier = Modifier, viewModel: MyViewModel = viewModel()) {
    val data = viewModel.data.observeAsState().value

    if (data != null) {
        Text(
            text = data,
            modifier = modifier.padding(16.dp)
        )
    } else {
        Text(text = "Laden...", modifier = modifier.padding(16.dp))
    }
}

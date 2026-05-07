package com.example.mobile

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile.features.groups.ui.GroupsViewModel
import com.example.mobile.core.components.BotBar
import com.example.mobile.core.components.TopBar
import com.example.mobile.core.ui.theme.FairShareTheme
import com.example.mobile.features.groups.ui.Screen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FairShareTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                    state = rememberTopAppBarState()
                )
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    topBar = {
                        TopBar(
                            scrollBehavior = scrollBehavior
                        )
                    },
                    bottomBar = {
                        BotBar()
                    },
                    floatingActionButton = {}
                ) { paddingValues ->
                    Screen(
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

// https://www.youtube.com/watch?v=laL2lAts_Rc
@Composable
fun LaadData(modifier: Modifier = Modifier, viewModel: GroupsViewModel = viewModel()) {
    //val data = viewModel.data.observeAsState().value

    if ("data" != null) {
        Text(
            text = "data",
            modifier = modifier.padding(16.dp)
        )
    } else {
        Text(text = "Laden...", modifier = modifier.padding(16.dp))
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    FairShareTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            topBar = { TopBar(scrollBehavior = scrollBehavior) },
            bottomBar = { BotBar() }
        ) { paddingValues ->
            Screen(modifier = Modifier.padding(paddingValues))
        }
    }
}


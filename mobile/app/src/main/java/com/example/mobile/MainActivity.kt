package com.example.mobile

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile.features.groups.ui.GroupsViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                    state = rememberTopAppBarState()
                )
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar(
                            scrollBehavior = scrollBehavior
                        )
                    },
                    bottomBar = {},
                    floatingActionButton = {}
                ) { paddingValues ->
                    GroupScreen(
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


@Composable
fun GroupScreen(modifier: Modifier = Modifier) {

}


@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.6f)
        ),
        title = {
            Text(
                text = "Groups",
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                fontSize = 24.sp
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription= null,
                modifier = Modifier.padding(end = 12.dp,)
                    .size(32.dp)
            )
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription= null,
                modifier = Modifier.padding(end = 8.dp,)
                    .size(32.dp)
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GroupScreenPreview() {
    MaterialTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            topBar = { TopBar(scrollBehavior = scrollBehavior) }
        ) { paddingValues ->
            GroupScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}
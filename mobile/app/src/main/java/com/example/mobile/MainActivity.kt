package com.example.mobile

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Balance
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationItemColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.mobile.core.components.BotBar
import com.example.mobile.core.components.TopBar
import com.example.mobile.core.ui.theme.FairShareTheme
import com.example.mobile.features.groups.ui.GroupScreen

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




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    FairShareTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            topBar = { TopBar(scrollBehavior = scrollBehavior) },
            bottomBar = { BotBar() }
        ) { paddingValues ->
            GroupScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}


package com.example.mobile.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobile.core.ui.theme.FairShareTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = {
            Text(
                text = "Groups",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(32.dp)
            )
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(32.dp)
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun TopBarPreview() {
    FairShareTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            topBar = { TopBar(scrollBehavior = scrollBehavior) },
            bottomBar = {  }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) { }
        }
    }

}
package com.example.mobile.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Balance
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobile.core.ui.theme.FairShareTheme

data class BotNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun BotBar() {
    val items = listOf(
        BotNavItem(
            title = "Groups",
            selectedIcon = Icons.Outlined.Group,
            unselectedIcon = Icons.Filled.Group
        ),
        BotNavItem(
            title = "Balance",
            selectedIcon = Icons.Outlined.Balance,
            unselectedIcon = Icons.Filled.Balance
        ),
        BotNavItem(
            title = "Account",
            selectedIcon = Icons.Outlined.AccountBox,
            unselectedIcon = Icons.Filled.AccountBox
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(0.2f),
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                ),
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    // navController.navigate(item.title)
                },
                label = {
                    Text(
                        text = item.title
                    )
                },
                icon = {
                    BadgedBox(
                        badge = {}
                    ) {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            },
                            modifier = Modifier.size(28.dp),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BotBarPreview() {
    FairShareTheme {
        Scaffold(
            topBar = { },
            bottomBar = { BotBar() }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) { }
        }
    }

}


package com.example.mobile.features.groups.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.DirectionsTransit
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobile.core.ui.theme.AppButtonGreen
import com.example.mobile.core.ui.theme.AppDark
import com.example.mobile.core.ui.theme.AppTextGrey
import com.example.mobile.features.groups.domain.Group

@Preview(showBackground = true)
@Composable
fun GroupsItem(group: Group = Group(id = 1, name = "Fastfood MCDO", icon="Food",listOf("Bob", "James"))) {
    val iconMap = mapOf("Food" to Icons.Filled.Fastfood,
        "Travel" to Icons.Filled.AirplanemodeActive,
        "Drinks" to Icons.Filled.LocalDrink,
        "Transport" to Icons.Filled.DirectionsTransit)
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp).fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp)){
                ItemIcon(icon = iconMap[group.icon])
                Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                    Text(
                        text = group.name,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = if (group.users.size == 1) group.users.size.toString() + " person" else group.users.size.toString() + " people",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "YOU ARE OWED",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "$142.50",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}

@Composable
fun ItemIcon(icon: ImageVector? = Icons.Filled.NotInterested){
    Box(
        modifier = Modifier.size(42.dp), contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(8.dp),
            color = AppButtonGreen.copy(alpha = 0.1f)
        ) {}

        Canvas(modifier = Modifier.size(35.dp)) {
            drawRoundRect(
                cornerRadius = CornerRadius(10f,10f),
                color = AppTextGrey.copy(alpha = 0.3f), style = Stroke(
                    width = 1.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            )
        }
        if (icon != null) {
            Icon(
                imageVector = icon,
                modifier = Modifier.size(30.dp),
                tint = AppDark,
                contentDescription = null
            )
        }else {
            Icon(
                imageVector = Icons.Filled.NotInterested,
                modifier = Modifier.size(30.dp),
                tint = AppDark,
                contentDescription = null
            )
        }
    }
}

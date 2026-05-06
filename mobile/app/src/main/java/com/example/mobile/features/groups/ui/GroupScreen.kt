package com.example.mobile.features.groups.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile.core.ui.theme.AppButtonGreen
import com.example.mobile.core.ui.theme.AppDark
import com.example.mobile.core.ui.theme.AppTextGrey
import com.example.mobile.core.ui.theme.FairShareTheme

@Composable
fun GroupScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BalanceSection()
        Spacer(Modifier.size(16.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 45.dp)
            ) {
                IconComponent()
                Spacer(Modifier.size(18.dp))
                Text(
                    text = "Start your first group",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = "Groups are how you split bills with roommates, trips, or anyone. Add people and track expenses forever.",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(Modifier.size(12.dp))
                Text(
                    text = "Tip: scan a receipt and we'll fill in the rest",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = modifier.background(
                        MaterialTheme.colorScheme.onTertiary,
                        shape = RoundedCornerShape(10.dp)
                    ),
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(Modifier.size(12.dp))
            }
            ButtonSection()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupsScreenPreview() {
    FairShareTheme {
        GroupScreen(modifier = Modifier.padding(16.dp))
    }
}


@Composable
fun IconComponent() {
    Box(
        modifier = Modifier.size(100.dp), contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = CircleShape,
            color = AppButtonGreen.copy(alpha = 0.1f)
        ) {}

        Canvas(modifier = Modifier.size(120.dp)) {
            drawCircle(
                color = AppTextGrey.copy(alpha = 0.3f), style = Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            )
        }
        Icon(
            imageVector = Icons.Outlined.Group,
            modifier = Modifier.size(55.dp),
            tint = AppDark,
            contentDescription = null
        )
    }
}


@Composable
fun BalanceSection() {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "OVERALL BALANCE",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 14.sp
            )
            Spacer(Modifier.size(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "$0.00",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "nothing to settle",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun ButtonSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.size(height = 45.dp, width = (240.dp)),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Text("Create a group")
        }/*            Button(
                        modifier = Modifier.size(height = 45.dp, width = (240.dp)),
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Text("Create a group")
                    }*/
    }
}
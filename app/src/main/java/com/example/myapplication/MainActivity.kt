package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Brand {
    val GradientTop = Color(0xFF111111)
    val GradientBottom = Color(0xFFFF5722)
    val SubtitleColor = Color(0xFFE0E0E0)
    val CardBackground = Color.White.copy(alpha = 0.15f)

    val ProfileImageSize = 120.dp
    val ProfileBorderWidth = 2.dp
    val CardCornerRadius = 12.dp
    val MainGapHeight = 48.dp
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Brand.GradientTop,
            Brand.GradientBottom
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Added top spacer to push the profile image away from the top boundary
                    Spacer(modifier = Modifier.height(24.dp))

                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile ni Christy",
                        modifier = Modifier
                            .size(Brand.ProfileImageSize)
                            .clip(CircleShape)
                            .border(Brand.ProfileBorderWidth, Color.White, CircleShape)
                    )

                    Text(
                        text = "Christy Ann Reyes",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "UI/UX Designer",
                        color = Brand.SubtitleColor,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(Brand.MainGapHeight))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ContactButton(
                        icon = Icons.Default.Call,
                        text = "+63 966 628 1947",
                        onClickLabel = "Make a phone call to Christy"
                    )

                    ContactButton(
                        icon = Icons.Default.Share,
                        text = "@christyreyes",
                        onClickLabel = "Open Christy's social portfolio"
                    )

                    ContactButton(
                        icon = Icons.Default.Email,
                        text = "christyreyes429@gmail.com",
                        onClickLabel = "Send an email to Christy"
                    )
                }
            }
        }
    }
}

@Composable
fun ContactButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClickLabel: String
) {
    Surface(
        shape = RoundedCornerShape(Brand.CardCornerRadius),
        color = Brand.CardBackground,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClickLabel = onClickLabel,
                onClick = { }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White
            )

            Text(
                text = text,
                color = Color.White,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    MaterialTheme {
        BusinessCard()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode Check")
@Composable
fun BusinessCardDarkPreview() {
    MaterialTheme {
        BusinessCard()
    }
}

@Preview(showBackground = true, fontScale = 1.5f, name = "Large Font Check")
@Composable
fun BusinessCardFontScalePreview() {
    MaterialTheme {
        BusinessCard()
    }
}
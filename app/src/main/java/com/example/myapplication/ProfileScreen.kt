package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

private val DarkPink = Color(0xFFE75B8E)
private val LightDarkPink = Color(0xFFE91E63)
private val DarkPinkContainer = Color(0xFF880E4F)

private val DarkPinkTheme = lightColorScheme(
    primary = DarkPink,
    secondary = LightDarkPink,
    primaryContainer = DarkPinkContainer,
    onPrimary = Color.White
)

@Composable
fun ProfileForm(state: ProfileUiState, viewModel: ProfileViewModel) {
    val roundedShape = RoundedCornerShape(5.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "My Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPink
        )
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.name,
            onValueChange = { viewModel.onNameChange(it) },
            label = { Text("Full name") },
            shape = roundedShape,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email") },
            shape = roundedShape,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = state.contactNumber,
            onValueChange = { viewModel.onContactChange(it) },
            label = { Text("Contact number") },
            shape = roundedShape,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = state.address,
            onValueChange = { viewModel.onAddressChange(it) },
            label = { Text("Address") },
            shape = roundedShape,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = state.username,
            onValueChange = { viewModel.onUsernameChange(it) },
            label = { Text("Username") },
            shape = roundedShape,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))
        Text("Skills", fontWeight = FontWeight.Bold, color = DarkPink)

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = state.newSkill,
                onValueChange = { viewModel.onNewSkillChange(it) },
                label = { Text("Add a skill") },
                shape = roundedShape,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = { viewModel.addSkill() },
                shape = roundedShape
            ) {
                Text("Add")
            }
        }

        state.skills.forEach { skill ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("• $skill", modifier = Modifier.weight(1f))
                TextButton(
                    onClick = { viewModel.removeSkill(skill) },
                    colors = ButtonDefaults.textButtonColors(contentColor = DarkPink)
                ) {
                    Text("Remove")
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Button(
            onClick = { viewModel.showPreview() },
            shape = roundedShape,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Preview")
        }
    }
}

@Composable
fun ProfilePreview(state: ProfileUiState, onBack: () -> Unit) {
    val roundedShape = RoundedCornerShape(5.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Profile Preview",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPink
        )
        Spacer(Modifier.height(12.dp))

        Text("Name: ${state.name}")
        Text("Email: ${state.email}")
        Text("Contact: ${state.contactNumber}")
        Text("Address: ${state.address}")
        Text("Username: ${state.username}")

        Spacer(Modifier.height(8.dp))
        Text("Skills:", fontWeight = FontWeight.Bold, color = DarkPink)
        if (state.skills.isEmpty()) {
            Text("No skills added yet.")
        } else {
            state.skills.forEach { skill -> Text("• $skill") }
        }

        Spacer(Modifier.height(20.dp))
        OutlinedButton(
            onClick = onBack,
            shape = roundedShape,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkPink)
        ) {
            Text("Back to edit")
        }
    }
}

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    MaterialTheme(colorScheme = DarkPinkTheme) {
        if (state.isPreview) {
            ProfilePreview(state = state, onBack = { viewModel.backToEdit() })
        } else {
            ProfileForm(state = state, viewModel = viewModel)
        }
    }
}
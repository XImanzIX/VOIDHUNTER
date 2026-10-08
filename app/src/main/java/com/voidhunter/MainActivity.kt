package com.voidhunter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// VOIDHUNTER color palette
val VoidBlack = Color(0xFF07080A)
val VoidElevated = Color(0xFF12151A)
val VoidSteel = Color(0xFFC8D0D4)
val VoidCyan = Color(0xFF7EB8C9)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VoidHunterApp()
        }
    }
}

@Composable
fun VoidHunterApp() {
    var command by remember { mutableStateOf("") }
    val output = remember { mutableStateListOf("> VOIDHUNTER v0.1.0 initialized", "> type 'help' for available modules") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VoidBlack)
            .padding(16.dp)
    ) {
        Text(
            text = "VOIDHUNTER",
            color = VoidCyan,
            fontFamily = FontFamily.Monospace,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "ethical hacking lab // dark terminal mode",
            color = VoidSteel,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(VoidElevated)
                .padding(12.dp)
        ) {
            items(output) { line ->
                Text(
                    text = line,
                    color = VoidSteel,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = command,
            onValueChange = { command = it },
            label = { Text("command", color = VoidCyan) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = VoidCyan,
                unfocusedBorderColor = VoidSteel,
                cursorColor = VoidCyan,
                focusedLabelColor = VoidCyan
            ),
            textStyle = LocalTextStyle.current.copy(
                fontFamily = FontFamily.Monospace,
                color = VoidSteel
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                output.add("> $command")
                output.add(handleCommand(command))
                command = ""
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = VoidCyan)
        ) {
            Text("EXECUTE", color = VoidBlack, fontFamily = FontFamily.Monospace)
        }
    }
}

fun handleCommand(cmd: String): String = when (cmd.trim().lowercase()) {
    "help" -> "modules: nmap, metasploit, burp, ai-assist, settings"
    "nmap" -> "[nmap] scanning... (stub)"
    "ai-assist" -> "[ai] assistant online (stub)"
    "" -> ""
    else -> "command not found: $cmd"
}
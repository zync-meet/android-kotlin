package com.zync.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.zync.android.ui.theme.ZyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContent {
            ZyncTheme {
                Text(text = "Welcome to Zync Mobile!")
            }
        }
    }
}

package com.zync.android.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.zync.android.database.entities.ProjectEntity

@Composable
fun DashboardScreen(projects: List<ProjectEntity>) {
    LazyColumn {
        items(projects) { project ->
            Card {
                Text(text = project.name)
                Text(text = project.description)
            }
        }
    }
}

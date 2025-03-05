package ru.vlyashuk.jetpackcomposecourse.other

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TestScreen() {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "Text 1")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Text 2")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Text 3")
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBarTest()
            },
            bottomBar = {
                BottomNavigationMenu()
            }
        ) {
            Text(
                modifier = Modifier.padding(it),
                text = "This is scaffold content"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTest() {
    TopAppBar(
        title = {
            Text(text = "TopAppBar")
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null,
                    tint = Color.Red
                )
            }
        },
        actions = {
            Row {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.Favorite, contentDescription = null,
                        tint = Color.Red
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.Delete, contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }
    )
}


@Composable
fun BottomNavigationMenu() {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.Black
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    Icons.Outlined.Edit, contentDescription = null,
                    tint = Color.Red
                )
            },
            label = {
                Text(text = "Edit", color = Color.White)
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    Icons.Filled.Favorite, contentDescription = null,
                    tint = Color.Red
                )
            },
            label = {
                Text(text = "Favorite", color = Color.White)
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    Icons.Outlined.Delete, contentDescription = null,
                    tint = Color.Red
                )
            },
            label = {
                Text(text = "Delete", color = Color.White)
            }
        )
    }
}

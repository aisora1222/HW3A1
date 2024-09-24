package com.example.hw3a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hw3a1.ui.theme.HW3A1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HW3A1Theme {
                ShoppingApp()
            }
        }
    }
}

@Composable
fun ShoppingApp() {
    var item by remember { mutableStateOf("") }
    var itemNum by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf(false) }
    val shoppingList = remember { mutableStateListOf<Triple<String, Int, Boolean>>() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp)
    ) {
        TextField(
            value = item,
            onValueChange = { item = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            label = { Text("Enter the Name of Item") }
        )

        TextField(
            value = itemNum,
            onValueChange = { itemNum = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            label = { Text("Enter the Number of Item") }
        )

        Button(
            onClick = {
                if (item.isNotEmpty() && itemNum.isNotEmpty()) {
                    val itemNumInt = itemNum.toIntOrNull() ?: 0
                    shoppingList.add(Triple(item, itemNumInt, selected))
                    item = ""
                    itemNum = ""
                    selected = false
                }
            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text("Add to List")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            items(shoppingList) { (item, quantity, isUrgent) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    Text(text = "$item ....... $quantity")
                    if (isUrgent) {
                        Text(text = "Expensive", color = Color.Red,textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(60.dp)
    ) {
        Checkbox(
            checked = selected,
            onCheckedChange = { selected = it },
            colors = CheckboxDefaults.colors(checkedColor = Color.Red),
            modifier = Modifier.padding(start = 60.dp)
        )
        Text(text = "Is it Expensive?", color = Color.Red)
    }
}

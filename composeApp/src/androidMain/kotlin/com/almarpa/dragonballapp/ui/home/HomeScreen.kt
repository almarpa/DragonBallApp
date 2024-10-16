package com.almarpa.dragonballapp.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.almarpa.dragonballapp.R
import com.almarpa.dragonballapp.domain.model.CharacterModel
import com.almarpa.dragonballapp.resources.BackgroundPrimary
import com.almarpa.dragonballapp.resources.Orange
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateToDetail: (Int) -> Unit) {
    val homeViewModel: HomeViewModel = koinViewModel()

    val characters by homeViewModel.characters.collectAsState()

    Scaffold(
        containerColor = BackgroundPrimary,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Goku Edition",
                        modifier = Modifier.padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = Orange
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundPrimary
                )
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp)) {
                items(characters) { character ->
                    CharacterItem(character) {
                        navigateToDetail(it.id)
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: CharacterModel, onItemClick: (CharacterModel) -> Unit) {
    Box(
        Modifier
            .padding(16.dp)
            .height(250.dp)
            .clickable { onItemClick(character) },
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.weight(1f))
            Card(
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20),
                border = BorderStroke(4.dp, Orange)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = character.name, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    Text(text = character.race, fontSize = 20.sp, fontStyle = FontStyle.Italic)
                }
            }
        }
        DragonBallShape()
        AsyncImage(
            model = character.image, contentDescription = "", modifier = Modifier.size(180.dp)
        )
    }
}

@Preview
@Composable
fun DragonBallShape() {
    Box(
        modifier = Modifier
            .size(180.dp)
            .clip(CircleShape)
            .background(Orange)
            .border(
                BorderStroke(
                    6.dp, Brush.horizontalGradient(
                        listOf(
                            Color.White.copy(alpha = 0.3f),
                            Orange,
                            Orange,
                        )
                    )
                ), CircleShape
            ), contentAlignment = Alignment.Center
    ) {
        Row {
            Column {
                DragonBallStar()
                DragonBallStar()
            }
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                DragonBallStar()
                DragonBallStar()
            }
        }
    }
}

@Composable
fun DragonBallStar() {
    Icon(
        modifier = Modifier
            .padding(10.dp)
            .size(24.dp),
        painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_star),
        contentDescription = "",
        tint = Color.Red
    )
}













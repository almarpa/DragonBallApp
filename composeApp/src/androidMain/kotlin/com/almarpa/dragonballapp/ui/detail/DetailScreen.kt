package com.almarpa.dragonballapp.ui.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.almarpa.dragonballapp.R
import com.almarpa.dragonballapp.domain.model.CharacterDetailModel
import com.almarpa.dragonballapp.domain.model.TransformationModel
import com.almarpa.dragonballapp.resources.Orange
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailScreen(id: Int, navigateBack:()-> Unit) {
    val detailViewModel: DetailViewModel = koinViewModel(parameters = { parametersOf(id) })
    val detail by detailViewModel.character.collectAsState()

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        detail?.let { character ->
            ItemDetail(character, navigateBack)
        } ?: run { CircularProgressIndicator(color = Orange) }
    }

}

@Composable
fun ItemDetail(character: CharacterDetailModel, navigateBack:()-> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color.Black, Orange), startY = 0f, endY = 450f
                )
            )
    ) {
        Box(contentAlignment = Alignment.TopCenter) {
            Card(
                Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 48.dp, top = 150.dp)
                    .fillMaxSize()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(100.dp))
                    Text(
                        text = character.characterModel.name,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.Center) {
                        Label(character.characterModel.race)
                        Spacer(modifier = Modifier.width(32.dp))
                        Label(character.characterModel.gender)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(Modifier.height(75.dp)) {
                        IconInformation(
                            Modifier.weight(1f), R.drawable.ic_ki, character.characterModel.ki
                        )
                        VerticalDivider(thickness = 2.dp)
                        IconInformation(
                            Modifier.weight(1f), R.drawable.ic_planet, character.originPlanet.name
                        )
                    }
                    TransformationList(Modifier.fillMaxSize(), character.transformations)
                }
            }
            AsyncImage(
                model = character.characterModel.image,
                contentDescription = "",
                modifier = Modifier
                    .size(250.dp)
                    .padding(vertical = 24.dp)
            )
            Row(Modifier.fillMaxWidth()) {
                Icon(
                    painterResource(id = R.drawable.ic_back_24),
                    "",
                    tint = Color.White,
                    modifier = Modifier.padding(8.dp).size(24.dp).clickable { navigateBack() }
                )
                Spacer(modifier = Modifier.weight(1f))
            }

        }


//
    }
}

@Composable
fun TransformationList(modifier: Modifier, transformations: List<TransformationModel>) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (transformations.isEmpty()) {
            Text(text = "No hay transformaciones a día de hoy")
        } else {
            Column {
                val pagerState = rememberPagerState(pageCount = { transformations.size })
                HorizontalPager(
                    state = pagerState,
                    pageSize = PageSize.Fixed(pageSize = 150.dp),
                    pageSpacing = 4.dp
                ) { pos ->
                    TransformationSticker(transformations[pos])
                }
            }
        }
    }
}

@Composable
fun TransformationSticker(transformationModel: TransformationModel) {
    Card(
        onClick = {},
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.4f)),
        modifier = Modifier.padding(horizontal = 6.dp, vertical = 24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = transformationModel.image, contentDescription = "", Modifier.weight(0.8f)
            )
            Text(
                text = transformationModel.name,
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Label(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .border(2.dp, Orange, RoundedCornerShape(50))
            .padding(horizontal = 16.dp, vertical = 2.dp)
    )
}

@Composable
fun IconInformation(modifier: Modifier, id: Int, text: String) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = id),
            contentDescription = "",
            modifier = Modifier.size(48.dp)
        )
        Text(text = text)
    }
}
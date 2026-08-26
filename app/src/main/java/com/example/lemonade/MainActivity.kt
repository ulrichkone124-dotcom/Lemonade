package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                ApplicationLemonade()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationLemonade() {
    var etapeActuelle by rememberSaveable { mutableIntStateOf(1) }
    var nombrePressionsRestantes by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Lemonade",
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF9E44C)
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            when (etapeActuelle) {
                1 -> {
                    TexteEtImageCitron(
                        idRessourceTexte = R.string.lemon_tree,
                        idRessourceImage = R.drawable.lemon_tree,
                        idRessourceDescription = R.string.lemon_tree_content_description,
                        auClicSurImage = {
                            etapeActuelle = 2
                            nombrePressionsRestantes = Random.nextInt(2, 5)
                        }
                    )
                }
                2 -> {
                    TexteEtImageCitron(
                        idRessourceTexte = R.string.lemon_squeeze,
                        idRessourceImage = R.drawable.lemon_squeeze,
                        idRessourceDescription = R.string.lemon_content_description,
                        auClicSurImage = {
                            nombrePressionsRestantes--
                            if (nombrePressionsRestantes == 0) {
                                etapeActuelle = 3
                            }
                        }
                    )
                }
                3 -> {
                    TexteEtImageCitron(
                        idRessourceTexte = R.string.lemon_drink,
                        idRessourceImage = R.drawable.lemon_drink,
                        idRessourceDescription = R.string.lemonade_content_description,
                        auClicSurImage = {
                            etapeActuelle = 4
                        }
                    )
                }
                4 -> {
                    TexteEtImageCitron(
                        idRessourceTexte = R.string.lemon_restart,
                        idRessourceImage = R.drawable.lemon_restart,
                        idRessourceDescription = R.string.empty_glass_content_description,
                        auClicSurImage = {
                            etapeActuelle = 1
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TexteEtImageCitron(
    idRessourceTexte: Int,
    idRessourceImage: Int,
    idRessourceDescription: Int,
    auClicSurImage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(40.dp))
                .background(Color(0xFFC3ECD2))
                .clickable { auClicSurImage() }
                .padding(32.dp)
        ) {
            Image(
                painter = painterResource(idRessourceImage),
                contentDescription = stringResource(idRessourceDescription),
                modifier = Modifier.size(160.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(idRessourceTexte),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ApplicationLemonadePreview() {
    LemonadeTheme {
        ApplicationLemonade()
    }
}
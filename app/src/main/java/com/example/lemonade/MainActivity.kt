package com.example.lemonade

import androidx.compose.ui.tooling.preview.Preview
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ApplicationLemonade()
                }
            }
        }
    }
}

@Composable
fun ApplicationLemonade() {
    var etapeActuelle by rememberSaveable { mutableIntStateOf(1) }
    var nombrePressionsRestantes by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
        modifier = modifier
    ) {
        Image(
            painter = painterResource(idRessourceImage),
            contentDescription = stringResource(idRessourceDescription),
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                .clickable { auClicSurImage() }
                .padding(16.dp)
        )

        Text(
            text = stringResource(idRessourceTexte),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
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
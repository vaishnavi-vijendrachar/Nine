package com.vaishnavi.nine.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vaishnavi.model.Assets
import com.vaishnavi.nine.R

@Composable
fun MainScreen(timestamp: Long, assetsList: List<Assets>, onClickAction: (String) -> Unit) {
    MaterialTheme {
        Scaffold(
            topBar = { TopBar() },
            content = {
                LazyColumn(
                    modifier = Modifier
                        .background(colorResource(id = R.color.background_primary))
                        .fillMaxWidth()
                        .padding(it)
                        .padding(dimensionResource(id = R.dimen.padding_0_5x)),
                    contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_0_5x))
                ) {

                    items(assetsList.size) { item ->
                        Article(assetsList.sortedByDescending { timestamp }, item, onClickAction)
                        Divider(
                            startIndent = dimensionResource(id = R.dimen.padding_1x),
                            thickness = dimensionResource(
                                id = R.dimen.padding_0_25x
                            ),
                            color = colorResource(id = R.color.background_secondary)
                        )
                    }
                }
            })
    }
}

@Composable
fun Article(assetsList: List<Assets>, item: Int, onClickAction: (String) -> Unit) {
    Column(modifier = Modifier
        .padding(dimensionResource(id = R.dimen.padding_0_5x))
        .clickable {
            assetsList[item].url?.let { onClickAction(it) }
        }
    ) {
        Row {
            Thumbnail(assetsList, item)
            Headline(assetsList, item)
        }
        Abstract(assetsList, item)
    }
}

@Composable
fun Thumbnail(assetsList: List<Assets>, item: Int) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_0_5x))
    ) {
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_0_5x)))
        Box(
            modifier = Modifier
                .width(
                    if (isTablet()) dimensionResource(id = R.dimen.tablet_image_width) else dimensionResource(
                        id = R.dimen.phone_image_width
                    )
                )
                .background(Color.Black)
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_1x)))
        ) {
            AsyncImage(
                model = assetsList[item].relatedImages[1].url,
                contentDescription = stringResource(id = R.string.content_description_thumbnail)
            )
        }
    }
}

@Composable
fun Headline(assetsList: List<Assets>, i: Int) {
    Column(
        modifier = Modifier
            .padding(
                dimensionResource(id = R.dimen.padding_1x),
                dimensionResource(id = R.dimen.padding_0_5x)
            )
    ) {
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_0_5x)))
        Text(
            assetsList[i].headline,
            color = colorResource(id = R.color.font),
            fontWeight = FontWeight.Normal,
            fontSize = if (isTablet()) 24.sp else 18.sp
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_0_5x)))
    }
}

@Composable
fun Abstract(assetsList: List<Assets>, i: Int) {
    Row {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_0_5x))
        ) {
            Spacer(
                modifier = Modifier.padding(
                    if (isTablet()) dimensionResource(id = R.dimen.padding_1x) else dimensionResource(
                        id = R.dimen.padding_0_5x
                    )
                )
            )
            Text(
                assetsList[i].theAbstract,
                color = colorResource(id = R.color.font),
                fontWeight = FontWeight.Light,
                fontSize = if (isTablet()) 20.sp else 14.sp
            )
            Spacer(
                modifier = Modifier.padding(
                    if (isTablet()) dimensionResource(id = R.dimen.padding_1x) else dimensionResource(
                        id = R.dimen.padding_0_5x
                    )
                )
            )
            Text(
                assetsList[i].byLine,
                color = colorResource(id = R.color.font),
                fontWeight = FontWeight.ExtraLight,
                fontSize = if (isTablet()) 18.sp else 11.sp
            )
            Spacer(
                modifier = Modifier.padding(
                    if (isTablet()) dimensionResource(id = R.dimen.padding_1x) else dimensionResource(
                        id = R.dimen.padding_0_5x
                    )
                )
            )
        }
    }
}

@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current
    return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        configuration.screenWidthDp > 840
    } else {
        configuration.screenWidthDp > 600
    }
}
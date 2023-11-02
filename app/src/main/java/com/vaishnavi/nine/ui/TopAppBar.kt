package com.vaishnavi.nine.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vaishnavi.nine.R

@Composable
fun TopBar() {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            elevation = dimensionResource(id = R.dimen.padding_0_5x),
            title = {
                Text(
                    stringResource(id = R.string.app_name),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.font_topbar),
                    fontFamily = FontFamily.SansSerif
                )
            },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Newspaper, "home")
                }
            },
            contentColor = colorResource(id = R.color.font_topbar),
            backgroundColor = colorResource(id = R.color.black)
        )
    }
}
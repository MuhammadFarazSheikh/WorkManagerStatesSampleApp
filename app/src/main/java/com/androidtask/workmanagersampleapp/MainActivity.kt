package com.androidtask.workmanagersampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    fun setupTopBar()
    {
        TopAppBar(modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
            title ={
                Text(text = stringResource(R.string.text_title),
                    fontSize = 15.sp)
            },
            contentColor = Color.White,
            backgroundColor = Color.DarkGray
        )
    }

    @Composable
    fun mainContent()
    {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            var (runOneTime) = createRefs()
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
            ) {
                Text(
                    stringResource(R.string.run_onetime_workmanager),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }

    @Composable
    @Preview
    fun mainStructure()
    {
        var scaffoldState = rememberScaffoldState(drawerState = DrawerState(initialValue = DrawerValue.Closed))
        Scaffold(modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            backgroundColor = Color.White,
            topBar = {
                setupTopBar()
            },
            scaffoldState = scaffoldState,
        ) {
            mainContent()
        }
    }
}
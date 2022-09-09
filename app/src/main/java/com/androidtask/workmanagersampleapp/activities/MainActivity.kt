package com.androidtask.workmanagersampleapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.work.*
import com.androidtask.workmanagersampleapp.R
import com.androidtask.workmanagersampleapp.utils.runOneTimeWorker
import com.androidtask.workmanagersampleapp.workmanager.OneTimeWorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            mainStructure()
        }
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
            modifier = Modifier
                .padding(10.dp, 10.dp, 10.dp, 0.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            var (runOneTime) = createRefs()
            Button(
                onClick = {
                          runOneTimeWorker(this@MainActivity, Observer { oneTimeWorkManagerListener->
                              if (oneTimeWorkManagerListener.state == WorkInfo.State.SUCCEEDED) {
                                  Toast.makeText(
                                      this@MainActivity,
                                      oneTimeWorkManagerListener.outputData.getString("api_results"),Toast.LENGTH_SHORT).show()
                              }
                          },this@MainActivity)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
            ) {
                Text(
                    stringResource(R.string.run_onetime_workmanager),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
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
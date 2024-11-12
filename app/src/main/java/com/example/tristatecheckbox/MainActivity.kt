package com.example.tristatecheckbox

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import com.example.tristatecheckbox.ui.theme.TriStateCheckboxTheme

const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TriStateCheckboxTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    AllCheckbox()
                }
            }
        }
    }
}

@Preview
@Composable
fun AllCheckbox(modifier: Modifier = Modifier){
    val allChecked = remember { mutableStateListOf(false, false, false) }
    val textLists = listOf("이용 약관 동의", "개인 정보 수집 동의", "위치 기반 서비스 약관 동의")
    val parentState = when {
        allChecked.all {it} -> ToggleableState.On
        allChecked.none {it} -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "전체 동의",
            )
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    val newState = if(parentState != ToggleableState.On){
                        true
                    }else {
                        false
                    }
                    allChecked.forEachIndexed { index, _ ->
                        allChecked[index] = newState
                    }
                }
            )
        }

        allChecked.forEachIndexed { index, checked ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${textLists[index]}"
                )
                Checkbox(
                    checked = checked,
                    onCheckedChange = { it ->
                        allChecked[index] = it
                    }
                )
            }
        }
    }
}
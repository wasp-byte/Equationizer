package com.waspbyte.equationizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(prefs = remember { createDataStore{ applicationContext.filesDir.resolve(dataStoreFileName).absolutePath } })
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App(prefs = createDataStore(applicationContext))
//}
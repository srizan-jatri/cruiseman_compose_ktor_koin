package com.srizan.cruiseman

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.srizan.cruiseman.databinding.ContentMainBinding
import org.koin.compose.KoinContext


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(
            ComposeView(this).apply {
                setContent {
                    KoinContext {
                        AndroidViewBinding(ContentMainBinding::inflate)
                    }
                }
            }
        )
    }
}




package com.kartollika.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.kartollika.myapplication.databinding.HostActivityBinding

class ActivityHost: Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = HostActivityBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.playground.setOnClickListener {
      startActivity(Intent(this, PlaygroundActivity::class.java))
    }

    binding.components.setOnClickListener {
      startActivity(Intent(this, ComponentsActivity::class.java))
    }
  }
}
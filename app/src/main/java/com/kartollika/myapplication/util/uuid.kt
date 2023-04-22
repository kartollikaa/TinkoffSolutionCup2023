package com.kartollika.myapplication.util

import java.util.UUID

val uuid: String
  get() = UUID.randomUUID().toString()
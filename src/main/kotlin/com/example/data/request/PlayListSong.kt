package com.example.data.request

import kotlinx.serialization.Serializable

@Serializable
data class AddToPlayList(val song:String,val playList:String)
@Serializable
data class RemoveFromPlayList(val song:String,val playList:String)

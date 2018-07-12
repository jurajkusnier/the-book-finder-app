package com.jurajkusnier.thebookfinderapp.data.model

data class GoogleBooksResult(val items:List<Item>)
data class Item(val id:String, val volumeInfo: VolumeInfo)
data class VolumeInfo(val title:String, val subtitle:String, val authors:List<String>, val publisher:String, val description: String, val imageLinks:ImageLink)
data class ImageLink(val smallThumbnail:String, val thumbnail:String)
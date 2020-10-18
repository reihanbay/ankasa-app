package com.arkademy.ankasa.dashboard.explore

data class TrendingModel (
    val image: String,
    val city: String,
    val country: String
){
    companion object {
        fun getAllDestination(): ArrayList<TrendingModel> {
            val listDestination = ArrayList<TrendingModel>()

            val listFirst = TrendingModel(
                "https://picsum.photos/id/237/200/300",
                "Tokyo",
                "Japan"
            )
            listDestination.add(listFirst)

            val listSecond = TrendingModel(
                "https://picsum.photos/id/237/200/300",
                "Tokyo",
                "Japan"
            )
            listDestination.add(listSecond)

            val listThird = TrendingModel(
                "https://picsum.photos/id/237/200/300",
                "Tokyo",
                "Japan"
            )
            listDestination.add(listThird)
            return listDestination
        }
    }
}
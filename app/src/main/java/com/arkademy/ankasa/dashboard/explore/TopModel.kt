package com.arkademy.ankasa.dashboard.explore

data class TopModel(
    val image: String,
    val country: String
) {
    companion object {
        fun getDestination(): ArrayList<TopModel> {
            val list = ArrayList<TopModel>()

            val listFirst = TopModel(
                "https://picsum.photos/id/237/200/300",
                "Jepang"
                )
            list.add(listFirst)

            val listSecond = TopModel(
                "https://picsum.photos/id/237/200/300",
                "Jepang"
            )
            list.add(listSecond)

            val listThird = TopModel(
                "https://picsum.photos/id/237/200/300",
                "Jepang"
            )
            list.add(listThird)

            val listFourth = TopModel(
                "https://picsum.photos/id/237/200/300",
                "Jepang"
            )
            list.add(listFourth)

            val listFifth = TopModel(
                "https://picsum.photos/id/237/200/300",
                "Jepang"
            )
            list.add(listFifth)

            val listSizx = TopModel(
                "https://picsum.photos/id/237/200/300",
                "Jepang"
            )
            list.add(listSizx)

            val listSeven = TopModel(
                "https://picsum.photos/id/237/200/300",
                "Jepang"
            )
            list.add(listSeven)

            val listEight = TopModel(
                "https://picsum.photos/id/237/200/300",
                "Jepang"
            )
            list.add(listEight)

            val listNine = TopModel(
                "https://picsum.photos/id/237/200/300",
                "Jepang"
            )
            list.add(listNine)

            val listTen = TopModel(
                "https://picsum.photos/id/237/200/300",
                "Jepang"
            )
            list.add(listTen)

            val listEleven = TopModel(
                "https://picsum.photos/id/237/200/300",
                "Jepang"
            )
            list.add(listEleven)

            return list
        }
    }
}
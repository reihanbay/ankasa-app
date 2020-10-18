package com.arkademy.ankasa.dashboard.booking

data class BookingModel (
    val date: String,
    val from: String,
    val to: String,
    val maskapai: String,
    val status: String
){
    companion object {
        fun getBookingList() : ArrayList<BookingModel> {
            val bookingList = ArrayList<BookingModel>()

            val bookingFirst = BookingModel("20 Januari 2020", "Medan", "Seoul", "Air Asia", "Waiting For Payment"
            )
            bookingList.add(bookingFirst)

            val bookingSecond = BookingModel("20 Januari 2020", "Medan", "Seoul", "Air Asia", "Waiting For Payment"
            )
            bookingList.add(bookingSecond)

            val bookingThird = BookingModel("20 Januari 2020", "Medan", "Seoul", "Air Asia", "Waiting For Payment"
            )
            bookingList.add(bookingThird)

            val bookingFourth = BookingModel("20 Januari 2020", "Medan", "Seoul", "Air Asia", "Waiting For Payment"
            )
            bookingList.add(bookingFourth)

            val bookingFifth = BookingModel("20 Januari 2020", "Medan", "Seoul", "Air Asia", "Waiting For Payment"
            )
            bookingList.add(bookingFifth)
            return bookingList
        }
    }
}
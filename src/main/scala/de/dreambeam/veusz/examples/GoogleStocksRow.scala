package de.dreambeam.veusz.examples

import java.time.LocalDate

case class GoogleStocksRow(dates: LocalDate,
                           open: Double,
                           high: Double,
                           low: Double,
                           close: Double,
                           adjClose: Double,
                           volume: Int)
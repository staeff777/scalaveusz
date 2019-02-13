package de.dreambeam.veusz.examples

import java.io.File

import de.dreambeam.veusz.components.{Barchart, Document, Graph}
import kantan.csv._
import kantan.csv.ops._
import kantan.csv.java8._
import kantan.csv.generic._
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.data.{DateTimeConstructor, Numerical}

object StockMarket extends App {
  // Read CSV
  val reader = new File("data/stocks/GOOGL.csv").asUnsafeCsvReader[GoogleStocksRow](rfc.withHeader).toVector

  val dates = reader.map(_.dates)
  val datesFormatted = DateTimeConstructor.fromLocalDate(dates)

  val opens = reader.map(_.open)
  val highs = reader.map(_.high)
  val lows = reader.map(_.low)

  val posErrors = (highs, opens).zipped.map { case (h, o) => h - o }
  val negErrors = (opens, lows).zipped.map { case (o, l) => o - l }

  val y = Numerical(opens, posErrors=Some(posErrors), negErrors=Some(negErrors))

  val bar = Barchart(Vector(y), datesFormatted, Direction.Vertical, BarchartMode.Stacked, Vector(""), "x", "y", "bar")
  bar.show("BarchartTest")
}

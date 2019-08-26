package de.dreambeam.veusz.util

import de.dreambeam.veusz.data.{Data, Text}

import scala.collection.mutable

object DataHandler{
  def apply(): DataHandler = new DataHandler()
}
class DataHandler {
  val dataset = mutable.HashMap.empty[Data, String]
  val references = mutable.Map.empty[String, Int]

  /**
    *
    * @param data one of Numerical, DateTime, Text
    * @param suffix a suffix to add to the returned reference
    * @return a String refernece to the data
    */
  def uniqueReference(data: Data, suffix: String): String = {
    if (dataset contains data) dataset(data)
    else {
      val nameWithSuffix: String = {
        if (data.name == "" || data.name == null) {
          if (suffix == "") s"data$suffix"
          else s"data_$suffix"
        }
        else s"${data.name}_$suffix"
      }
      val nameWithIndex: String = {
        if (references contains nameWithSuffix) {
          references(nameWithSuffix) += 1
          s"$nameWithSuffix${references(nameWithSuffix)}"
        }
        else {
          references.put(nameWithSuffix, 1)
          nameWithSuffix
        }
      }
      dataset.put(data, nameWithIndex)
      nameWithIndex
    }
  }
}
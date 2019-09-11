package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, GraphItem, Item, NonOrthGraphItem}
import de.dreambeam.veusz.format._

case class Key(var title: String = "", var name: String = "key", var config: KeyConfig = KeyConfig()) extends GraphItem with Configurable {
  val group = "key"
}

case class KeyConfig(main: KeyMainConfig = KeyMainConfig(),
                     text: TextConfig = TextConfig(),
                     background: BackgroundConfig = BackgroundConfig(),
                     border: BorderConfig = BorderConfig())

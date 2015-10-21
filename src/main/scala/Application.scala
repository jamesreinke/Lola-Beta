package lola

import scala.scalajs.js.JSApp
import org.scalajs.dom._

import org.scalajs.jquery.{jQuery, JQuery}

object Application extends JSApp {

	def main = {
		val tag = "div"
		val attributes = Map("class" -> "gay-fish", "value" -> "6")
		val style = Map("text-align" -> "center")
		val sText = "Dick Cheese"
		val eText = ""
		val items = List()
		val g = new js.Node(tag, attributes, style, sText, eText, items, "poop")
		jQuery("body").append(g.render)
	}
}
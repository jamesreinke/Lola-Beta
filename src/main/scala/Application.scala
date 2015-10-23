package lola

import scala.scalajs.js.JSApp
import org.scalajs.dom._

import org.scalajs.jquery.{jQuery, JQuery}

object Application extends JSApp {

	object html {
		def apply(
			tag: String = "",
			attributes: Map[String,String] = Map(),
			style: Map[String,String] = Map(),
			text: String = "",
			items: List[interface.Node] = List(),
			id: String = interface.Lola.assign): interface.Node = {
			new interface.Node(tag, attributes, style, text, "", items, id)
		}
	}

	def main = {
		import interface._
		import upickle.default._
		val n = html("div", text = "Poop Dick", style = Map("text-align" -> "center", "background-color" -> "lightblue"))
		val c1 = new Create(n)
		var c2 = new Css(n, "background-color", "red")
		Parse(read[Command](write(c1)))
		Parse(read[Command](write(c2)))
		println(write(c2))
	}
}
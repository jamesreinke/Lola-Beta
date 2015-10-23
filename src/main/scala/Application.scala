package lola

import scala.scalajs.js.JSApp
import org.scalajs.dom._

import org.scalajs.jquery.{jQuery, JQuery}

object Application extends JSApp {

	def main = {
		import interface._
		import upickle.default._
		val tag = "div"
		val attributes = Map("class" -> "col-md-6", "name" -> "james")
		val style = Map("text-align" -> "center")
		val items = List[Node]()
		val id = Lola.assign
		val n = new Node(tag, attributes, style, "Dick Sauce", "", items, id)
		val c = new Create(n)
		Parse(read[Command](write(c))) // encode... decode... parse !
	}
}
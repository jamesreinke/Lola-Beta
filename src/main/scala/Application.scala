package lola

import scala.scalajs.js.JSApp
import org.scalajs.dom._

import org.scalajs.jquery.{jQuery, JQuery}

object Application extends JSApp {

	def main = {
		import interface._
		import upickle.default._
		for(a <- 1 until 100) {
			val style = Map(
				"padding" -> "20px",
				"width" -> "30%",
				"height" -> "50px",
				"background-color" -> "lightblue",
				"text-align" -> "center",
				"float" -> "right")
			val n = el("div", sText = "Dick Cheese", style = style)
			val c = new Create(n)
			Parse(read[Command](write(c)))
		}
	}
}
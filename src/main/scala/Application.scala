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
				"margin" -> "20px",
				"width" -> "27%",
				"height" -> "50px",
				"background-color" -> "lightblue",
				"text-align" -> "center",
				"float" -> "left")
			val n = el("div", sText = "Dick Cheese", style = style)
			val c = new Create(n)
			val c2 = new OnClick(n, new Delete(n))
			Parse(read[Command](write(c)))
			Parse(read[Command](write(c2)))
		}
	}
}
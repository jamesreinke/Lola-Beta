package lola

import scala.scalajs.js.JSApp
import org.scalajs.dom._

import org.scalajs.jquery.{jQuery, JQuery}


object Application extends JSApp {

	def main = {
		import interface._
		import upickle.default._
		for(a <- 1 until 100) {
			val n = el("div", sText = "Dick Cheese", style = Map("background-color" -> "lightblue", "text-align" -> "center"))
			val c = new Create(n)
			Parse(read[Command](write(c))) // Encode -> Decode -> Parse
		}
	}
}
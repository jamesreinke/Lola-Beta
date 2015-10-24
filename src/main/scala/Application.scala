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
				"margin" -> "15px",
				"width" -> "27%",
				"height" -> "100px",
				"background-color" -> "lightblue",
				"text-align" -> "center",
				"border-radius" -> "5px",
				"box-shadow" -> "10px 10px 5px #888888")
			val attr = Map("class" -> "col-md-3")
			val n = el("div", sText = "Dick Cheese", style = style, attributes = attr)
			val c = new Create(n)
			val c2 = new OnClick(n, new SlideUp(n, 800))
			Parse(DecodeCommands(Encode(List(c, c2))))
		}
	}

	def initiate(url: String): Unit = {
		lola.js.Lola.get(url)
	}



}
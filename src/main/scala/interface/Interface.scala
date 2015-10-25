package lola.interface

object Lola {

	/*
		Assign permenant ID's to all nodes
	*/
	import scala.util.Random
	val r = new Random()
	def assign = {
		val s = r.alphanumeric
		s take 10 mkString ""  // returns a pseudo-random string of 10 characters (negligible chance of collision)
	}

}

sealed case class Node(
	val tag: String,
	val attributes: Map[String, String],
	val style: Map[String, String],
	val text: String,
	val items: List[Node],
	val id: String)

object el {
	def apply(
		tag: String = "",
		attributes: Map[String,String] = Map(),
		style: Map[String,String] = Map(),
		text: String = "",
		items: List[Node] = List(),
		id: String = Lola.assign): Node = {
		new Node(tag, attributes, style, text, items, id)
	}
}

sealed trait Command

sealed case class OnClick(n: Node, cm: Command) extends Command

sealed case class OnHover(n: Node, cm: Command, cm2: Command) extends Command

sealed case class Create(n: Node) extends Command

sealed case class Delete(n: Node) extends Command

sealed case class Css(n: Node, style: Map[String,String]) extends Command

sealed case class Animate(css: Css, mili: Int = 400) extends Command // TODO: Implement in Scala JS and in Parser

sealed case class Get(url: String) extends Command

sealed case class Post(url: String, n: Node) extends Command

sealed case class SlideUp(n: Node, mili: Int = 400) extends Command

sealed case class SlideDown(n: Node, mili: Int = 400) extends Command

sealed case class FadeOut(n: Node, mili: Int = 400) extends Command

sealed case class FadeIn(n: Node, mili: Int = 400) extends Command
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
	var tag: String,
	var attributes: Map[String, String],
	var style: Map[String, String],
	var text: String,
	var value: String,
	var items: List[Node],
	var id: String) 

object el {
	def apply(
		tag: String = "",
		attributes: Map[String,String] = Map(),
		style: Map[String,String] = Map(),
		text: String = "",
		value: String = "",
		items: List[Node] = List(),
		id: String = Lola.assign): Node = {
		new Node(tag, attributes, style, text, value, items, id)
	}
}

sealed trait Command

sealed case class OnClick(n: Node, cm: Command) extends Command

sealed case class OnHover(n: Node, cm: Command, cm2: Command) extends Command

sealed case class OnKeyUp(n: Node, cm: Command) extends Command

sealed case class Create(n: Node) extends Command

sealed case class Delete(n: Node) extends Command

sealed case class Update(n: Node) extends Command

sealed case class Get(url: String) extends Command

sealed case class Post(url: String, n: Node) extends Command

sealed case class Animate(style: Map[String,String], mili: Int = 400) extends Command // TODO: Implement in Scala JS and in Parser

sealed case class SlideUp(n: Node, mili: Int = 400) extends Command

sealed case class SlideDown(n: Node, mili: Int = 400) extends Command

sealed case class FadeOut(n: Node, mili: Int = 400) extends Command

sealed case class FadeIn(n: Node, mili: Int = 400) extends Command
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
	val sText: String,
	val eText: String,
	val items: List[Node],
	val id: String)

sealed trait Command

sealed case class OnClick(n: Node, cm: Command) extends Command

sealed case class OnHover(n: Node, cm: Command, cm2: Command) extends Command

sealed case class Create(n: Node) extends Command

sealed case class Delete(n: Node) extends Command

sealed case class Update(n: Node) extends Command

sealed case class Css(n: Node, k: String, v: String) extends Command

sealed case class Animate(n: Node, style: Map[String,String], time: Long) extends Command

sealed case class Get(n: Node, url: String) extends Command

sealed case class Post(n: Node, url: String) extends Command
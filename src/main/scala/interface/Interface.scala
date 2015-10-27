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

object Clear {
	def apply() = {
		new Clear()
	}
	sealed case class Clear() extends Command
}

object OnClick {
	def apply(n: Node, cm: Command) = {
		new OnClick(n, cm)
	}
	sealed case class OnClick(n: Node, cm: Command) extends Command
}

object OnHover {
	def apply(n: Node, cm: Command, cm2: Command) = {
		new OnHover(n, cm, cm2)
	}
	sealed case class OnHover(n: Node, cm: Command, cm2: Command) extends Command
}


object OnKeyUp {
	def apply(n: Node, cm: Command) = {
		new OnKeyUp(n, cm)
	}
	sealed case class OnKeyUp(n: Node, cm: Command) extends Command
}

object Create {

	def apply(n: Node) = {
		new Create(n)
	}

	sealed case class Create(n: Node) extends Command
}

object Delete {
	def apply(n: Node) = {
		new Delete(n)
	}
	sealed case class Delete(n: Node) extends Command
}

object Update {
	def apply(n: Node) = {
		new Update(n)
	}
	sealed case class Update(n: Node) extends Command
}

object Get {
	def apply(url: String) = {
		new Get(url)
	}
	sealed case class Get(url: String) extends Command
}

object Post {
	def apply(url: String, n: List[Node]) = {
		new Post(url, n)
	}
	sealed case class Post(url: String, n: List[Node]) extends Command
}

object SlideUp {
	def apply(n: Node, mili: Int = 400) = {
		new SlideUp(n, mili)
	}
	sealed case class SlideUp(n: Node, mili: Int = 400) extends Command
}

object SlideDown {
	def apply(n: Node, mili: Int = 400) = {
		new SlideDown(n, mili)
	}
	sealed case class SlideDown(n: Node, mili: Int = 400) extends Command
}

object FadeOut {
	def apply(n: Node, mili: Int = 400) = {
		new FadeOut(n, mili)
	}
	sealed case class FadeOut(n: Node, mili: Int = 400) extends Command
}

object FadeIn {
	def apply(n: Node, mili: Int = 400) = {
		new FadeIn(n, mili)
	}
	sealed case class FadeIn(n: Node, mili: Int = 400) extends Command
}

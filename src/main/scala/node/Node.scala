package lola.js

import lola.Parse
import upickle.default._

import lola.interface.{Encode, DecodeCommands, DecodeNodes}
import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom._
import org.scalajs.jquery.{jQuery, JQuery}

object Lola {

	/* Permenant nodes, referenced by id */
	var nodes: scala.collection.mutable.Map[String, Node] = scala.collection.mutable.Map()

	def register(n: Node): Unit = nodes += n.id -> n

	def remove(n: Node): Unit = nodes -= n.id

	def getById(id: String): Option[Node] = nodes get id

	import org.scalajs.dom.ext.Ajax
	import scala.concurrent._
	import scala.scalajs
                .concurrent
                .JSExecutionContext
                .Implicits
                .runNow

	/*
		Send data, wait for a response... decode the message and execute the abstract syntax tree.
	*/
	def get(url: String, timeout: Int = 0, headers: Map[String, String] = Map(), withCredentials: Boolean = false): Unit = {
		Ajax.get(url, "", timeout, headers, withCredentials).onSuccess {
			case xhr => {
				val c = DecodeCommands(xhr.responseText)
				Parse(c)
			}
		}
	}
	
	def post(url: String,  n: Node, timeout: Int = 0, headers: Map[String, String] = Map(), withCredentials: Boolean = false): Unit = {
		Ajax.post(url, write(Parse(n)), timeout, headers, withCredentials).onSuccess {
			case xhr => {
				val c = DecodeCommands(xhr.responseText)
				Parse(c)
			}
		}
	}

}
/*
	JQuery selection
*/
sealed trait JQSelect {

	val id: String

	var cache: Option[JQuery] = None

	def jqSelect: JQuery = cache match {
		case Some(jQuery) => jQuery
		case None => {
			val _jqCache = jQuery(s"#$id")
			cache = Some(_jqCache)
			_jqCache
		}
	}
}
/*
	Javascript Selection
*/
sealed trait Select extends JQSelect {

	def jsSelect: html.Element = {
		jqSelect.get(0).asInstanceOf[html.Element]
	}
}

sealed trait JQAnimation extends JQSelect {

	def slideDown(mili: Int = 400): Unit = {
		jqSelect.slideDown(mili)
	}

	def slideUp(mili: Int = 400): Unit = {
		jqSelect.slideUp(mili)
	}

	def fadeIn(mili: Int = 400): Unit = {
		jqSelect.fadeIn(mili)
	}

	def fadeOut(mili: Int = 400): Unit = {
		jqSelect.fadeOut(mili)
	}
	
}

sealed trait Reaction extends Select {

	def onClick(f: () => Unit): Unit = {
		jsSelect.onclick = (e: dom.MouseEvent) => {
			f()
		}
	}

	def onHover(enter: () => Unit, exit: () => Unit) = {
		jsSelect.onmouseenter = (e: dom.MouseEvent) => {
			enter()
		}
		jsSelect.onmouseleave = (e: dom.MouseEvent) => {
			exit()
		}
	}

	def onKeyUp(f: () => Unit): Unit = {
		jsSelect.onkeyup = (e: dom.KeyboardEvent) => {
			f()
		}
	}
}

sealed trait Attributes extends Select {

	def removeAttribute(attr: String): Unit = {
		jqSelect.removeAttr(attr)
	}

	def getAttribute(attr: String): Option[String] = {
		jsSelect.getAttribute(attr) match {
			case "" => None
			case s => Some(s)
		}
	}

	def setCss(style: Map[String,String]): Unit = {
		for((k,v) <- style) jqSelect.css(k,v)
	} 

	def getCss(k: String): String = {
		jqSelect.css(k)
	}

	def setAttribute(attr: String, value: String): Unit = jsSelect.setAttribute(attr, value)

}

sealed trait Position extends Select with Attributes {

	sealed class Point(val x: Double, val y: Double) {

		def +(p: Point): Point = {
			new Point(x + p.x, y + p.y)
		}

		def avg(p: Point): Point = {
			new Point(x + p.x / 2, y + p.y / 2)
		}
	}

	def position: Point = new Point(jsSelect.offsetLeft, jsSelect.offsetTop)

	def setPosition(p: Point): Unit = {
		val style = Map("position" -> "absolute", "left" -> (p.x.toString + "px"), "top" -> (p.y.toString + "px"))
		setCss(style)
	}
}

sealed class Node(
	val tag: String, 
	val attributes: Map[String, String], 
	val style: Map[String,String], 
	val sText: String, 
	val eText: String, 
	val items: List[Node], 
	val id: String) extends JSApp with Select with JQAnimation with Position with Attributes with Reaction {

	private val atrStr = attributes map { x => x._1 + "=" + "'" + x._2 + "'" } mkString " "
	private val styleStr = style map { x => x._1 + ":" + x._2 + ";" } mkString ""
		
	override def toString = render

	def render = {
		s"""
		<${tag} id=${id} ${atrStr} style="${styleStr}">
			${sText}
			${items mkString ""}
			${eText}
		</${tag}>
		"""
	}

	private def self = this

	def remove: Unit = {
		jqSelect.remove
		Lola.remove(self)
		cache = None
	}
	/*
		Convenient jquery replacement method... must figure out how to utilize this for Update
	*/
	def replace(n: Node): Unit = {
		jqSelect.replaceWith(n.jqSelect)
	}

	object create {
		def apply(): Unit = {
			remove
			jQuery("body").append(render)
			Lola.register(self)
		}
		def apply(item: Node): Unit = {
			item.jqSelect.append(render)
			Lola.register(self)
		}
	}

	def main: Unit = {}

}

package lola.interface

import upickle.default._

object Decode {
	def apply(s: String): Option[Command] = {
		try {
			Some(read[Command](s))
		}
		catch {
			case e: Exception => None
		}
	}
}

object Encode {
	def apply(n: Node): Option[String] = {
		try {
			Some(write(n))
		}
		catch {
			case e: Exception => None
		}
	}
	def apply(c: Command): Option[String] = {
		try {
			Some(write(c))
		}
		catch {
			case e: Exception => None
		}
	}
}
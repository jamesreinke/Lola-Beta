package lola

import upickle.default._

object Parse {

	/*
		Javascript Node -> Interface Node
	*/

	def apply(n: js.Node): interface.Node = new interface.Node(n.tag, n.attributes, n.style, n.sText, n.eText, n.items map { x => Parse(x) }, n.id)

	/*
		Interface Node -> Javascript Node
	*/
	def apply(n: interface.Node): js.Node = lola.js.Lola.getById(n.id) match {
		case Some(node) => node
		case None => new js.Node(n.tag, n.attributes, n.style, n.sText, n.eText, n.items map { x => Parse(x) }, n.id)
	}

	/*
		Interface Command -> Javascript Unit Execution -> Interface Node
	*/
	def apply(c: interface.Command): Unit = c match {
		case interface.Create(n: interface.Node) => Parse(n).create()
		case interface.Delete(n: interface.Node) => Parse(n).remove
		case interface.Css(n: interface.Node, style: Map[String,String]) => Parse(n).setCss(style)
		case interface.OnClick(n: interface.Node, c: interface.Command) => Parse(n).onClick(() => Parse(c))
		case interface.OnHover(n: interface.Node, c: interface.Command, c2: interface.Command) => Parse(n).onHover(() => Parse(c), () => Parse(c2))
		case interface.SlideUp(n: interface.Node, mili: Int) => Parse(n).slideUp(mili)
		case interface.SlideDown(n: interface.Node, mili: Int) => Parse(n).slideDown(mili)
		case interface.FadeIn(n: interface.Node, mili: Int) => Parse(n).fadeIn(mili)
		case interface.FadeOut(n: interface.Node, mili: Int) => Parse(n).fadeOut(mili)
		case interface.Get(url: String) => js.Lola.get(url)
		case interface.Post(url: String, n: interface.Node) => js.Lola.post(url, Parse(n))
	}

	def apply(cms: List[interface.Command]): Unit = {
		for(c <- cms) Parse(c)
	}

}
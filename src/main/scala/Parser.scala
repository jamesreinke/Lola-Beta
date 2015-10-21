package lola


object Parse {

	/*
		Javascript Node -> Interface Node
	*/

	def apply(n: js.Node): interface.Node = new interface.Node(n.tag, n.attributes, n.style, n.sText, n.eText, n.items map { x => Parse(x) }, n.id)

	/*
		Interface Node -> Javascript Node
	*/
	def apply(n: interface.Node): js.Node = new js.Node(n.tag, n.attributes, n.style, n.sText, n.eText, n.items map { x => Parse(x) }, n.id)

	/*
		Interface Command -> Javascript Unit Execution -> Interface Node
	*/
	

}
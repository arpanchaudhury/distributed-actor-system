package sample.remote.adapter

import akka.actor.Actor
import models.{ProductSummary, TransformedProductSummary, StickyNav, TransformedStickyNav}
import spray.json.{JsString, JsObject}

import scala.xml.Node

class TransformActor extends Actor {

  override def receive: Receive = {
    case ps @ ProductSummary(xml: Node) =>
      println(s"received by transformer: $xml")
      sender() ! TransformedProductSummary(ps.xmlToJson(xml))
    case sn @ StickyNav(xml: Node) =>
      println(s"received by transformer: $xml")
      sender() ! TransformedStickyNav(sn.xmlToJson(xml))
  }

}

object XmlToJsonConverter{

  def convert(xml: Node) = {
    val id = (xml \\ "id").head.text
    val name = (xml \\ "name").head.text
    JsObject(Map("id" -> JsString(id), "name" -> JsString(name)))
  }

}

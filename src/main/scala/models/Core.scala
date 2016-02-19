package models

import sample.remote.adapter.XmlToJsonConverter
import spray.json.JsObject

import scala.xml.Node


trait Module
case class ProductSummary(xml: Node) extends Module {

  def xmlToJson(xml: Node) = XmlToJsonConverter.convert(xml)

}

case class StickyNav(xml: Node) extends Module {
  def xmlToJson(xml: Node) = XmlToJsonConverter.convert(xml)
}

trait Result
case class TransformedProductSummary(json: JsObject) extends Result
case class TransformedStickyNav(json: JsObject) extends Result

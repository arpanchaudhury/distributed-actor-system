package sample.remote.adapter

import akka.actor.{Actor, Props}
import models.{TransformedStickyNav, TransformedProductSummary, Result, Module}

class DelegatorActor extends Actor{
  override def receive: Receive = {
    case module: Module =>
      val transformer = context.actorOf(Props[TransformActor])
      transformer ! module
    case result: Result => result match{
      case TransformedProductSummary(json) =>
        val cadc = context.actorOf(Props[CadcActor])
        cadc ! json
      case TransformedStickyNav(json) =>
        val cadc = context.actorOf(Props[CadcActor])
        cadc ! json
    }
  }
}

package sample.remote.adapter

import akka.actor.Actor

class CadcActor extends Actor{

  override def receive: Receive ={
    case json => println(s"received by cadc :$json")
  }
}

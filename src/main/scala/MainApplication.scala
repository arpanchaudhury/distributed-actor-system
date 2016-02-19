import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import models.{ProductSummary, StickyNav}
import sample.remote.adapter.DelegatorActor

import scala.concurrent.duration.DurationInt

object MainApplication {

  def main(args: Array[String]): Unit = {
    startRemoteTransformerWorkerSystem()
    startRemoteCadcSystem()
    startRemoteDelegatorSystem()
  }

  def startRemoteTransformerWorkerSystem(): Unit = {
    ActorSystem("Transformer", ConfigFactory.load("transformer"))
    println("Transformer Started ")
  }

  def startRemoteCadcSystem(): Unit = {
    ActorSystem("Cadc", ConfigFactory.load("cadc"))
    println("Cadc Started")
  }

  def startRemoteDelegatorSystem(): Unit = {
    val system = ActorSystem("DelegationSystem", ConfigFactory.load("remotedelegator"))

    val actor = system.actorOf(Props[DelegatorActor], name = "delegatorActor")

    println("DelegationSystem Started")

    val productSummaryXml =
      <module>
      <id>urn:sony:productSummary:1</id>
      <name>nex5r</name>
      </module>

    val stickyNavXml =
      <module>
      <id>urn:sony:stickyNav:1</id>
      <name>cybershot</name>
      </module>

    import system.dispatcher
    system.scheduler.schedule(1.second, 1.second) {
      actor ! ProductSummary(productSummaryXml)
      actor ! StickyNav(stickyNavXml)
    }
  }
}

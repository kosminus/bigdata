package com.enisei.actorExample

import akka.actor.{Actor, ActorSystem, Props}
import com.enisei.actorExample.Hello.WordCounterActor.Person

object Hello extends App {

  object  WordCounterActor {
    case class Person(name:String)
    object Person {
      def apply(name: String) = new Person(name)
    }
  }


  class WordCounterActor extends Actor {
    import WordCounterActor._
    override def receive: Receive = {
      case Person(name) =>  println(s"textul are lungimea de ${name.length}")
    }
  }

  val actorSystem = ActorSystem("ActorWordCounter")
  val words = actorSystem.actorOf(Props[WordCounterActor])
  words !  Person("Maria")
  words !  Person("Ana")


}

package com.enisei.actorExample

import akka.actor.{Actor, ActorSystem, Props}

object WordCounter extends App {

  class WordCounterActor extends Actor {
    override def receive: Receive = {
      case word:String =>  println(s"textul are lungimea de ${word.length}")
    }
  }

  val actorSystem = ActorSystem("ActorWordCounter")
  val words = actorSystem.actorOf(Props[WordCounterActor])
  words ! "Hello World! "


}

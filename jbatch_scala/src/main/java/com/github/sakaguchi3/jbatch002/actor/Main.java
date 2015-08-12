package com.github.sakaguchi3.jbatch002.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("EchoSystem");
		ActorRef echoActorRef = system.actorOf(Props.create(EchoActor.class), "EchoActor");
		echoActorRef.tell("Hello world", ActorRef.noSender());
		system.stop(echoActorRef);
		system.terminate();
	}
}

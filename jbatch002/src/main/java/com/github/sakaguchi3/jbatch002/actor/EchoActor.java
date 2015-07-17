package com.github.sakaguchi3.jbatch002.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class EchoActor extends UntypedActor {
	public EchoActor() {
		System.out.println("constructor");
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			System.out.println("Got message. " + message);
			ActorRef ref = this.getSender();
			ref.tell(message, ref);
		} else {
			System.out.println("Got unknown type message.");
			unhandled(message);
		}
	}
}

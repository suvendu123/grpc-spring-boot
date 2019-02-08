package com.suv.grpc.test;

import com.suv.grpc.helloworld.Greeting;
import com.suv.grpc.helloworld.HelloWorldServiceGrpc;
import com.suv.grpc.helloworld.Person;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PlainJavaTest {
	
	 private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;

	 public static void main(String[] args) {
		 PlainJavaTest test = new PlainJavaTest();
		 test.sayHello("Suvendu", "Ban");
	}
	  public PlainJavaTest() {
	    ManagedChannel managedChannel = ManagedChannelBuilder
	        .forAddress("localhost", 6565).usePlaintext().build();

	    helloWorldServiceBlockingStub =
	        HelloWorldServiceGrpc.newBlockingStub(managedChannel);
	  }

	  public String sayHello(String firstName, String lastName) {
	    Person person = Person.newBuilder().setFirstName(firstName)
	        .setLastName(lastName).build();
	    System.out.println("client sending {}"+ person);

	    Greeting greeting =
	        helloWorldServiceBlockingStub.sayHello(person);
	    System.out.println("client received {}"+ greeting);

	    return greeting.getMessage();
	  }

}

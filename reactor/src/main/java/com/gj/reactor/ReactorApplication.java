package com.gj.reactor;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

//@SpringBootApplication
public class ReactorApplication {
	
	private  <T> T identityWithThreadLogging(T el, String operation) {
		  System.out.println(operation + " -- " + el + " -- " + 
		    Thread.currentThread().getName());
		  return el;
		}
	public  void flatMapWithoutChangingScheduler() {
		  Flux.range(1, 3).
		    map(n -> identityWithThreadLogging(n, "map1")).
		    subscribeOn(Schedulers.parallel()).
		    subscribe(n -> {
		      this.identityWithThreadLogging(n, "subscribe");
		      System.out.println(n);
		    });
		}
/**
 Reactive streams are not always asynchronous. 
 The threading behavior primarily depends on the source and can be influenced with operators.
  The nice thing, though, is that the code stays the same.
We can use the same programming model and the same pipeline for both synchronous and asynchronous streams.
 
 */
	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		ExecutorService executorService2 = Executors.newFixedThreadPool(10);
		// multiple_Subscriber_For_A_Flux_SeparateThread_Per_Subscriber2(executorService2,executorService);
		demo_Connectable_Stream();
		try {
			Thread.sleep(150000);
			// executorService.shu
			executorService.shutdown();
			executorService2.shutdown();
			System.out.println("ended :" + Thread.currentThread().getName());
		} catch (Exception e) {

		}
	}
	private static Mono<String> demoMonoDoOnSuccess() {
	
    return  getMono().doOnSuccess(u->{System.out.print("Doonsuccess::");})
      .map(t->t);
	  
    }
	
	private static Mono<String> getMono(){
		return Mono.just("test");
	}
	//todo
	public void demo_onError_onComplete() {
		
	}
	//parallel Stream
	public static void demo_Connectable_Stream() {
		Flux<Integer> fx=Flux.range(1, 100).log()
		.doOnSubscribe(s->{System.out.println(s);});
		ConnectableFlux<Integer> connFlux=fx.publish();
		
		connFlux.subscribe(t->{System.out.println("First Subscriber: "+t);});
		connFlux.subscribe(t->{System.out.println("Second Subscriber: "+t);});
		
		connFlux.connect();
	}
	
	//callback method subscribe		
		public static void demo_subscribe_Stream() {
			
			Flux.range(1, 100).log()
			.subscribe(new Subscriber<Integer>() {

				@Override
				public void onSubscribe(Subscription s) {
					//s.request(n); you can manage backpressure from here
					 s.request(Long.MAX_VALUE); //imp step
					System.out.println(s);
				}

				

				@Override
				public void onError(Throwable t) {
					
					System.out.println(t);
				}

				@Override
				public void onComplete() {
				System.out.println("on complete");
					
				}

				@Override
				public void onNext(Integer t) {
					System.out.println(t);
					
				}
			});
			
		}
/**
 * public Flux<T> take(long n)
public Flux<T> take(Duration timespan)
public Flux<T> take(Duration timespan, Scheduler timer)
public Flux<T> takeLast(int n)
public Flux<T> takeUntil(Predicate<? super T> predicate)
public Flux<T> takeUntilOther(Publisher<?> other)
public Flux<T> takeWhile(Predicate<? super T> continuePredicate)
 */
	
	public static void demoParallelFlux() {
		Flux.range(1, 100)
		    .parallel()
		    .runOn(Schedulers.elastic())
		    .map(val->val*2)
		    .subscribe(val->{System.out.println("Thread name and value :"+Thread.currentThread().getName()+":"+val);});
	}
	
	public static void demoFluxRangeSubscribeOnDifferentThread(ExecutorService ser) {
		Flux.range(1, 10).subscribeOn(Schedulers.fromExecutor(ser)).subscribe(
	    		  successValue -> {System.out.println("Thread name and value :"+Thread.currentThread().getName()+":"+successValue);},
	    		  error -> System.out.println(error.getMessage()),
	    		  () -> System.out.println("Flux consumed.")
	    		);
	}
	
	public static void demoFluxRange() {
		Flux.range(1, 100)
		    .buffer(12)
		    .flatMap(val->{System.out.println(val); return Flux.fromIterable(val);})
		    .subscribe(
	    		  successValue -> {
	    			  try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			  System.out.println("Thread name and value :"+Thread.currentThread().getName()+":"+successValue);
	    			  },
	    		  error -> System.out.println(error.getMessage()),
	    		  () -> System.out.println("Flux consumed.")
	    		);
	}
	public static void multiple_Subscriber_For_A_Flux_SeparateThread_Per_Subscriber(ExecutorService executorService2 ) {
		
		//check the output...as the names of the method, we get subscribers working in separate threads, publish and subscribe  on the same thread.
		

		Flux<Long> startFlux = Flux.interval(Duration.ofMillis(1000)).take(10).share();
		for (int i = 0; i < 10; i++) {
			final int subscriptionNumber = i;
			Flux<Long> outputFlux = Flux.from(startFlux)
					.publishOn(Schedulers.fromExecutorService(executorService2, "anesh2"))
					.map(t -> {
						System.out.println("Thread Name :" + Thread.currentThread().getName() + " Flux Value:  " + t);
						return t;
					});
			outputFlux.subscribe(out -> System.out.println("Thread Name :" + Thread.currentThread().getName()
					+ " Subscription : " + subscriptionNumber + " Flux Value:  " + out));
		}

	}
	
	
	public static void multiple_Subscriber_For_A_Flux_SeparateThread_Per_Subscriber2(ExecutorService executorService2,ExecutorService executorService ) {
		
 //in this all the subsribers are handled in same thread
		Flux<Long> startFlux = Flux.interval(Duration.ofMillis(1000)).take(10).share();
		for (int i = 0; i < 10; i++) {
			final int subscriptionNumber = i;
			Flux<Long> outputFlux = Flux.from(startFlux).publishOn(Schedulers.fromExecutorService(executorService, "anesh"));
					
			outputFlux.subscribeOn(Schedulers.fromExecutorService(executorService2, "anesh2")).subscribe(
					out -> {
						try {
						Thread.sleep(1000);
					}catch(Exception r) {}
						System.out.println("Thread Name :" + Thread.currentThread().getName()
					
					+ " Subscription : " + subscriptionNumber + " Flux Value:  " + out);
						},error->{System.out.println(error);});
		}

	}
	}




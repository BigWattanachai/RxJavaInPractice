import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by BiG on 4/1/2017 AD.
 */
public class RxJavaInPractice {
  public static void main(String[] args) {
    observableJust();
    publishSubject();
    replaySubject();
    behaviorSubject();
    asyncSubject();
    handleTheError();
    unsubscribe();
    unsubscribe2();
    onCompleted();
    observableNever();
    observableError();
    observableCreate();
  }

  private static void observableCreate() {
    Observable<String> values = Observable.create(o -> {
      o.onNext("Hello");
      o.onComplete();
    });
    values.subscribe(
      v -> System.out.println("Received: " + v),
      e -> System.out.println("Error: " + e),
      () -> System.out.println("Completed")
    );
  }

  private static void observableError() {
    Observable<String> values = Observable.error(new Exception("Oops"));
    values.subscribe(
      v -> System.out.println("Received: " + v),
      e -> System.out.println("Error: " + e),
      () -> System.out.println("Completed")
    );
  }

  private static void observableNever() {
    Observable<String> values = Observable.never();
    values.subscribe(
      v -> System.out.println("Received: " + v),
      e -> System.out.println("Error: " + e),
      () -> System.out.println("Completed")
    );
  }

  private static void onCompleted() {
    Subject<Integer> values = ReplaySubject.create();
    values.subscribe(
      v -> System.out.println("First: " + v),
      e -> System.out.println("First: " + e),
      () -> System.out.println("Completed")
    );
    values.onNext(0);
    values.onNext(1);
    values.onComplete();
    values.onNext(2);
  }

  private static void unsubscribe2() {
    Subject<Integer> values = ReplaySubject.create();
    Disposable subscription1 = values.subscribe(
      v -> System.out.println("First: " + v)
    );
    values.subscribe(
      v -> System.out.println("Second: " + v)
    );
    values.onNext(0);
    values.onNext(1);
    subscription1.dispose();
    System.out.println("Unsubscribed first");
    values.onNext(2);
  }

  private static void unsubscribe() {
    Subject<Integer> values = ReplaySubject.create();
    Disposable subscription = values.subscribe(
      System.out::println,
      System.err::println,
      () -> System.out.println("Done")
    );
    values.onNext(0);
    values.onNext(1);
    subscription.dispose();
    values.onNext(2);
  }

  private static void handleTheError() {
    Subject<Integer> s = ReplaySubject.create();
    s.subscribe(
      System.out::println,
      System.err::println);
    s.onNext(0);
    s.onError(new Exception("Oops"));
  }

  private static void asyncSubject() {
    AsyncSubject<Integer> s = AsyncSubject.create();
    s.subscribe(System.out::println);
    s.onNext(0);
    s.onNext(1);
    s.onNext(2);
    s.onComplete();
  }

  private static void behaviorSubject() {
    BehaviorSubject<Integer> s = BehaviorSubject.create();
    s.onNext(0);
    s.onNext(1);
    s.onNext(2);
    s.subscribe(v -> System.out.println("Late: " + v));
    s.onNext(3);
  }

  private static void replaySubject() {
    ReplaySubject<Integer> s = ReplaySubject.create();
    s.subscribe(v -> System.out.println("Early:" + v));
    s.onNext(0);
    s.onNext(1);
    s.subscribe(v -> System.out.println("Late: " + v));
    s.onNext(2);
  }

  private static void observableJust() {
    List<String> list = Arrays.asList("Android", "Ubuntu", "Mac os");
    Observable<List<String>> listObservable = Observable.just(list);
    listObservable.subscribe(new Observer<List<String>>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(List<String> strings) {
        strings.forEach(System.out::println);
      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {

      }
    });
  }

  private static void publishSubject() {
    PublishSubject<Integer> subject = PublishSubject.create();
    subject.onNext(1);
    subject.subscribe(System.out::println);
    subject.onNext(2);
    subject.onNext(3);
    subject.onNext(4);
  }
}

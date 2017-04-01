import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

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

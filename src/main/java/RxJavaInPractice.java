import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by BiG on 4/1/2017 AD.
 */
public class RxJavaInPractice {
  public static void main(String[] args) {
    observableJust();
    publishSubject();
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

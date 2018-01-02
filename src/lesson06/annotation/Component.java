package lesson06.annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*애노테이션 유지정책
 * RetentionPolicy.SOURCE = 소스파일에서만 유지, 컴파일할때 제거됨, 즉 클래스 파일에 애노테이션 정보가 남아있지 않는다.
 * RetentionPolicy.CLASS = 클래스 파일에 기록됨, 실행 시에는 유지되지 않음, 즉 실행 중에서는 클래스에 기록된 애노테이션 값을 꺼낼수 없음(기본정책)
 * RetentionPolicy.RUNTIME = 클래스 파일에 기록됨, 실행 시에도 유지됨, 즉, 실행 중에 클래스에 기록된 애노테이션 값을 참조할 수 있음.
 * */ 
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
  String value() default "";
}
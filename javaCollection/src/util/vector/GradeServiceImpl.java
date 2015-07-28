package util.vector;
//전체에서 특정 글자 바꾸기 단축키 ctrl + F
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/*
 프로그램을 코딩하다 보면 
 자료구조를 다루는 데 있어서 
 CRUD 액션의 반복을 경험하게 됩니다.
 Create : 입력, 생산
 Read : 조회, 출력
 Update : 수정 -> 저장된 값을 수정 DB
 Delete : 삭제 -> 완전삭제 DB, null 값으로 바꾸는 삭제
 * */
public class GradeServiceImpl implements GradeService {
	// 필드에 아래 메소드들이 공유할 자료구조를 뭘 쓸까?
	// 1. ArrayList 2.Map 3.Stack 4.HashMap
	// 순서 o, 중복 o
	private List<Grade> vec = new ArrayList<Grade>(); // grade를 담는 구조
	Grade grade = new Grade(); // 디폴트 생성자가 있어서 에러 제거

	@Override
	public void input(Grade grade) {
		// 성적표 입력
		// 만약 자료구조로 배열을 사용했다면 vec[0] = hong;
		vec.add(grade);
	}

	@Override
	public void printList() {
		// 전체 출력
		System.out.println(vec.toString());

	}

	@Override
	public String searchGradeByHakbun(String hakbun) {
		// 학번으로 검색
		String msg = "";
		Grade grade = null;// 지변으로 인스턴스를 선언했으므로 초기화함
		// List 계열의 클래스 길이는 size() 구한다.
		for (int i = 0; i < vec.size(); i++) {
			// 만약 vec가 배열이라면
			// vec.get(i)
			String searchHakbun = vec.get(i).getHakbun();
			String anotherString;
			// 객체.메소드.메소드.메소드.메소드 이런
			// 패턴는 반드시 return값이 있는
			// 메소드들 끼리 연결시에만 가능하다.
			// 이런 방식을 메소드 체인리라고 함
			if (hakbun.equalsIgnoreCase(searchHakbun)) {
				String name = vec.get(i).getName();
				int kor = vec.get(i).getKor();
				int eng = vec.get(i).getEng();
				int math = vec.get(i).getMath();
				grade = new Grade(searchHakbun, name, kor, eng, math);
				msg = grade.toString();
				break;// 중간이라도 학번이 일치하면 그대로 스톱

			} else {
				msg = "조회하는 학번이 없습니다.";
			}

		}
		return msg;
	}

	@Override
	public void ascGradeTotal() {
		//Java API 중에서 정렬담당 클래스
		//Comparator
		//인터페이스를 구현한 익명 내부 클래스
		//anonymous inner class
		//새로운 클래스를 만들기보다 한방에 생성하는거..... 
		Comparator<Grade> desc = new Comparator<Grade>() {
			
			@Override
			public int compare(Grade g1, Grade g2) {
				//삼항연산자
				/*
				 if(condition){
				 -->true 면 statment 실행
				 }else{
				 --->false면 statment 실행
				 }
				 (c)
				 (조건식)? 참 : 거짓;
				 * 
				 * int temp = 0;
				if ((g1.getTotal()<g2.getTotal())) {
					temp 1;
				} else {
					if (g1.getTotal()==g2.getTotal()) {
						temp 0;
					} else {
						temp -1;
					}
				} */
				return (g1.getTotal()<g2.getTotal())  ? 1 : 
					   (g1.getTotal()==g2.getTotal()) ?  0:-1;
			}
		
		};  //위에 기준으로 바뀐 값을 
		Collections.sort(vec,desc);
		System.out.println(vec.toString());
	}

	// 성적증명

	@Override
	public Vector<Grade> searchGradeByname(String name) {
		Vector<Grade> temp = new Vector<Grade>();

		Grade grade = null;

		for (int i = 0; i < vec.size(); i++) {
			String searchName = vec.get(i).getName();
			// 고정값(파라미터).equals(변수값)

			if (name.equalsIgnoreCase(searchName)) {

				grade = new Grade(searchName, vec.get(i).getHakbun(), vec
						.get(i).getKor(), vec.get(i).getEng(), vec
						.get(i).getMath());
				// arr[i] = "홍길동";
				temp.add(grade);
				// temp.put();
				break;
			} else {
				// temp 라는 벡터를 완전히 비워서 null로 리턴
				temp.remove(new Grade());
			}

		}
		return temp;
	}

	@Override
	public void descByTotal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ascByName() {
		
		Comparator<Grade> asc = new Comparator<Grade>() {

			@Override
			public int compare(Grade g1, Grade g2) {
				//값(vlaue) 이 int 타입이 아니고 
				//String 타입의 우선순위 결정할떄는...
				//compareTo() 메소드를 사용해야 한다.
			return g1.getName().compareTo(g2.getName()); 
			}
		};
		Collections.sort(vec,asc);
		System.out.println(vec.toString());
		
	}
	
	
}



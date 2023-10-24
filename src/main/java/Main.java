import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 프로그램 실행 ==");
        Scanner sc = new Scanner(System.in);
//            List<> = new ArrayList<>();

        while (true) {
            System.out.print("명령 >> ");
            String command = sc.nextLine();
            if (command.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (command.equals("등록")) {

            } else if (command.equals("수정")) {

            } else if (command.equals("삭제")) {

            }
        }
    }
}
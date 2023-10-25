import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long lastId = 0;
        System.out.println("== 프로그램 실행 ==");
        Scanner sc = new Scanner(System.in);
        List<Article> articleList = new ArrayList<>();
        List<Member> memberList = new ArrayList<>();

        while (true) {
            System.out.print("명령 >> ");
            String command = sc.nextLine().trim();
            if (command.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (command.equals("등록")) {
                lastId++;
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String content = sc.nextLine().trim();

                System.out.println(lastId + "번 게시글이 등록되었습니다.");

                Article article = new Article(lastId, title, content);
                articleList.add(article);
            } else if (command.equals("목록")) {
                if (articleList.isEmpty()) {
                    System.out.println("등록된 게시물이 없습니다.");
                } else {
                    System.out.println("번호 / 제목 / 내용");
                    for (Article article : articleList) {
                        System.out.printf("%d / %s / %s\n", article.getId(), article.getTitle(), article.getContent());
                    }
                }
            } else if (command.equals("삭제")) {
                System.out.print("삭제할 번호를 입력해주세요. >> ");
                long id = Long.parseLong(sc.nextLine());

                long foundIndex = -1;

                for (int i = 0; i < articleList.size(); i ++) {
                    Article article = articleList.get(i);
                    if (article.getId() == id) {
                        foundIndex = id;
                        articleList.remove(article);
                        break;
                    }
                }

                if (foundIndex == -1) {
                    System.out.println(id + "번째 게시물은 존재하지 않습니다.");
                } else {
                    System.out.println(id + "번째 게시글이 삭제 되었습니다.");
                }
            } else if (command.equals("수정")) {
                System.out.printf("수정할 번호를 입력해주세요. >> ");
                long id = Long.parseLong(sc.nextLine());

                long foundIndex = -1;

                for (int i = 0; i < articleList.size(); i ++) {
                    Article article = articleList.get(i);
                    if (article.getId() == id) {
                        foundIndex = id;

                        System.out.println("기존 제목 : " + article.getTitle());
                        System.out.print("수정 제목 : ");
                        String title = sc.nextLine().trim();
                        article.setTitle(title);
                        System.out.println("기존 제목 : " + article.getContent());
                        System.out.print("수정 내용 : ");
                        String content = sc.nextLine().trim();
                        article.setContent(content);

                        break;
                    }
                }

                if (foundIndex == -1) {
                    System.out.println(id + "번째 게시물은 존재하지 않습니다.");
                } else {
                    System.out.println(id + "번째 게시글이 수정 되었습니다.");
                }
            }
        }
        sc.close();
        System.out.println("== 프로그램 종료 ==");
    }
}
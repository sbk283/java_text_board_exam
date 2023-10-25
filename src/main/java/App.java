import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {
        System.out.println("== 프로그램 실행 ==");

        long lastId = 0;
        List<Article> articleList = new ArrayList<>();
        List<Member> memberList = new ArrayList<>();
        Member loginedMember = null;
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.print("명령 >> ");
            String command = sc.nextLine().trim();
            if (command.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (command.equals("회원가입")) {
                String userId;
                String password;
                String passwordConfirm;

                while (true) {
                    System.out.print("아이디 >> ");
                    userId = sc.nextLine().trim();
                    boolean duplicatedUserId = false;

                    for (Member member : memberList) {
                        if (member.getUserId().equals(userId)) {
                            duplicatedUserId = true;
                        }
                    }

                    if (duplicatedUserId) {
                        System.out.println("존재하는 아이디 입니다.");
                        continue;
                    }
                    break;
                }
                while (true) {
                    System.out.print("비밀번호 >> ");
                    password = sc.nextLine();
                    System.out.print("비밀번호 확인 >> ");
                    passwordConfirm = sc.nextLine();
                    if (!password.equals(passwordConfirm)) {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                        continue;
                    }
                    break;
                }
                LocalDate now = LocalDate.now();
                Member member = new Member(userId, password, now.toString());
                memberList.add(member);

                System.out.println(userId + "님 회원가입이 완료되었습니다.");
            } else if (command.equals("로그인")) {
                if (command.equals("로그인")) {
                    boolean checkedUserId = false;
                    Member member = null;
                    System.out.print("아이디 >> ");
                    String userId = sc.nextLine();
                    System.out.print("비번 >> ");
                    String password = sc.nextLine();
                    for (Member value : memberList) {
                        if (value.getUserId().equals(userId)) {
                            member = value;
                            checkedUserId = true;
                            break;
                        }
                    }
                    if (!checkedUserId) {
                        System.out.println("해당 회원이 존재하지 않습니다.");
                        continue;
                    }
                    if (!member.getPassword().equals(password)) {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                        continue;
                    }
                    loginedMember = member;

                    System.out.println("로그인 성공!" + loginedMember.getUserId() +"님 환영합니다.");
                }
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
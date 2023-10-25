import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
        LocalDate now2 = LocalDate.now();

        Member member1 = new Member(1,"user1","1234",now2.toString());
        memberList.add(member1);

        while (true) {
            System.out.print("명령 >> ");
            String command = sc.nextLine().trim();
            if (command.equals("종료") || command.equals("whdfy")) {
                break;
            } else if (command.equals("회원가입") || command.equals("ghldnjsrkdlq")) {
                String userId;
                String password;
                String passwordConfirm;

                if (loginedMember != null) {
                    System.out.println("이미 로그인 중입니다.");
                    continue;
                }
                while (true) {
                    System.out.print("아이디 >> ");
                    userId = sc.nextLine().trim();
                    boolean duplicatedUserId = false;

                    for (int i = 0; i < memberList.size(); i++) {
                        if (memberList.get(i).getUserId().equals(userId)) {
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
                    if (password.equals(passwordConfirm) == false) {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                        continue;
                    }
                    break;
                }
                LocalDate now = LocalDate.now();
                Member member = new Member(userId, password, now.toString());
                memberList.add(member);

                System.out.println(userId + "님 회원가입이 완료되었습니다.");
            } else if (command.equals("로그인") || command.equals("fhrmdls")) {
                if (loginedMember != null) {
                    System.out.println("이미 로그인 중입니다.");
                    continue;
                }
                    boolean checkedUserId = false;
                    Member member = null;

                    System.out.print("아이디 >> ");
                    String userId = sc.nextLine();
                    System.out.print("비번 >> ");
                    String password = sc.nextLine();

                    for (int i = 0; i < memberList.size(); i++) {
                        if (memberList.get(i).userId.equals(userId)) {
                            checkedUserId = true;
                            member = memberList.get(i);
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

            } else if (command.equals("로그아웃") || command.equals("fhrmdkdnt")) {
                if (loginedMember == null){
                    System.out.println("로그인 상태가 아닙니다.");
                    continue;
                }
                loginedMember = null;
                System.out.println("로그인 처리가 완료되었습니다.");
            } else if (command.equals("등록") || command.equals("emdfhr")) {
                if (loginedMember == null) {
                    System.out.println("로그인시 이용 가능한 기능입니다.");
                    continue;
                }
                lastId++;
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String content = sc.nextLine().trim();

                System.out.println(lastId + "번 게시글이 등록되었습니다.");

                Article article = new Article(lastId, title, content, loginedMember.getUserId());

                articleList.add(article);

            } else if (command.equals("목록") || command.equals("ahrfhr")) {
                if (articleList.isEmpty()) {
                    System.out.println("등록된 게시물이 없습니다.");
                } else {
                    System.out.println("번호 / 제목 / 내용");
                    for (int i = 0; i < articleList.size(); i++) {
                        Article article = articleList.get(i);
                        System.out.printf("%d / %s / %s / %s\n", article.getId(), article.getTitle(), article.getContent(), article.getUserId());
                    }
                }
            } else if (command.equals("삭제") || command.equals("tkrwp")) {
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
            } else if (command.equals("수정") || command.equals("tnwjd")) {
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
        if (loginedMember != null){
            System.out.println("로그아웃 처리되었습니다.");
            loginedMember = null;
        }
        System.out.println("== 프로그램 종료 ==");
    }
}
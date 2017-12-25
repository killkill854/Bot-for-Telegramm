import java.util.ArrayList;

public class User {
    public    boolean tired = false;
    public     int dollars = 0;
    public    boolean[] knowLanguage = {true, false, false, false, false};
    public   ArrayList companies = new ArrayList();

    String getInfo () {

        String result = "Ваш счёт: ";
        for (int i = 0; i < dollars; i = i + 1) {
            result = result + "$";
        }

//        напечатайИзвестныеЯзыки();


        result += "Компании, в которых вы работаете: ";
        for (int i = 0; i < companies.size(); ++i) {
            System.out.println("- " + companies.get(i));
        }
        return result;
    }



//    void напечатайИзвестныеЯзыки() {
//        System.out.println("Вы знаете следующие языки: ");
//        for (int i = 0; i < Main.languages.length; i++) {
//            if (knowLanguage[i] == true) {
//                System.out.println(Main.languages[i]);
//            }
//        }
//    }


}
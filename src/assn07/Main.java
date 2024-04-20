package assn07;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static <K> void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        // your code below


        // infite loop to go back to "Enter master password"

        String masspass = "";
        Scanner in = new Scanner(System.in);
        while (!passwordManager.checkMasterPassword(masspass)){
            System.out.println("Enter Master Password");
            masspass = in.nextLine();
        }

        String command = "";
        while (!command.equals("Exit")) {
//            System.out.println("\nEnter your next command: ");
            command = in.nextLine();
            if (command.equals("New password")) {
                String website = in.nextLine();
                String pass = in.nextLine();
                passwordManager.put(website, pass);
                System.out.println("New password added");
            } else if (command.equals("Get password")) {
                String website = in.nextLine();
                String res = passwordManager.get(website);
                if (res == null) {
                    System.out.println("Account does not exist");
                } else {
                    System.out.println(res);
                }
            } else if (command.equals("Delete account")) {
                String website = in.nextLine();
                String res = passwordManager.remove(website);
                if (res == null) {
                    System.out.println("Account does not exist");
                } else {
                    System.out.println("Account deleted");
                }
            } else if (command.equals("Check duplicate password")) {
                String pass = in.nextLine();
                List<String> res = passwordManager.checkDuplicate(pass);
                if (res == null) {
                    System.out.println("No account uses that password");
                } else {
                    System.out.println("Websites using that password:");
                    for (String temp : res) {
                        System.out.println(temp);
                    }
                }
            } else if (command.equals("Get accounts")) {
                Set<String> keySet = passwordManager.keySet();
                System.out.println("Your accounts:");
                Iterator itr = keySet.iterator();
                while (itr.hasNext()) {
                    System.out.println(itr.next());
                }
            } else if (command.equals("Generate safe random password")) {
                int len = in.nextInt();
                if (len < 4) { len=4; }
                System.out.println(passwordManager.generatesafeRandomPassword(len));
            } else {
                System.out.println("Command not found");
            }
        }



    }
}

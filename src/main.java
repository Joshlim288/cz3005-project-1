import java.util.Scanner;

public class main {

    public static void main(String[] args){
        Algorithm.initializeData();
        Algorithm toRun = null;
        Scanner sc = new Scanner(System.in);
        int menuOption = 0, startNode = 0, endNode = 0;
        while (menuOption != 4) {
            System.out.print(
                "CZ3005 AI Project 1\n" +
                "1 - Task 1\n"  +
                "2 - Task 2\n"  +
                "3 - Task 3\n"  +
                "4 - Exit\n"    +
                "Choose task to run: "
            );

            try{
                menuOption = Integer.parseInt(sc.nextLine());
                switch(menuOption){
                    case(1) -> toRun = new BFS();
                    case(4) -> {
                        sc.close();
                        return;
                    }
                }
                System.out.print("Enter start node: ");
                startNode = Integer.parseInt(sc.nextLine());
                System.out.print("Enter end node: ");
                endNode = Integer.parseInt(sc.nextLine());
                toRun.run(startNode, endNode);
                System.out.print("Press any key to continue");
                sc.nextLine();
            } catch(Exception e) {
                System.out.println("Enter a valid input");
                continue;
            }
        }
    }
}

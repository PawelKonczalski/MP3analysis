package pl.sda.mp3analysis;

import pl.sda.mp3analysis.sevice.SongService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
//
//        SessionFactory sf = new Configuration()
//                .configure()
//                .buildSessionFactory();
//
//        Session session = sf.openSession();

        SongService songService = new SongService();
        Scanner sc = new Scanner(System.in);
        int operator = -1;
        while(operator!=0) {
            System.out.println("1 - save mp3 file from direct folder to db\n2 - show file list in db" +
                    "\n3 - find file by title in db\n4 - find file by artist in db\n0 - exit\nChoose operation: ");
            operator = sc.nextInt();
            if (operator < 0 || operator > 4){
                System.out.println("Unknown operation!!!\n");
            } else {
                switch (operator) {
                    case 1:
                        System.out.print("Folder directory (use double \\ to next folder directory): ");
                        String pp = sc.next();
                        System.out.println(pp);
                        songService.save(pp);
                        break;
                    case 2:
                        songService.findAll();
                        break;
                    case 3:
                        System.out.print("Insert song title to found: ");
                        sc.nextLine();
                        songService.findByTitle(sc.nextLine());
                        break;
                    case 4:
                        System.out.print("Insert song artist to found: ");
                        songService.findByArtist(sc.next());
                        break;
                }
            }
        }
    }
}

package backend.qlgiaibongda;

import java.util.ArrayList;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
        int numberOfTeams = 4; // Số lượng đội thi đấu
        List<String> teams = new ArrayList<>(); // Danh sách tên đội

        // Tạo danh sách các đội
        for (int i = 1; i <= numberOfTeams; i++) {
            teams.add("Đội " + i);
        }
        int soVong = 0;
        // Tạo lịch thi đấu lượt đi
        System.out.println("Lịch thi đấu lượt đi:");
        for (int i = 0; i < numberOfTeams - 1; i++) {
            System.out.println("Vòng " + (i + 1) + ":");
            soVong = i + 1;
            for (int j = 0; j < numberOfTeams / 2; j++) {
                int team1 = j;
                int team2 = numberOfTeams - 1 - j;

                System.out.println(teams.get(team1) + " vs " + teams.get(team2));
            }

            // Di chuyển đội cuối lên trên
            String lastTeam = teams.remove(numberOfTeams - 1);
            teams.add(1, lastTeam);
        }

        // Tạo lịch thi đấu lượt về
        System.out.println("Lịch thi đấu lượt về:");
        for (int i = 0; i < numberOfTeams - 1; i++) {
            soVong = soVong + 1;
            System.out.println("Vòng " + (soVong) + ":");

            for (int j = 0; j < numberOfTeams / 2; j++) {
                int team1 = numberOfTeams - 1 - j;
                int team2 = j;

                System.out.println(teams.get(team1) + " vs " + teams.get(team2));
            }

            // Di chuyển đội cuối lên trên
            String lastTeam = teams.remove(numberOfTeams - 1);
            teams.add(1, lastTeam);

        }
    }
}

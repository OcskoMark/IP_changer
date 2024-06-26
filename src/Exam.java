import java.util.Random;
import java.util.Scanner;

public class Exam {
    private static final int NUMBER_OF_TASKS = 10;
    private static final int POINTS_TO_CORRECT_ANSWER = 4;
    private static final int MAX_POINTS = NUMBER_OF_TASKS * POINTS_TO_CORRECT_ANSWER;

    public Exam() {
        doExam();
    }

    private int readAndEvaluateAnswer(String expectedAnswer) {
        Scanner reader = new Scanner(System.in);
        String answer = reader.nextLine();

        if (!IPAddress.checkIPAddressIsValid(answer)) {
            return 0;
        }

        if (expectedAnswer.equals(answer)) {
            return POINTS_TO_CORRECT_ANSWER;
        } else {
            int earnedPoints = 0;
            String[] answerOctets = answer.split("\\.");
            String[] expectedOctets = expectedAnswer.split("\\.");

            for (int i = 0; i < expectedOctets.length; i++) {
                if (expectedOctets[i].equals(answerOctets[i])) {
                    earnedPoints += POINTS_TO_CORRECT_ANSWER / expectedOctets.length;
                }
            }

            return earnedPoints;
        }
    }

    private int generateAndEvaluateTask() {
        Random random = new Random();
        IPAddress ipAddress = new IPAddress();

        if ((random.nextInt(100) % 2) == 0) {
            System.out.println("Please, type the following IP address in decimal form!");
            System.out.println(ipAddress.getBinaryAddress());
            return readAndEvaluateAnswer(ipAddress.getDecimalAddress());
        } else {
            System.out.println("Please, type the following IP address in binary form!");
            System.out.println(ipAddress.getDecimalAddress());
            return readAndEvaluateAnswer(ipAddress.getBinaryAddress());
        }
    }

    private void getExamResult(int earnedPoints) {
        double[] pointsBoundaries = {0.5, 0.65, 0.8, 0.9};
        double scoreProportion = (double) earnedPoints / MAX_POINTS;
        int grade = 1;

        for (int i = 0; i < pointsBoundaries.length; i++) {
            if (scoreProportion < pointsBoundaries[i]) {
                grade = i + 1;
            }
        }

        System.out.println("You earned " + earnedPoints + " points from the " + MAX_POINTS + " this is " + (scoreProportion * 100) + "%.");
        System.out.println("Your grade: " + grade);
        System.out.println();
    }

    private void doExam() {
        int earnedPoints = 0;

        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
            System.out.println();
            System.out.println((i + 1) + ". task:");

            earnedPoints += generateAndEvaluateTask();
        }

        getExamResult(earnedPoints);
    }
}

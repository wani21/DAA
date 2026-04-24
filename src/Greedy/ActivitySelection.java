package Greedy;

import java.util.*;

class Activity {
    int id, start, end;

    Activity(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}

public class ActivitySelection {

    public static void selectActivities(Activity[] arr) {

        // Step 1: Sort by end time
        Arrays.sort(arr, new Comparator<Activity>() {
            public int compare(Activity a, Activity b) {
                return a.end - b.end;
            }
        });

        System.out.println("Selected Activities:");

        // Step 2: Select first activity
        int lastEnd = arr[0].end;
        System.out.println("Activity " + arr[0].id +
                " -> (" + arr[0].start + ", " + arr[0].end + ")");

        // Step 3: Select remaining activities
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].start >= lastEnd) {
                System.out.println("Activity " + arr[i].id +
                        " -> (" + arr[i].start + ", " + arr[i].end + ")");
                lastEnd = arr[i].end;
            }
        }
    }

    public static void main(String[] args) {

        Activity[] activities = {
                new Activity(1, 1, 3),
                new Activity(2, 2, 4),
                new Activity(3, 3, 5),
                new Activity(4, 0, 6),
                new Activity(5, 5, 7),
                new Activity(6, 8, 9)
        };

        selectActivities(activities);
    }
}
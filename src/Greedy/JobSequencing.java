package Greedy;

import java.util.*;

class Job {
    int id, deadline, profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {

    public static void scheduleJobs(Job[] jobs) {

        // Step 1: Sort jobs by profit (descending)
        Arrays.sort(jobs, new Comparator<Job>() {
            public int compare(Job a, Job b) {
                return b.profit - a.profit;
            }
        });

        // Step 2: Find maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline) {
                maxDeadline = job.deadline;
            }
        }

        // Step 3: Create slot array
        int[] slot = new int[maxDeadline + 1];
        Arrays.fill(slot, -1);

        int totalProfit = 0;

        System.out.println("Selected Jobs:");

        // Step 4: Schedule jobs
        for (int i = 0; i < jobs.length; i++) {

            for (int j = jobs[i].deadline; j > 0; j--) {

                if (slot[j] == -1) {
                    slot[j] = i;
                    totalProfit += jobs[i].profit;

                    System.out.println("Job " + jobs[i].id +
                            " (Deadline: " + jobs[i].deadline +
                            ", Profit: " + jobs[i].profit + ")");
                    break;
                }
            }
        }

        System.out.println("Total Profit: " + totalProfit);
    }

    public static void main(String[] args) {

        Job[] jobs = {
                new Job(1, 2, 100),
                new Job(2, 1, 19),
                new Job(3, 2, 27),
                new Job(4, 1, 25),
                new Job(5, 3, 15)
        };

        scheduleJobs(jobs);
    }
}
package ca.bytetube._15_greedy;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {


    private static class Meeting{
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


    public static int bestArrange(Meeting[] meetings, int current) {
        Arrays.sort(meetings, new Comparator<Meeting>() {

            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.end - o2.end;
            }
        });
        int count = 0;
        for(int i = 0; i < meetings.length; i++) {
            if(current <= meetings[i].start) {
                count++;
                current = meetings[i].end;
            }
        }
        return count;

    }



}

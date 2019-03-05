package com.wangmeng.dataStructure.circularQueue;



import java.util.*;

/**
 * BFS算法实现开锁
 */
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        //initialize the queue
        LinkedList<String> queue = new LinkedList<>();
        int steps = 0;
        //tansfer the deadends to the map
        Set<String> deadEnds = new HashSet<>();
        for (String d : deadends) {
            deadEnds.add(d);
        }
        if (deadEnds.contains("0000")) {
            return -1;
        }
        Set<String> visited = new HashSet<>();
        queue.offer("0000");

        //traverse the queue
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                String head = queue.peek();
                queue.pop();
                char[] headArray = head.toCharArray();
                for (int i = 0; i < headArray.length; i++) {
                    for (int j = -1; j < 2; j += 2) {
                        char[] curr = headArray.clone();
                        curr[i] = (char) ((curr[i] - '0' + j + 10) % 10 + '0');
                        //get a situation
                        String temp = new String(curr);
                        if (temp.equals(target)) {
                            return steps;
                        } else if (deadEnds.contains(temp) || visited.contains(temp)) {
                            continue;
                        }
                        queue.offer(temp);
                        visited.add(temp);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] deadends = new String[] {"0000"};
        String target =  "8888";
        int i = new OpenTheLock().openLock(deadends, target);
        System.out.println(i);
    }
}

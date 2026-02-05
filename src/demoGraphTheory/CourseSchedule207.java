package demoGraphTheory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
public class CourseSchedule207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 入度数组，用于记录每门课程的入度（即修这门课之前需要的先修课程数量）
        int[] inDegree = new int[numCourses];
        // 邻接表
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // 计算每门课程的入度，并构建邻接表
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preCourse = prerequisite[1];
            inDegree[course]++;
            adjList.get(preCourse).add(course);
        }

        // 存储入度为0的课程的队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 记录已完成课程的数量
        int finished = 0;
        while (!queue.isEmpty()) {
            int selectedCourse = queue.poll();
            finished++;
            // 获取当前课程的后序课程列表
            List<Integer> nextCourses = adjList.get(selectedCourse);
            for (Integer nextCourse : nextCourses) {
                // 后续课程的入度减一
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse); // 入度为0的课程加入队列queue
                }
            }
        }

        // 检查是否能修完所有课程，并返回
        return finished == numCourses;
    }
}

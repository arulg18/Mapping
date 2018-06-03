import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Mapping {

    public static Field field;
    static boolean[][] visited;
    public static Stack<Node> pathway = new Stack<>();


    public static void main(String[] args) throws IOException, URISyntaxException {
        field = new Field();

        visited = new boolean[field.field.length][field.field[0].length];
        search(new Node(26, 62), new Node(26, 60));

        System.out.println("# of Steps: " + (pathway.size() - 1));

        while (pathway.size() > 0){
            System.out.print(pathway.pop() + ", ");

        }


    }

    public static void search(Node current, Node destination){

        LinkedList<Node> queue = new LinkedList<>();
        visited[current.x][current.y] = true;
        current.setLast(new Node(-1, -1));

        queue.add(current);



        while (queue.size() != 0 && !found){
            current = queue.poll();
            if (current.x == destination.x && current.y == destination.y){
                found = true;
                destination = current;
                continue;
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if(nx == 13 && ny == 13){
                    System.out.println("here");
                }
                double k = distance(new Node(nx, ny), destination) + f_x(i);
                if (isValid2(nx, ny) && !isVisited(nx, ny)){
                    visited[nx][ny] = true;
                    add(queue, k, destination, new Node(nx, ny, current));
                }

            }

        }

        if (found) {

            Node path = destination;
            while (path.x != -1 && path.y != -1) {
                pathway.add(path);
                path = path.last;
            }
        }


    }
    public static double f_x(int i){
        switch (i){
            case 0:
            case 1:
                return 10/3;
            case 2:
            case 3:
                return 10/7;
            case 4:
            case 5:
            case 6:
            case 7:
                return Math.sqrt(2) / (1.55984/4);
        }
        return 0;
    }
    public static void add(LinkedList<Node> queue, double k, Node destination, Node adj){
        if (queue.size()==0){
            queue.add(adj);
            return;
        }
        int i = 0;
        while (i < queue.size() && distance(queue.get(i), destination) < k){
            i++;
        }
        if(i == queue.size()){
            queue.add(adj);
        }else {
            queue.add(i, adj);
        }
    }


    static int[] dx = new int[]{-1,1,0,0, 1, -1, 1, -1};
    static int[] dy = new int[]{0,0,-1,1, 1, 1, -1, -1};
    static boolean found = false;

    public static boolean isValid(int x, int y){
        return x >= 0 && x < field.field.length && y >= 0 && y < field.field[x].length && field.field[x][y]!=0;

    }
    public static boolean isValid2(int x, int y){
        for (int i = x-9; i < x+9; i++) {
            for (int j = y-9; j < y+9; j++) {
                if(!(i >= 0 && i < field.field.length && j >= 0 && j < field.field[i].length && field.field[i][j]!=0)){
                    return false;
                }
            }

        }
        return true;
    }

    public static boolean isVisited(int x, int y) {
        return x >= 0 && x < field.field.length && y >= 0 && y < field.field[x].length && visited[x][y];
    }

    public static double distance (Node c, Node d){
        return (Math.sqrt(  Math.pow(d.x - c.x, 2) + Math.pow(d.y - c.y, 2) ));

    }

    static class Node{
        int x,y;
        Node last;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Node(int x, int y, Node last){
            this.x = x;
            this.y = y;
            this.last = last;
        }

        public void setLast(Node last) {
            this.last = last;
        }

        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    }


}

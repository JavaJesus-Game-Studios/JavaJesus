package javajesus.entities;

import java.util.ArrayList;
import java.util.List;

class Node {
    int x;
    int y;
    
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Pathfind
{  
    public static void main(String args[])
    {
        char[][] matrix = {
            {'1', '0', '1', '1', '1', '0', '1', 'X'},
            {'1', '1', '0', '1', '1', '0', '1', '1'},
            {'1', '1', '1', '0', '0', '0', '1', '1'},
            {'1', '1', '1', '1', '0', '1', '0', '1'},
            {'1', '1', '1', '1', '1', '0', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1'}
        };
        
        boolean pathExists = generatePath(matrix, 0, 0, 0, 7);
    }
    
    public static boolean generatePath(char[][] matrix, int sX, int sY, int gX, int gY) {
        // Maps current node to previous
        // For every Node at x in cNode, the Node at x in pNode is its predecessor
        List<Node> pNode = new ArrayList<Node>();
        List<Node> cNode = new ArrayList<Node>();
        
        List<Node> queue = new ArrayList<Node>();
        Node start = new Node(sX, sY);
        
        queue.add(start);
        cNode.add(start);
        pNode.add(null);
        
        boolean pathExists = false;
        Node current = new Node(0,0);
        
        while(!queue.isEmpty()) {
            current = queue.remove(0);
            if(current.x == gX && current.y == gY) {
            	pathExists = true;
                break;
            }
            
            matrix[current.x][current.y] = '0'; // mark as visited
            
            List<Node> neighbors = getNeighbors(matrix, current);
            queue.addAll(neighbors);
            
            // For every neighbor, add current node as predecessor
            cNode.addAll(neighbors);
            while(cNode.size() != pNode.size()) {
            	pNode.add(current);
            }
        }
        
        List<Node> path = new ArrayList<Node>();
        path.add(current);
        if(pathExists) {
        	while(current != null) {
        		int ind = cNode.indexOf(current);
        		path.add(pNode.get(ind));
        		current = pNode.get(ind);
        	}
        }
        
        for(int i = path.size() - 2; i >= 0 ; i--) {
        	System.out.print("[" + path.get(i).x + ", " + path.get(i).y + "], ");
        }
        
        return pathExists;
    }
    
    public static List<Node> getNeighbors(char[][] matrix, Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        
        if(isValidPoint(matrix, node.x - 1, node.y)) {
            neighbors.add(new Node(node.x - 1, node.y));
        }
        
        if(isValidPoint(matrix, node.x + 1, node.y)) {
            neighbors.add(new Node(node.x + 1, node.y));
        }
        
        if(isValidPoint(matrix, node.x, node.y - 1)) {
            neighbors.add(new Node(node.x, node.y - 1));
        }
        
        if(isValidPoint(matrix, node.x, node.y + 1)) {
            neighbors.add(new Node(node.x, node.y + 1));
        }
        
        return neighbors;
    }
    
    public static boolean isValidPoint(char[][] matrix, int x, int y) {
        return !(x < 0 || x >= matrix.length || y < 0 || y >= matrix.length) && (matrix[x][y] != '0');
    }
}
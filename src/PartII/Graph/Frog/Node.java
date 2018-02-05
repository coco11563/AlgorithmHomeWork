package PartII.Graph.Frog;

public class Node {
    private int p_id;
    private String p_name = "";
    private NodeType type = NodeType.PATH;

    public Node(int p_id) {
        this.p_id = p_id;
    }
    public Node (int p_id, NodeType nodeType) {
        this(p_id);
        this.type = nodeType;
    }
    public Node (int p_id, NodeType nodeType, String name) {
        this(p_id, nodeType);
        this.p_name = name;
    }

    public int getP_id() {
        return p_id;
    }


    public String getP_name() {
        return p_name;
    }


    public NodeType getType() {
        return type;
    }


    enum NodeType {
        GOAL, START, PATH, DETOUR;
    }

    public static void main(String args[]) {
        Node n = new Node(1, NodeType.DETOUR, "123");
        System.out.println(n.getP_id() + "," + n.getP_name() + "," + n.getType());
    }
}

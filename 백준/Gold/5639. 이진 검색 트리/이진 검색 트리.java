import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuffer sb;
	static class Node {
		int val;
		Node left;
		Node right;
		
		void insert(int num) {
			if(this.val > num) {
				if(this.left == null) {
					this.left = new Node();
					this.left.val = num;
				}
				else
					this.left.insert(num);
			}
			else {
				if(this.right == null) {
					this.right = new Node();
					this.right.val = num;
				}
				else
					this.right.insert(num);
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		Node root = new Node();
		root.val = Integer.parseInt(br.readLine());
		
		while(true) {
			try {
				int val = Integer.parseInt(br.readLine());
				root.insert(val);
				
			} catch (IOException | NumberFormatException e) {
				break;
			}
		}
		
		postOrder(root);
		System.out.println(sb);
	}
	
	static void postOrder(Node node) {
		if(node.left != null) postOrder(node.left);
	 	if(node.right != null) postOrder(node.right);
		sb.append(node.val).append("\n");
		
	}

}
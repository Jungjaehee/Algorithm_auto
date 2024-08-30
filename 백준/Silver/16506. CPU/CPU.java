import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static Map<String, String> operation = new HashMap<String, String>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		operation.put("ADD", "0000");
		operation.put("SUB", "0001");
		operation.put("MOV", "0010");
		operation.put("AND", "0011");
		operation.put("OR", "0100");
		operation.put("NOT", "0101");
		operation.put("MULT", "0110");
		operation.put("LSFTL", "0111");
		operation.put("LSFTR", "1000");
		operation.put("ASFTR", "1001");
		operation.put("RL", "1010");
		operation.put("RR", "1011");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int counts = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < counts; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String opcode = st.nextToken();
			boolean isB = true;
			if(opcode.endsWith("C"))
				isB = false;
			
			sb.append(isB? operation.get(opcode)+"0" : operation.get(opcode.substring(0, opcode.length()-1))+"1");
			sb.append(0);
			
			// rD
			sb.append(String.format("%3s", Integer.toBinaryString(
					Integer.parseInt(st.nextToken()))).replace(" ", "0"));

			// rA
			sb.append(String.format("%3s", Integer.toBinaryString(
					Integer.parseInt(st.nextToken()))).replace(" ", "0"));
			
			// rB or #c
			sb.append(String.format(isB? "%3s0" : "%4s", Integer.toBinaryString(
					Integer.parseInt(st.nextToken()))).replace(" ", "0"));			
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
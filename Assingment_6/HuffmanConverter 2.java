import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files

//constructor
public class HuffmanConverter{

        // ASCII number of characters
        public static final int NUMBER_OF_CHARACTERS = 256;

        private String contents;
        private HuffmanTree huffmanTree;
        private int count[];
        private String code[];

        // Construct using an input string.
        // Initialize count and code.
        public HuffmanConverter(String input) {
          this.contents = input;
          this.count = new int[NUMBER_OF_CHARACTERS];
          this.code = new String[NUMBER_OF_CHARACTERS];
        }

        // Record how often each character occurs and store in count.
        public void recordFrequencies() {
            for(int i = 0; i < contents.length(); i++){
                char c = contents.charAt(i);
                count[(int)c] ++; 
            }
            
            for(int i = 0; i < NUMBER_OF_CHARACTERS; i++){
            	if (count[i] > 0) {
                    char c = (char) i;
                    String output;
                    if (c == '\n') {
                    	output = "\\n";
                    }
                    else {
                    	output = Character.toString(c);
                    }
                    System.out.print("<" + output + ", " + count[i] + "> ");
            	}
            }
            System.out.println("\n");
        }

        // Construct a Huffman tree from count and 
        // store the tree in huffmanTree.
        public void frequenciesToTree() {
            BinaryHeap<HuffmanNode> bheap = new BinaryHeap<HuffmanNode>();
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    char letter = (char) i;
                    double freq = count[i]; 
                    HuffmanNode node = new HuffmanNode(Character.toString(letter), freq);
                    bheap.insert(node);
                }
            }
            this.huffmanTree = HuffmanTree.createFromHeap(bheap); 
        }

        // Construct code from huffmanTree.
        public void treeToCode() {
        	Arrays.fill(code, "");  
        	treeToCode(huffmanTree.root, ""); 
        	
        	//huffmanTree.printLegend(); 
        	for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
        	    if (count[i] > 0) {
        	        char c = (char) i;
        	        String huffCode = code[c];
        	        if (c == '\n') {
        	            System.out.println("'\\n'=" + huffCode);
        	        } else {
        	            System.out.println("'" + c + "'=" + huffCode);
        	        }
        	    }
        	}
        }

        private void treeToCode(HuffmanNode t, String encoding) {
        	if(t.left == null && t.right == null) {
        		char c = t.letter.charAt(0); 
        		code[(int)c] = encoding;
        		return;
        	}
        	if(t.left != null) {
        		treeToCode(t.left, encoding + "0");
        	}
        	if(t.right != null) {
        		treeToCode(t.right, encoding + "1");
        	}
        }

        // Encode content using code.
        public String encodeMessage() {
        	String encodedMes = ""; 
        	for(int i = 0; i < contents.length(); i++) {
        		char c = contents.charAt(i);
        		encodedMes += code[(int)c];
        	}
        	System.out.println("\nHuffman Encoding: ");
        	return encodedMes;
        }
        
        // Decode a Huffman encoding.
        public String decodeMessage(String encodedStr) {
        	HuffmanNode temp = huffmanTree.root;
        	String decoded = "";
        	for(int i = 0; i < encodedStr.length(); i++) {
        		char bit = encodedStr.charAt(i);  
        			if (bit == '0') {
        				temp = temp.left; 
        			} else if(bit == '1') {
        				temp = temp.right; 
        			}
        			if(temp.left == null && temp.right == null) {
        				decoded += temp.letter; 
        				temp = huffmanTree.root;
        			}
        	}
        	return decoded; 
        }
        		
        // Read an input file.
        public static String readContents(String filename) {
            String temp = "";
            try {
                File file = new File(filename);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    temp += sc.nextLine();
                    temp += "\n";
                }
                sc.close();
                return temp;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return "";
        }

        public static void main(String args[]) {
                String input = HuffmanConverter.readContents(args[0]);
                HuffmanConverter h = new HuffmanConverter(input);
                h.recordFrequencies();
                // Print a list of characters and frequencies here!
                h.frequenciesToTree();
                h.treeToCode();
                // Print the Huffman encoding here!
                String encoded = h.encodeMessage();
                System.out.println(encoded+"\n");
                System.out.println("Message size in ASCII encoding: "+h.contents.length()*8);
                System.out.println("Message size in Huffman coding: "+encoded.length()+"\n");
                System.out.println(h.decodeMessage(encoded));
        }

}

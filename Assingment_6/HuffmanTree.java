public class HuffmanTree
{
HuffmanNode root;
	public HuffmanTree(HuffmanNode huff) {
		this.root = huff; 
		
	}
	
	public void printLegend() {
		printLegend(root, "");
	}
	
	private void printLegend(HuffmanNode t, String s) {
		if (t.letter.length() > 1) {
			printLegend(t.left, s + "0"); 
			printLegend(t.right, s + "1");
		}
		else if (t.letter.length() == 1) {
			System.out.println(t.letter+"="+s);
		}
	}
	
	public static BinaryHeap legendToHeap(String legend) {
		String[] elements = legend.split(" "); //split string
		
		int size = elements.length/2; 
		HuffmanNode[] array = new HuffmanNode[size];
		int index = 0;
		for(int i = 0; i < elements.length; i+=2) {
			String letter = elements[i];
			double frequency = Double.parseDouble(elements[i+1]);
			HuffmanNode combo = new HuffmanNode(letter, frequency);
			array[index] = combo; 
			index++;
		}
		return new BinaryHeap<HuffmanNode>(array);
	}
	
	public static HuffmanTree createFromHeap(BinaryHeap b) {
		while (b.getSize() > 1) {
			HuffmanNode min1 = (HuffmanNode)b.deleteMin(); 
			HuffmanNode min2 = (HuffmanNode)b.deleteMin(); 
			HuffmanNode combo = new HuffmanNode(min1, min2); 
			b.insert(combo); 
		}
			HuffmanNode root = (HuffmanNode) b.deleteMin(); 
			return new HuffmanTree(root); 
		}
	
	public static void main(String[] args) {
		BinaryHeap<HuffmanNode> bheap = HuffmanTree.legendToHeap("A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2");
		bheap.printHeap(); 
		HuffmanTree htree = HuffmanTree.createFromHeap(bheap);
		htree.printLegend(); 
		
	}
}
package ca.bytetube._06_tree.printer;

public abstract class Printer {	

	protected BinaryTreeInfo tree;
	
	public Printer(BinaryTreeInfo tree) {
		this.tree = tree;
	}
	

	public abstract String printString();
	

	public void println() {
		print();
		System.out.println();
	}

	public void print() {
		System.out.print(printString());
	}
}

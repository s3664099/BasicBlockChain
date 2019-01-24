
public class GenerateChain {
	
	private Block[] block = new Block[5];
	String hash;
	
	//this will generate a chain of four blocks
	public GenerateChain()
	{
		for (int i=0;i<4;i++)
		{
			
			hash = getHash(i);
			
			block[i] = new Block("Block "+i, hash);
			
			System.out.println(block[i].toString(hash));
			System.out.println();
		}
		
		//changing the time stamp on block 3
		block[2].changeTimeStamp();
		
		//printing out the contents of the chain again
		for(int i=0;i<4;i++)
		{
			hash = getHash(i);
			
			System.out.println(block[i].toString(hash));
			System.out.println();
		}
		
		
		
	}
	
	private String getHash(int i)
	{
		if (i==0)
		{
			return "0";
		} else {
			return block[i-1].getHash();
		}
	}

}

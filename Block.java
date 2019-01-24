import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

public class Block {
	
	//These are the details we are going to include in our block.
	private String name;
	private Timestamp time = new Timestamp(System.currentTimeMillis());
	private String thisHash;
	private String previousHash;
	
	public Block(String name, String previousHash)
	{
		this.name = name;
		this.previousHash = previousHash;
		
		thisHash = getSHA();
	}
	
	//Code used from https://www.geeksforgeeks.org/sha-256-hash-in-java/
	private String getSHA()
	{
		String hash;
		
		try {

			//This method is called to get the SHA-256 algorithim
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//The details of the hash are obtained and turned into bytes
			byte[] messageDigest = md.digest(getDetails(previousHash).getBytes());
			
			//The number representing the details of the block are generated
			BigInteger no = new BigInteger(1, messageDigest);
			
			//the number is turned into hex code
			hash = no.toString(16);
			
			//if the length is less than 32, then 0s are added at the start
			while (hash.length()<32)
			{
				hash ="0" + hash;
			}
			
			return hash;
			
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
		return null;
	}
	
	//this method turns the details of the block into a String
	//The hash of the previous block is being passed through to show
	//how the hash of the entire chain can be changed if one small detail of the
	//block is changed
	private String getDetails(String hash)
	{
		return String.format("Name: %s%nTimestamp: %s%nPrevious Hash: %s%n", name, time, previousHash);
	}
	
	public String toString(String hash)
	{
		//the previous hash needs to be updated in case something has been
		//changed in a previous block
		previousHash = hash;
		return String.format("%sThis Hash: %s", getDetails(hash),getSHA());
	}
	
	public String getHash()
	{
		return getSHA();
	}
	
	//this method has been created to demonstrate how, if a part of the block is changed
	//the entire block chain will be effected
	public void changeTimeStamp()
	{
		time = new Timestamp(System.currentTimeMillis());
	}


}

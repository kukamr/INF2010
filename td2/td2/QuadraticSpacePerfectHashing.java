import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing()
	{
		a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		return items.length;
	}

	public boolean containsKey(int key)
	{
		if(this.items[key] == null)
			return false;
		else
			return true;

	}

	public boolean containsValue(AnyType x )
	{
		int key = getKey(x);
		return(items[key] == x);

	}

	public void remove (AnyType x) {
		items[getKey(x)] = null;

	}

	public int getKey (AnyType x) {

		int key = ((a*x.hashCode() + b) % p) % items.length;
		return key;
		
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			items = null;
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;
			Object value = array.get(0);
			items = (AnyType[]) new Object[] {value};

			return;
		}

		items = (AnyType[]) new Object[array.size()*array.size()];

		a = generator.nextInt(p - 1) + 1;
		b = generator.nextInt(p - 1);

		for(AnyType item : array){
			int newIndex = getKey(item);
			items[newIndex] = item;
		}

	}

	public String toString() {
		String result = "";
		
		for(int i = 0; i < Size(); i++){
			if(items[i] != null){
				result += MessageFormat.format("({0},{1}), ", getKey(items[i]), items[i]);
			}
		}
		return result; 
	}

	public ArrayList toArrayList() {
		
		ArrayList<AnyType> toReturn = new ArrayList<AnyType>();

		for(int i = 0; i < Size(); i++) {
			if(items[i] != null){
				toReturn.add(items[i]);
			}
		}

		return toReturn;
	}

	public void makeEmpty () {
		   // A completer
	   }
	
}

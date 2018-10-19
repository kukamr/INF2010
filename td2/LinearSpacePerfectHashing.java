import java.util.Random;

import javax.lang.model.util.ElementScanner6;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;


	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			data = new QuadraticSpacePerfectHashing[0];
			return;
		}

		if(array.size() == 1)
		{
			a = b = 0;

			data = new QuadraticSpacePerfectHashing[1];
			data[0] = new QuadraticSpacePerfectHashing<AnyType>(array);
			return;
		}

		data = new QuadraticSpacePerfectHashing[array.size()];
		a = generator.nextInt(p - 1) + 1;
		b = generator.nextInt(p);
		

		for ( AnyType item : array) {
			int key = getKey(item);

			if(data[key] == null) {
				ArrayList arrayListWithItem = new ArrayList<AnyType>();						//On cree un ArrayList avec un seul item	
				arrayListWithItem.add(item);													
				data[key] = new QuadraticSpacePerfectHashing<AnyType>(arrayListWithItem);	//On cree un nouveau QuadraticHashing dans notre Array Data
			} else {
				ArrayList<AnyType> values = data[key].toArrayList();						//On cree un ArrayList avec toutes les donnes qui sont deja presentes dans le QuadraticHashing
				values.add(item);															//On rajoute l'item
				data[key] = new QuadraticSpacePerfectHashing<AnyType>(values);				//On cree un nouvrau QuadraticHashing plus grand dans Array Data
			}
		}		

	}

	public int Size()
	{
		if( data == null )
			return 0;

		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			if(data[i] == null) 
				size += 1;
			else 
				size += data[i].Size();
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		return (data[key] != null ? true : false);

	}
	
	public int getKey (AnyType x) {
		int key = ((a * x.hashCode() + b) % p) % data.length;

		return key;
		
	}
	
	public boolean containsValue (AnyType x) {
		int key = getKey(x);

		if(containsKey(key)){
			return data[key].containsValue(x);
		}
		return false;
	}
	
	public void remove (AnyType x) {
		int key = getKey(x);
		data[key].remove(x);
		
	}

	public String toString () {
		String result = "";
		
		for(int i =0; i < data.length; i++){
			if(data[i] != null){
				result += MessageFormat.format("[{0}] -> {1} \n", i, data[i].toString());
			} else {
				result += MessageFormat.format("[{0}] -> null \n", i);
			}
		}
		
		
		return result; 
	}

	public void makeEmpty () {
		// A completer

   	}
	
}

package io.github.jkai.smart.bar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class FixedBuffer 
{
	private Deque<Integer> list;
	private int maxSize;
	
	public FixedBuffer(int maxSize)
	{
		this.maxSize = maxSize;
		list = new LinkedList<>();
	}
	
	public void pushNPop(int i)
	{
		if(list.size() >= maxSize)
		{
			list.pop();
		}
		list.add(i);
	}
	
	public int getMedian()
	{
		ArrayList<Integer> sortedList = new ArrayList<>(list);
		Collections.sort(sortedList);
		return sortedList.get(sortedList.size() / 2);
	}
	
	
}

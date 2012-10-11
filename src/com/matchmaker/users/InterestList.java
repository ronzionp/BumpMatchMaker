package com.matchmaker.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class InterestList 
{
	private static final String CATEGORIES_SEPERATOR = ":";
	private static final String VALUES_SEPERATOR = ",";
	
	public enum Category
	{
		MUSIC,
		MOVIES,
		//TODO: add more by facebook & twitter categories
		;
		
		/**
		 * @param name of the Enum to get
		 * @return the Enum represented by name or null if no Enum with given name exists
		 */
		public Category getEnum(String name)
		{
			for (Category cat : Category.values())
			{
				if (cat.name().equals(name))
					return cat;
			}
			
			return null;
		}
	}

	private HashMap<Category, List<String>> map;
	
	public InterestList()
	{
		map = new HashMap<InterestList.Category, List<String>>();
	}
	
	public void addInterest(Category category, String value)
	{
		List<String> values = map.get(category);
		
		if (values == null)
			values = new ArrayList<String>();
		
		values.add(value);
		map.remove(category);	//Not sure if needed but just in case
		map.put(category, values);
	}
	
	/**
	 * This method should be used to get list of interest values to compare with other user interest by category.
	 * @param {@link Category} to get interest list.
	 * @return list of strings representing the values for the given category or null in case of not existing category.
	 */
	public List<String> getInterestListByCategory(Category category)
	{
		return map.get(category);
	}
	
	/**
	 * This method should be used in order to check whether this user has interest in specific category.
	 * @param {@link Category} to check.
	 * @return true or false respectively.
	 */
	public boolean isCategoryExists(Category category)
	{
		return map.containsKey(category);
	}

	/**
	 * This method should be used when user need to send the interest list over bump channel.
	 * @return string representing the map in the form of: 
	 * Category1=val1{@link #VALUES_SEPERATOR}val2{@link #CATEGORIES_SEPERATOR}Category2=val3{@link #VALUES_SEPERATOR}val4{@link #VALUES_SEPERATOR}val5{@link #VALUES_SEPERATOR}...
	 * Notice: the returned string will contain only categories which has at least 1 value.
	 */
	public String getStringifiedMap() 
	{
		String stringify = "";
		for (Category category : map.keySet())
		{
			List<String> values = map.get(category);
			if (!values.isEmpty())
			{
				stringify += category.name();
				stringify += "=";
				Iterator<String> valIter = values.iterator();
				while (valIter.hasNext())
				{
					stringify += valIter.next();
					if (valIter.hasNext())
						stringify += VALUES_SEPERATOR;
				}
				stringify += CATEGORIES_SEPERATOR;
			}
		}
		
		return stringify;
	}
	
	public InterestList createFromStringifiedMap(String stringifiedMap)
	{
		//TODO: implement this as reversing to getStringifiedMap
		return null;
	}
}

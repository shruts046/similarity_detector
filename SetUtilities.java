/*
 * Copyright 2021 Marc Liberatore.
 */

package sets;

import java.util.HashSet;
import java.util.Set;

public class SetUtilities {
	/**
	 * Returns a new set representing the union of s and t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the union of s and t
	 */
	public static <E> Set<E> union(Set<E> s, Set<E> t) {
		if(s == null)
		return t;
   
	if(t == null)
		return s;
   
	Set<E> unionSet = new HashSet<E>();
   
	for (E element : s)
	{
		unionSet.add(element);
	}
   
	for (E element : t)
	{
		unionSet.add(element);
	}
   
	return unionSet;
	}

	/**
	 * Returns a new set representing the intersection of s and t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the intersection of s and t
	 */
	public static <E> Set<E> intersection(Set<E> s, Set<E> t) {
		if(s == null || t == null)
		return null;
   
	Set<E> intersectionSet = new HashSet<E>();
	for (E element : s)
	{
		if (t.contains(element))
		{
			intersectionSet.add(element);
		}
	}
   
	return intersectionSet;
	}

	/**
	 * Returns a new set representing the set difference s and t,
	 * that is, s \ t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the difference of s and t
	 */
	public static <E> Set<E> setDifference(Set<E> s, Set<E> t) {
		if(s == null)
		return null;
   
	if(t == null)
		return s;
   
	Set<E> differenceSet = new HashSet<E>();
	for (E element : s)
	{
		if (!t.contains(element))
		{
			differenceSet.add(element);
		}
	}
   
	return differenceSet;
	}
	
	/**
	 * Returns the Jaccard index of the two sets s and t.
	 * 
	 * It is defined as 1 if both sets are empty.
     *
	 * Otherwise, it is defined as the size of the intersection of the sets, 
	 * divided by the size of the union of the sets.
	 * 
	 * Does not modify s or t.
	 * 
	 * @param s
	 * @param t
	 * @return the Jaccard index of s and t
	 */
	public static <E> double jaccardIndex(Set<E> s, Set<E> t) {
		if(s.isEmpty() && t.isEmpty())
		return 1;
   
	Set<E> intersectionSet = intersection(s, t);
	Set<E> unionSet = union(s, t);
   
	return ((double)intersectionSet.size() / unionSet.size());
	}
}

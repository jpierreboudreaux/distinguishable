package combinations;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/*
 * There is no simple formula for distributing n distinguishable
 * objects into k indistinguishable boxes. This program seeks to
 * find the solution to a "labeled" objects into "unlabeled"
 * boxes within a reasonable timeframe.
 */
public class Distinguishable<E> {
	
	/*
	 * The objects placed into the boxes are taken in as a generic.
	 * This allows the array of different combinations of specific objects
	 * to be returned faithfully in addition to the number of combinations.
	 */
	private E[] items;
	/*
	 * The boxes variable simply stores the number of boxes that each object
	 * will have the option of being placed into. Only the integer value
	 * is needed as each of the boxes is indistinguishable from the others.
	 * (i.e. the boxes exist as an unlabeled set of identical objects)
	 */
	private int boxes;
	/*
	 * This boolean checks whether the set of combinations has already been
	 * generated
	 */
	private boolean hasSet;
	/*
	 * The HashSet stores each unique List<E> of combinations. The HashSet
	 * format protects against any duplicate Lists appearing.
	 */
	private HashSet<List<List<E>>> set;
	
	public Distinguishable() {
		this(null, 0);
	}
	
	public Distinguishable(E[] items, int boxes) {
		// k > n : k = n my principle for indistinguishable boxes
		if(items != null && boxes > items.length) boxes = items.length;
		this.boxes = boxes;
		this.items = items;
		hasSet = false;
		set = new HashSet<List<List<E>>>();
	}
	
	/*
	 * Creates the initial empty list of size n where 
	 * n = # of items and calls helper function
	 */
	public HashSet<List<List<E>>> getCombinations(List<List<E>> list) {
		if(hasSet || boxes == 0 || items.length == 0) return set;
		for(int i = 0; i < boxes; i++) {
			list.add(new ArrayList<E>());
		}
		list.get(0).add(items[0]);
		combinations(list, 1, 1);
		hasSet = true;
		return set;
	}
	
	public void combinations(List<List<E>> list, int maxIndex, int lvl) {
		if(lvl == items.length) {
			set.add(list);
			return;
		}
		if(maxIndex >= boxes) maxIndex = boxes - 1;
		for(int i = 0; i <= maxIndex; i++) {
			List<List<E>> tempList = clone(list);
			List<E> temp = tempList.get(i);
			temp.add(items[lvl]);
			tempList.set(i, temp);
			combinations(tempList, Math.max(i + 1, maxIndex), lvl + 1);
		}
	}
	
	public List<List<E>> clone(List<List<E>> orig) {
		List<List<E>> copy = new ArrayList<List<E>>();
		for(int i = 0; i < orig.size(); i++) {
			List<E> innerCopy = new ArrayList<E>();
			for(int j = 0; j < orig.get(i).size(); j++) {
				innerCopy.add(orig.get(i).get(j));
			}
			copy.add(innerCopy);
		}
		return copy;
	}
	
	public E[] getItems() {
		return items;
	}
	
	public int getBoxes() {
		return boxes;
	}
	
	public void setItems(E[] items) {
		this.items = items;
	}
	
	public void setBoxes(int boxes) {
		this.boxes = boxes;
	}
	
	/*
	 * I did a bit of reading on Sterling numbers when
	 * optimizing this one. Is that cheating?
	 */
	public int numCombinations() {
		return set.size();
	}
	
	/*
	 * Returns a single random list of objects in
	 * k respective buckets. Much simpler than returning
	 * an entire list.
	 */
	public List<List<E>> getRandomCombination() {
		Random rand = new Random();
		List<List<E>> list = new ArrayList<List<E>>();
		for(int i = 0; i < boxes; i++) {
			list.add(new ArrayList<E>());
		}
		int lvl = 0;
		while(lvl < boxes) {
			list.get(rand.nextInt()).add(items[lvl]);
		}
		return list;
	}
	
	public void printCombinations() {
		getCombinations(new ArrayList<List<E>>());
		Iterator<List<List<E>>> it = set.iterator();
		System.out.println("----------------------------------");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}

package sports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Product {
	private String name;
	//private String attivita;
	//private String categoria;
	private Set<Rating> recensioni = new HashSet<> ();
	
	
	public Product(String name) {
		this.name=name;
		//this.attivita=attivita
		//this.categoria=categoria
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addRating(Rating r) {
		recensioni.add(r);
	}
	
	public List<String> getRatings(){
		if(recensioni.size()==0)
			return new ArrayList<>();
		
		return recensioni.stream().sorted((s,q)->q.getNumStars()-s.getNumStars())
				.map(n-> n.toString())
				.collect(Collectors.toList());

	}
	
	public double getRatingsAverage(){
		
		int numrec =recensioni.size();
		double n=  recensioni.stream()
				.mapToInt(q-> q.getNumStars())
				.sum();
		
		return n/numrec;

	}
	
	
	public List<Rating> getRatingObjList(){
		return recensioni.stream().collect(Collectors.toList());
	}
	
	
}

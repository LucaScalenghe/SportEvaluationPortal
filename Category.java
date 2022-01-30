package sports;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Category {

	private String name;
	private Set<Activity> attivita = new HashSet<> ();
	private Set<Product> prodotti = new HashSet<>();
	
	
	public Category(String s) {
		this.name=s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addActivity(Activity s) {
		attivita.add(s);
	}

	
	public void addProduct(Product s) {
		prodotti.add(s);
	}
	
	
	public List<String> getProducts(){
		if(prodotti.size()==0)
			return null;
		
		return prodotti.stream().map(Product::getName)
		.sorted((s,q)->s.compareTo(q))
		.collect(Collectors.toList());

	}
	
	
	
	
}

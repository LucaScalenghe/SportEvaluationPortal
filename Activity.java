package sports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Activity {

	
	
	private String name;
	private Set<Category> categorie = new HashSet<>();
	private Set<Product> prodotti = new HashSet<>();

	
	
	public Activity(String s) {
		this.name=s;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void addCategory(Category c) {
		categorie.add(c);
	}

	public List<String> getCategories(){
    	List<String> l = new ArrayList<>();

		if(categorie.size()==0)
			return l;
		
		return categorie.stream().map(Category::getName)
		.sorted((s,q)->s.compareTo(q))
		.collect(Collectors.toList());

	}
	
	public void addProduct(Product s) {
		prodotti.add(s);
	}
	
	public List<String> getProducts(){
		return prodotti.stream().map(Product::getName)
		.sorted((s,q)->s.compareTo(q))
		.collect(Collectors.toList());

	}
	
	//_-------------------------------------------------------
	private List<String> getRatingList(){
	     return prodotti.stream().flatMap(p->p.getRatings().stream()).collect(Collectors.toList());
	}
	
	
	
	
}

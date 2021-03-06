package sports;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Facade class for the research evaluation system
 *
 */
public class Sports {

	Map<String,Activity> attivita = new HashMap<> ();
	Map<String,Category> categorie = new HashMap<> ();
	Map<String,Product> prodotti = new HashMap<> ();
	Set<Rating> recensioni = new HashSet<> ();
	
    /**
     * Define the activities types treated in the portal.
     * The method can be invoked multiple times to add different activities.
     * 
     * @param actvities names of the activities
     * @throws SportsException thrown if no activity is provided
     */
    public void defineActivities (String... activities) throws SportsException {
    
    	if(activities.length==0) {
    		throw new SportsException ("No activities");
    	}
    	
    for(String s : activities) {
    	Activity a = new Activity(s);
    	attivita.put(s,a);
    }
    	
    	
    
    }

    /**
     * Retrieves the names of the defined activities.
     * 
     * @return activities names sorted alphabetically
     */
    public List<String> getActivities() {
    	List<String> l= attivita.values().stream().map(Activity::getName)
    			.sorted((s,q)->s.compareTo(q))
    			.collect(Collectors.toList());
    	return l;
    }


    /**
     * Add a new category of sport products and the linked activities
     * 
     * @param name name of the new category
     * @param activities reference activities for the category
     * @throws SportsException thrown if any of the specified activity does not exist
     */
    public void addCategory(String name, String... linkedActivities) throws SportsException {
   
    	for(String s : linkedActivities) {
    		if(!attivita.containsKey(s)) {
    			throw new SportsException("Errore");
    		}
    	}
    	
    	Category c= new Category(name);
    	categorie.put(name,c);
    	
    	
    	for(String s : linkedActivities) {
    	
    		c.addActivity(attivita.get(s));
    		attivita.get(s).addCategory(c);
    	}

    	
    }

    /**
     * Retrieves number of categories.
     * 
     * @return categories count
     */
    public int countCategories() {
        return categorie.size();
    }

    /**
     * Retrieves all the categories linked to a given activity.
     * 
     * @param activity the activity of interest
     * @return list of categories (sorted alphabetically)
     */
    public List<String> getCategoriesForActivity(String activity) {
    	List<String> l = new ArrayList<>();
    	
    	if(!attivita.containsKey(activity))
    		return l;
    	
    	return attivita.get(activity)
    			 .getCategories();
    	
    }

    //R2
    /**
     * Add a research group and the relative disciplines.
     * 
     * @param name name of the research group
     * @param disciplines list of disciplines
     * @throws SportsException thrown in case of duplicate name
     */
    public void addProduct(String name, String activityName, String categoryName) throws SportsException {
    	if(prodotti.containsKey(name)) {
    		throw new SportsException("errore");
    	}
    	//dovrei aggiungere oltre che a name anche activity name e category name
    	Product p = new Product(name);
    	prodotti.put(name, p);
    	attivita.get(activityName).addProduct(p);
    	categorie.get(categoryName).addProduct(p);
    }

    /**
     * Retrieves the list of products for a given category.
     * The list is sorted alphabetically.
     * 
     * @param categoryName name of the category
     * @return list of products
     */
    public List<String> getProductsForCategory(String categoryName){
    	if(!categorie.containsKey(categoryName))
    		return new LinkedList<String>();
    	return categorie.get(categoryName).getProducts();
    	
    }

    /**
     * Retrieves the list of products for a given activity.
     * The list is sorted alphabetically.
     * 
     * @param activityName name of the activity
     * @return list of products
     */
    public List<String> getProductsForActivity(String activityName){
		List<String> l=new ArrayList<>();
    	if(!attivita.containsKey(activityName))
    	return l; 
    	return attivita.get(activityName).getProducts();
    }

    /**
     * Retrieves the list of products for a given activity and a set of categories
     * The list is sorted alphabetically.
     * 
     * @param activityName name of the activity
     * @param categoryNames names of the categories
     * @return list of products
     */
    public List<String> getProducts(String activityName, String... categoryNames){
    	List<String> l = new ArrayList<>();
    	if(!attivita.containsKey(activityName)|| 
        	attivita.get(activityName).getProducts()==null) {
        	return l;    
        	}
    	
    	List<String> n = attivita.get(activityName).getProducts();    	    	
    	for(String s : categoryNames) {
    		l.addAll(categorie.get(s).getProducts());
    	}
    	List<String> res = new ArrayList<> ();
    	for(String z : n) {
    		if(l.contains(z))
    			res.add(z);
    	}
    	

    	return res;
    }

    //    //R3
    /**
     * Add a new product rating
     * 
     * @param productName name of the product
     * @param userName name of the user submitting the rating
     * @param numStars score of the rating in stars
     * @param comment comment for the rating
     * @throws SportsException thrown numStars is not correct
     */
    public void addRating(String productName, String userName, int numStars, String comment) throws SportsException {
    
    	if(numStars<0 || numStars>5) {
    		throw new SportsException("Errore");
    	}
    	
    	Rating r= new Rating(productName,userName,numStars,comment);
    	recensioni.add(r);
    	prodotti.get(productName).addRating(r);
    
    }



    /**
     * Retrieves the ratings for the given product.
     * The ratings are sorted by descending number of stars.
     * 
     * @param productName name of the product
     * @return list of ratings sorted by stars
     */
    public List<String> getRatingsForProduct(String productName) {
    	
    	return prodotti.get(productName).getRatings();
    }


    //R4
    /**
     * Returns the average number of stars of the rating for the given product.
     * 
     * 
     * @param productName name of the product
     * @return average rating
     */
    public double getStarsOfProduct (String productName) {
    	return prodotti.get(productName).getRatingsAverage();
    }

    /**
     * Computes the overall average stars of all ratings
     *  
     * @return average stars
     */
    public double averageStars() {
       double np= prodotti.size();
    	double r= prodotti.values().stream().mapToDouble(s->s.getRatingsAverage()).sum();
    	return recensioni.stream().mapToInt(Rating::getNumStars).average().orElse(0);
    }

    //R5 Statistiche
    /**
     * For each activity return the average stars of the entered ratings.
     * 
     * Activity names are sorted alphabetically.
     * 
     * @return the map associating activity name to average stars
     */
    public SortedMap<String, Double> starsPerActivity() {

    	
  return null;
    	
    	//    	return attivita.values().stream()
//    			.filter(a->a.getRatingList().size >0)
//    			.collect(Collectors.toMap(a->a.getName(),
//    					a->a.getAverageStars(),
//    					(a,b)->a,
//    					TreeMap::new
//   					));
//    	
//    	
//    	
//        return recensioni.stream().collect(Collectors.groupingBy(r -> {
//        	for(Product p : prodotti.values().map(z->z.getName()).collect(Collectors.toList()))
//        		if(p.getName().equals(r.getProductsName()))
//        			return p.getActivity();
//        	return null;
//        },TreeMap::new,Collectors.averagingDouble(Rating::getNumStars)));
   }

    /**
     * For each average star rating returns a list of
     * the products that have such score.
     * 
     * Ratings are sorted in descending order.
     * 
     * @return the map linking the average stars to the list of products
     */
    public SortedMap<Double, List<String>> getProductsPerStars () {
    	return prodotti.values().stream()
    			.filter(p->p.getRatings().size()>0)
    			.collect(Collectors.groupingBy(Product::getRatingsAverage,Collectors.toList()))
    			.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),
    					e->e.getValue().stream().map(f->f.getName()).collect(Collectors.toList()),
    					(a,b)->a ,TreeMap::new)).descendingMap();
    }

}

package recipes.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //autogen
	private Long id;

	private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    
    @Lob
    private String directions;
    private String url;
    
    // owned by Recipe, cascade to delete ingredients when deleting recipe 
    // stored in Ingredient's property called recipe 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();
    
    @Lob // Large object, BLOB (binary large object)
    private Byte[] image;

    @Enumerated(value = EnumType.STRING) // permite añadir nuevos valores entre medias 
    // sin descolocar la bbdd. ORDINAL asigna números por posición, lleva mal los cambios
    private Difficulty difficulty;
    
    @OneToOne(cascade = CascadeType.ALL) //para que borre la nota al borrar la receta
    private Notes notes;

	
    // crear una tabla conjunta, indicando que de este lado use recipe_id,
    // y del otro, cat_id
    @ManyToMany
    @JoinTable(name = "recipe_category",
        joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	public Integer getCookTime() {
		return cookTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	public Integer getServings() {
		return servings;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public Notes getNotes() {
		return notes;
	}

	public void setDifficulty(Difficulty diff) {
		this.difficulty = diff;
		
	}

	public void setDirections(String directions) {
		this.directions = directions;
		
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public String getDirections() {
		return directions;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	
}

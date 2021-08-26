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

import lombok.Data;

@Entity
@Data
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
    
}

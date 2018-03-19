package permissions;

import java.security.*;
import java.util.*;

/**
 * A permission that checks for bad words.
 */
public class BookCategoryPermission extends Permission
{
   private String action;

   /**
    * Constructs a word check permission.
    * @param target a comma separated word list
    * @param anAction "insert" or "avoid"
    */
   public BookCategoryPermission(String target, String anAction)
   {
      super(target);
      action = anAction;
   }

   public String getActions()
   {
      return action;
   }

   public boolean equals(Object other)
   {
      if (other == null) return false;
      if (!getClass().equals(other.getClass())) return false;
      BookCategoryPermission b = (BookCategoryPermission) other;
      if (!Objects.equals(action, b.action)) return false;
      if ("access".equals(action)) return Objects.equals(getName(), b.getName());
      else return false;
   }

   public int hashCode()
   {
      return Objects.hash(getName(), action);
   }

   public boolean implies(Permission other)
   {
      if (!(other instanceof BookCategoryPermission)) return false;
      BookCategoryPermission b = (BookCategoryPermission) other;
      if (action.equals("access")) {
			return categorySet().containsAll(b.categorySet())&&roleSet().containsAll(b.roleSet());
		} else
			return false;
   }

   public Set<String> categorySet() {
		Set<String> set = new HashSet<String>();
		List<String> CategoryAndRole = Arrays.asList(getName().split("/"));
		String category = CategoryAndRole.get(0);
		set.addAll(Arrays.asList(category.split(",")));
		return set;
	}
	
	public Set<String> roleSet() {
		Set<String> set = new HashSet<String>();
		List<String> CategoryAndRole = Arrays.asList(getName().split("/"));
		String role = CategoryAndRole.get(1);
		set.addAll(Arrays.asList(role.split(",")));
		return set;
	}
}

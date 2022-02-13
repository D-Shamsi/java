import java.util.*;
class RoleHierarchy{
  static class Node{
    String role;
    ArrayList<Node> subrole=new ArrayList<>();
  }
  static class User{
    String username;
    String userrole;
	public User(String name,String role){
		username=name;
		userrole=role;
	}
  }
  public static Node createRootRole(){
     Node t=new Node();
	 Scanner sc=new Scanner(System.in);
	 System.out.print("Enter the root role name: ");
     String root_role=sc.nextLine();
     t.role=root_role;
	 System.out.print("--------------------------\n");
     return t;
  }
  public static Node findNode(Node node,String role){
     if(node.role.equals(role)){
          return node;
     }else if (node.subrole != null){
          int i;
          Node result = null;
          for(i=0; result == null && i < node.subrole.size(); i++){
               result = findNode(node.subrole.get(i), role);
          }
          return result;
     }
       return null;
  }

  public static void displayRootRole(Node root){
	String str=root.role+" ";
	for(Node child:root.subrole)
	  {
		str=str+child.role+" ";
	  }
	  System.out.println(str);
     for(Node child:root.subrole)
	  {
		displayRootRole(child);
	  }
  }

  public static void displayRoles(Node node){
	Queue<Node> q=new ArrayDeque<>();
	q.add(node);
	Queue<Node> cq=new ArrayDeque<>();
     while(q.size()>0){
		node=q.remove();
		System.out.print(node.role+" ");
		for(Node child:node.subrole)
	  {
		 cq.add(child);
	  }
	  if(q.size()==0)
	   {
			q=cq;
			cq=new ArrayDeque<>();
	   }

     }
	 System.out.println();
  }

  public static void deleteRole(Node root,String del_role,String trans_role,ArrayList<User> users)
	{
		Node temp=findNode(root,del_role);
		temp.role=trans_role;
		for(User data:users)
	  {
		 if(data.userrole.equals(del_role))
			  data.userrole=trans_role;
	  }
	}

  public static void addSubRole(Node root){
     Node t=new Node();
	 System.out.print("Enter the sub role name: ");
	 Scanner sc=new Scanner(System.in);
     String role=sc.nextLine();
     System.out.print("Enter the reporting to the role name: ");
     String root_role=sc.nextLine();
     t.role=role;
	 Node temp=findNode(root,root_role);
	 temp.subrole.add(t);
  }

  public static void addUser(ArrayList<User> users){
	Scanner sc=new Scanner(System.in);
    System.out.print("Enter User Name: ");
    String username=sc.nextLine();
	System.out.print("Enter Role: ");
    String userrole=sc.nextLine();
	users.add(new User(username,userrole));
  }

  public static void displayUser(ArrayList<User> users){
	for(User data:users)
	  {
		 System.out.println(data.username+"-"+data.userrole);
	  }
  }

  public static void deleteUser(ArrayList<User> users,String del_user)
	{
		for(User data:users)
	  {
		 if(data.username.equals(del_user))
		  {
			 users.remove(data);
          }
	  }
	}

   public static int heigthhOfHierachy(Node root)
	{
		int ht=-1;
		for(Node child:root.subrole)
		{
			int ch=heigthhOfHierachy(child);
			ht=Math.max(ht,ch);
		}
		ht=ht+1;
		return ht;
	}

  public static void main(String[] args){
    //System.out.print(createRootRole().role);// Task1:- Root role name Display
	Node root=createRootRole();
	ArrayList<User> user=new ArrayList<>();
	System.out.println("Operation: ");
    System.out.println("    1. Add Sub Role.\n");
	System.out.println("    2. Display Roles.\n");
	System.out.println("    3. Delete Role.\n");
	System.out.println("    4. Add User.\n");
	System.out.println("    5. Display Users.\n");
	System.out.println("    6. Delete Users.\n");
	System.out.println("    7. Height of role hierachy.\n");
	while(true){
	System.out.print("Enter Operation to be Performed: ");
	Scanner sc=new Scanner(System.in);
	int choice=sc.nextInt();
	switch(choice){
		case 1:addSubRole(root);
		        break;
        case 2: displayRoles(root);
		        break;
        case 3: System.out.print("Enter the role to be deleted: ");
	            sc.nextLine();
				String delete_role=sc.next();
                System.out.print("Enter the role to be transfered: ");
				sc.nextLine();
	            String transfer_role=sc.next();
			    deleteRole(root,delete_role,transfer_role,user); 
		        break;
        case 4: addUser(user);
		        break;
        case 5: displayUser(user);
		        break;
        case 6: System.out.print("Enter username to be deleted: ");
	            sc.nextLine();
				String delete_user=sc.next();
			    deleteUser(user,delete_user);
		        break;
         case 7: int height=heigthhOfHierachy(root);
		         System.out.println("Height- "+height);
		         break;
        default:System.exit(0);;
	}
    }		
  }
}
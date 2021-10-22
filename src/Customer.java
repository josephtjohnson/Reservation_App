public class Customer{
  private String firstName;
  private String lastName;
  private String email;
  
  public Customer(String firstName, String lastName, String email){
    boolean keepRunning = true;
    String emailRegex = "^(.+)@(.+).(.+)$";
    pattern pattern = Pattern.compile(emailRegex);
    this.firstName = firstName;
    this.lastName = lastName;
    while (keepRunning){
      try{
        if(pattern.matcher(email).matches() == true) {
          this.email = email;
          keepRunning = false;
        }
      }
      catch (IllegalArgumentException e){
        System.out.println("Email format incorrect (ex. jeff@mail.com)");
        System.out.println("Type email");
        Scanner scanner = new Scanner(System.in);
        String email = String.parsestring(scanner.nextLine());
        if(pattern.matcher(email).matches() == true) { 
          this.email = email;
          keepRunning = false;
        }
        else {
        System.out.println("Invalid email format. Please try again (ex. jeff@mail.com)");
        }
      }
    }
  }
  @Override
  public String toString(){
    return "Customer: " + firstName + "" lastName "" email;
  }
}

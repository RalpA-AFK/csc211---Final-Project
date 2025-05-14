  The following code is a template for the e- commerce website that we would be making in class.
The first thing to focus on when going about this was whether this would be an interface that relies on user input as a prompt or input through the code itself to run. 
I decided to use the scanner class as a medium for searching and filtering information in the product list. 
Though not long currently they’re currently 7 items listed as products in the code and of them there are 3 different types of items that can be listed. 
(CPU (Intel i7) - Electronics, Eggs (Dozen) - Grocery, Empire Jeans - Clothing, Kosher Butter (500g) - Grocery, Nike Air Jordans - Clothing, RAM (16GB DDR4) - Electronics, SSD (1TB NVMe) - Electronics)
The listing are controlled by the Admin who is defined in the user class. 
The Admin currently has the ability to add product listings through writing in the main code itself as well as removing product listings and checking product inventory itself.
The inputs that are recognized in my code are from the perspective of a consumer. 
They are immediately prompted with the ability of a search function to search for an exact item and if refused they are then shown a filter function that will allow them to filter out whether they are looking for electronics, clothing or groceries. 
An example of this is shown below. 

searchResults = admin.inventory.stream()
    .filter(p -> p.category.toLowerCase().contains(categoryFilter))
    .collect(Collectors.toList());

  The above code shows the use of the admin to call a list of the current products. 
This list is then reviewed by the predicate which is defined as p and filters the categories according to the searchResults variable.
The result of this filtering is then collected using .collect(Collectors.toList()); shown at the bottom of the code. 
This code while simple is repeated about 3 times pertaining to each type of filtering whether it be on product type price range or name itself. 

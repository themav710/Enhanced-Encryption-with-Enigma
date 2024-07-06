import random

# Generate 10 random integers between 11 and 100 (inclusive)
# Open the file for writing in 'w' (write) mode
with open("Rotor_Settings.txt",'w') as file:
  for i in range(100):
    numbers = [random.randint(0,2147483647) for _ in range(10)]
    for j in numbers:
      file.write(str(j)+" ")
    file.write("\n")
# Print a message indicating successful writing
print("Numbers written to 'numbers.txt' successfully!")

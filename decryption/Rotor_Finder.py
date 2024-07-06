def print_line_by_number(filename, line_number):
  """
  Prints the line at a given number from a file.

  Args:
    filename: Path to the file containing lines.
    line_number: The line number to print (1-based indexing).

  Raises:
    ValueError: If the line number is out of range (less than 1 or greater than 100).
    FileNotFoundError: If the file is not found.
  """
  if line_number < 1 or line_number > 100:
    raise ValueError(f"Line number must be between 1 and 100 (inclusive).")

  try:
    with open(filename, 'r') as file:
      # Iterate through lines until the desired line number is reached
      for i, line in enumerate(file, 1):
        if i == line_number:
          print(line.rstrip())  # Remove trailing newline character
          return  # Exit the loop after printing the desired line
  except FileNotFoundError:
    print(f"Error: File '{filename}' not found.")


if __name__ == "__main__":
  filename = "Rotor_Settings.txt"  # Replace with your actual file name
  line_number = int(input("Enter line number: "))
  try:
    print_line_by_number(filename, line_number)
  except ValueError as e:
    print(f"Invalid line number: {e}")

def search_file(filename, search_string):
  """
  Searches a file for a given string and returns a list of line numbers where it's found.

  Args:
    filename: Path to the file to search.
    search_string: The string to search for.

  Returns:
    A list of line numbers where the search_string is found, or an empty list if not found.
  """
  line_numbers = []
  try:
    with open(filename, 'r') as file:
      for line_number, line in enumerate(file, 1):  # Start line number from 1
        if search_string in line:
          line_numbers.append(line_number)
  except FileNotFoundError:
    print(f"Error: File '{filename}' not found.")
  return line_numbers


if __name__ == "__main__":
  filename = "Hashes.txt"  # Replace with your actual file name
  search_string = input("Enter hash: ")  # Replace with the string to search
  found_lines = search_file(filename, search_string)

  if found_lines:
    print(f"String '{search_string}' found on line(s): {', '.join(map(str, found_lines))}")
  else:
    print(f"String '{search_string}' not found in the file.")

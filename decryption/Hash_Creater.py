import hashlib


def generate_hash(input_file, output_file):
  """
  Generates SHA512 hash for each line in the input file and writes them to the output file.

  Args:
    input_file: Path to the file containing lines.
    output_file: Path to the file where hashes will be written.
  """
  try:
    with open(input_file, 'r') as in_file, open(output_file, 'w') as out_file:
      for line in in_file:
        # Remove trailing newline character
        line = line.rstrip()
        # Create SHA512 hash object
        hasher = hashlib.sha512()
        # Update the hasher with the line (encoded as bytes)
        hasher.update(line.encode())
        # Get the hexadecimal digest of the hash
        hash_value = hasher.hexdigest()
        # Write the line number and hash to the output file
        out_file.write(f"{hash_value}\n")
  except FileNotFoundError:
    print(f"Error: File '{input_file}' not found.")


if __name__ == "__main__":
  input_file = "Rotor_Settings.txt"  # Replace with your actual file name
  output_file = "Hashes.txt"  # Replace with your desired output file name
  generate_hash(input_file, output_file)
  print(f"SHA512 hashes written to '{output_file}'.")

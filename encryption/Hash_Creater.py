import hashlib

def generate_sha512_hash(input_string):
  """
  Generates the SHA-512 hash for a given string.

  Args:
    input_string: The string to hash.

  Returns:
    The SHA-512 hash of the input string as a hexadecimal string.
  """
  hasher = hashlib.sha512()
  hasher.update(input_string.encode())  # Encode the string to bytes
  return hasher.hexdigest()

# Example usage
input_string = input("Enter settings: ")
sha512_hash = generate_sha512_hash(input_string)
print(f"SHA-512 hash: {sha512_hash}")

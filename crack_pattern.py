import argparse
import binascii

if __name__ == "__main__":
	parser = argparse.ArgumentParser()
	parser.add_argument("gesture", help="Location of the gesture.key file.")
	parser.add_argument("rainbow_table", help="Location of the rainbow table")
	args = parser.parse_args()

	# Read gesture file
	def read_gesture(path):
		with open(path, "rb") as gesture:
			return gesture.read()

	# Search the hash in the gesture file		
	def search_hash(path, hash):
		with open(path, 'rb') as rainbow_table:
			for line in rainbow_table:
				if line.find(hash.upper()) != -1:
					print('The gesture key is: ' + line.decode("utf-8")[0:line.decode("utf-8").index(';')])
	
	# extract sha1 hash
	sha1_hash = binascii.hexlify(bytearray(read_gesture(args.gesture)))

	# search the rainbow table for the hash and print the result.
	search_hash(args.rainbow_table, sha1_hash)
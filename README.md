# Cryptographer 1.0

A simple text encryptor based on the bitwise XOR operation.

## Usage
### Encoding
1) Enter the text that you want to encrypt in the **Result or input** field, and the key with which the encryption will be performed in the **Key** field.
2) Press the **Encode** button. The application will create an encrypted bin-file named ***encrypted_file.bin*** in the same folder. And it will also remove the initial text from the **Result or input** field.
### Decoding
1) Press on the **Select file** button and select the encrypted file. The label ***No file selected*** should change to ***File is selected***.
2) Enter the key in the **Key** field with which the encryption was performed.
3) Press on the **Decode** button. The decrypted text will appear in the **Result or input** field (be careful, the previous text will be deleted from this field).

## Supporting information
* The application uses KOI-8 encoding to write and read encrypted files.
* There is no set limit on the number of characters for the initial text or key.
* There is no set limit on the encrypted file size.
* An encrypted file can have any extension (or none at all).
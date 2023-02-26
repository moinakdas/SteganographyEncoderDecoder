# Steganography Encoder Decoder
SteganographyEncoderDecoder is a program to encode images with messages with an imperceptible difference to the image.

## Installation

Use the [git](https://git-scm.com/) tool to clone this repository.

```bash
git clone https://github.com/Sadkoi/SteganographyEncoderDecoder
```

## Usage

Once you have cloned the repository, navigate to the directory with the files and compile both.

```bash
javac Steganography.java
javac Picture.java
```

To encode a message in your image, run the script with your message and the path to your image.
For example, the following will encode the string "Hello World" to a PNG file located in the same directory.

```bash
java Steganography "Hello World" MyImage.png
```

To decode a message from an image, run the script with just the path to your image.
For example, the following will decode the PNG file encoded in the previous section.

```bash
java Steganography MyImage.png
```

## License
[GNU](https://www.gnu.org/licenses/gpl-3.0.en.html)

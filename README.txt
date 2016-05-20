hi, it's 10:54 and i'm writing a readme file.

so my capstone is a steganography tool, which essentially hides a file within another.
i take the file i want to hide is converted into a byte[], which is then inserted into
the least significant bit of the rbg of the cover image.

well, i probably used at least some concepts we learned this year... uhh theres like arrays
and actionlisteners? i admit i did not focus AT ALL on class hierarchy. However, i did learn
a ton about new things, like encryption and bit shifting. ALso it helped alot with learning
the eclipse IDE and windowbuilder

pseudo algorithms:	

open cover image, convert to int[] of rgb values
open target data, convert to bytestream
take password, check if it's confirmed, then generate a cipher key with it
encrypt the bytestream with the password
convert the int[] rgb values into 8bit numbers, and iterate and replace the least significant 
	bit with corresponding bit in the bytestream.
rebuild cover image and voila:

id say i made a pretty good gui, but it's still incomplete and im working on it:
you basically just work your way down the labeled jpanels on the left. 
after running you press save to save your altered cover image. (make sure to save as png)
(jpg compression is poop and ruins the precise rgb values)

oh dear gosh its 11:01 already